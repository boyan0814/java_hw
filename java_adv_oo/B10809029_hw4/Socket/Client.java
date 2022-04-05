
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	// Client 狀態
	enum State {
		ACCEPTING_COMMAND,
		RECEIVING_FILE
	}

	String serverName;
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	State state;
	BufferedWriter writer;

	public Client(String name, int port) throws IOException {
		serverName = name;
		state = State.ACCEPTING_COMMAND;
		socket = new Socket(InetAddress.getByName(serverName), port);
		System.out.println("create client socket ok");
		output = new ObjectOutputStream(socket.getOutputStream());
		System.out.println("create output stream ok");
		output.flush();
		input = new ObjectInputStream(socket.getInputStream());
		System.out.println("create input stream ok");
		System.out.println("Please enter account/password to login");
		System.out.print("username:");
	}

	private void mainLoop() throws IOException, ClassNotFoundException {
		String command, message;
		switch (state) {
			case ACCEPTING_COMMAND:
				Scanner scanner = new Scanner(System.in);
				String commandLine = scanner.nextLine();
				output.writeObject(commandLine);
				output.flush();
				message = (String) input.readObject();
				String tokens[] = commandLine.split(" ");
				command = tokens[0];
				if (command.equals("get") && message.equals("ready")) {
					System.out.println("Ready to receive " + tokens[1]);
					writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tokens[1]), "UTF8"));
					state = State.RECEIVING_FILE;
					break;
				}
				if (message.equals("password:") || message.equals("No this username\nusername:")
						|| message.equals("Password wrong\n---Reset Login---\nusername:"))
					System.out.print(message);
				else
					System.out.println(message);
				if (command.equals("quit") || command.equals("exit")) {
					return;
				}
				break;

			case RECEIVING_FILE:
				message = (String) input.readObject();
				if (message == null) {
					// System.out.println("receive null");
					writer.close();
					writer = null;
					state = State.ACCEPTING_COMMAND;
					break;
				}
				System.out.println("receive: " + message);
				writer.write(message + "\n");
				break;
		}
	}

	public void exec() {
		try {
			while (true) {
				mainLoop();
			}
		} catch (Exception ex) {
//                    ex.printStackTrace();
//			Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
		}
//                System.out.println("exit exec");
	}

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		new Client("localhost", 1234).exec();
	}
}
