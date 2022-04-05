
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// 繼承執行序
public class Server extends Thread {
	ArrayList<String> keyAccount = new ArrayList();
	ArrayList<String> keyPassword = new ArrayList();
	// 例外狀況介面
	interface ProcessLine {
		void process(String line, int i) throws IOException;
	}

	class Connection extends Thread {

		Boolean accountBool = false;
		Boolean passwordBool = false;
		String account = "account";
		String password = "password";
		Socket socket;
		ObjectInputStream input;
		ObjectOutputStream output;
		File workingDirectory;

		// 初始化
		public Connection(Socket socket) {

			this.socket = socket;
			try {
				// 物件輸入、輸出流建置
				System.out.println("open input stream");
				input = new ObjectInputStream(socket.getInputStream());
				System.out.println("open output stream");
				output = new ObjectOutputStream(socket.getOutputStream());
				System.out.println("open output stream ok");

				// 設定為該程式位置
				workingDirectory = new File("").getAbsoluteFile();
//				workingDirectory = new File("C://");//new File(".");//
				System.out.println("workingDirectory is " + workingDirectory);
			} catch (IOException x) {
				x.printStackTrace();
			}
		}
		// Print 出該 path 下的所有檔案 (dir)
		private void showDirectory(File dir) throws IOException {
			StringBuilder s = new StringBuilder();
			for (File file : dir.listFiles()) {
				s.append("\n" + file.getName());
			}
			output.writeObject(s.toString());
		}

		// 修改 Path (cd)
		private void changeDirectory(String dirName) throws IOException {
			String message;

			if (dirName.equals("..")) { // 進入上層位置
				System.out.println("current " + workingDirectory.getAbsolutePath());
				System.out.println("parent is " + workingDirectory.getParentFile());
				workingDirectory = workingDirectory.getParentFile();
				message = "working directory becomes " + workingDirectory;
			} else { // 進入指定下層位置
				File subDir = new File(workingDirectory.getAbsolutePath() + "\\" + dirName);
				if (subDir.exists() && subDir.isDirectory()) {
					workingDirectory = subDir;
					System.out.println("change to subdir " + subDir);
					message = "working directory becomes " + workingDirectory;
				} else {
					message = "subDir " + workingDirectory;
					System.out.println("Incorrect subdirectory: " + subDir);
				}
			}
			output.writeObject(message);
		}

		// 刪除指定資料
		private void deleteFile(String fileName) throws IOException {
			File file = new File(workingDirectory.getAbsolutePath() + "\\" + fileName);
			if (!file.exists()) {
				output.writeObject("File " + file.getName() + " not found.");
			} else if(!file.isFile()) {
				output.writeObject(file.getName() + " is not a file.");
			} else {
				file.delete();
				output.writeObject("File " + file.getName() + " deleted");
			}
		}

		// 取得指定資料
		private void getFile(String fileName) throws IOException {
			File file = new File(workingDirectory.getAbsolutePath() + "\\" + fileName);
			System.out.println("get file " + file.getAbsolutePath());
			if (file.exists() && file.isFile()) {
				System.out.println("ready to send " + file);
				output.writeObject("ready");
				readLines(file, "UTF8", (String line, int i) -> output.writeObject(line));
				output.writeObject(null);
			}
		}

		// 指令接收
		public boolean processCommand(String received) throws IOException {
			String tokens[] = received.split(" ");
			String command = tokens[0];
			switch (command) {
				case "dir":
					if (tokens.length == 1) {
						showDirectory(workingDirectory);
					} else {
						File dir = new File(tokens[1]);
						if (!dir.exists()) {
							output.writeObject("Syntax error: directory " + dir + " not existing");
						} else if (dir.isFile()) {
							output.writeObject("Syntax error: cd $dirname");
						} else {
							showDirectory(dir);
						}
					}
					break;
				case "cd":
					if (tokens.length == 1) {
						output.writeObject("Syntax error: cd $dirname");
						break;
					}
					changeDirectory(tokens[1]);
					break;
				case "del":
					if (tokens.length == 1) {
						output.writeObject("Syntax error: del $filename");
						break;
					}
					deleteFile(tokens[1]);
					break;
				case "get":
					if (tokens.length == 1) {
						output.writeObject("Syntax error: get $filename");
						break;
					}
					getFile(tokens[1]);
					break;
				case "exit":
				case "quit":
					close();
					return false;
				default:
					output.writeObject("Unknown command: " + received);
			}
			return true;
		}

		@Override
		// Connection Thread 進入點
		public void run() {
			try {
				// 存取暫存
				output.flush();
				boolean toContinue = true;

				readKey("UTF8");
				int accountAddress = -1;

				// 將取得指令寫入
				while (toContinue) {
					String received = (String) input.readObject();  //blocking
					System.out.println("receiving [" + received + "]");

					if (accountBool==false){
						if (keyAccount.contains(received)){

							for (int i=0;i<keyAccount.size();i++){
								if (keyAccount.get(i).equals(received)){
									accountAddress = i;
								}
							}

							accountBool = true;
							System.out.println(account);
							output.writeObject("password:");
						}else {
							output.writeObject("No this username\nusername:");
						}
						continue;
					}

					if (passwordBool==false){


						if (accountAddress == -1){
							output.writeObject("Password wrong\n---Reset Login---\nusername:");
							accountBool = false;
							continue;
						}

						if (keyPassword.get(accountAddress).equals(received)) {
							passwordBool = true;
							System.out.println(password);
							output.writeObject("---Login Sucess---");
						}else {
							output.writeObject("Password wrong\n---Reset Login---\nusername:");
							accountBool = false;
						}
						continue;
					}

					toContinue = processCommand(received);
				}
			} catch (ClassNotFoundException x) {
				x.printStackTrace();
			} catch (IOException x) {
				System.out.println(x);
				close();
			}
		}

		// 關閉 stream
		public void close() {
			try {
				System.out.println("close connection...");
				input.close();
				output.close();
				socket.close();
				removeConnection(this);
			} catch (IOException x) {
				x.printStackTrace();
			}
		}
	}

	int maxConnections;
	ServerSocket serverSocket;
	List<Connection> connections= Collections.synchronizedList(new LinkedList());

	// 讀取資料
	public static void readLines(File file, String encoding, ProcessLine processor) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
		int i = 0;
		while (true) {
			String line = input.readLine();
			if (line == null) {
				break;
			}
			processor.process(line, i);
			i++;
		}
		input.close();
	}


	// 讀取 key
	public void readKey(String encoding) {
		try {
			FileInputStream keyFile = new FileInputStream("key.txt");
			BufferedReader input = new BufferedReader(new InputStreamReader(keyFile, encoding));

			while (true) {
				String line = input.readLine();

				System.out.println(line);

				String[] temp = line.split(" ");
				keyAccount.add(temp[0]);
				keyPassword.add(temp[1]);

				System.out.println(keyAccount);
				System.out.println(keyPassword);
				if (line == null) {
					break;
				}
			}
			input.close();
		}catch (Exception e){

		}
	}

	public Server(int port, int capacity) throws IOException {
		maxConnections = capacity;

		// 設定 Port 和最多連線數
		serverSocket = new ServerSocket(port, maxConnections);
	}

	void removeConnection(Connection connection) {
		connections.remove(connection);
	}

	// Server Thread 進入點
	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("wait for connections...");
				Socket connSocket = serverSocket.accept();
				System.out.println("maxConnections=" + maxConnections);
				if (connections.size() < maxConnections) {
					System.out.println("create connection socket");
					Connection connection = new Connection(connSocket);
					connections.add(connection);
					connection.start();
				}
			}
		} catch (IOException x) {
			x.printStackTrace();
		}
	}

	// class 進入點
	public static void main(String args[]) throws IOException {
		new Server(1234, 100).start();
	}
}
