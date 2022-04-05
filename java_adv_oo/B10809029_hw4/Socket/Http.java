
import java.io.*;
import java.net.*;
import java.util.Set;

public class Http {

    public static void main(String args[]) throws IOException, URISyntaxException {
        //loadURL(args[0],args.length > 1 ? args[1] : null, args.length > 2 ? args[2] : null); // http://www.yam.com
	loadURL();
    }

    //public static void loadURL(String host, String path, String query) {
    public static void loadURL() throws IOException, URISyntaxException {
            String protocol = "https";
            String host = "tw.search.yahoo.com";
            String path = "/search";
            String query = "fr=yfp-search-sb&p=�먦";
            URI uri = new URI(protocol, host,  path, query, null);
            String request = uri.toASCIIString(); // handling encoded issue
            System.out.println("request=" + request);
            URL url = new URL(request);
            URLConnection connection = url.openConnection();
//          System.out.println(connection.getContentEncoding());
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=big5");
//            connection.setRequestProperty("Content-Type", "application/xml");
            //System.out.println("property " + connection.getRequestProperty("Content-Type"));
			// Set<String> keys = connection.getHeaderFields().keySet();
//			System.out.println(keys);
//            InputStream gzipStream = new GZIPInputStream(stream); // when the url is a zip file
            InputStream stream = connection.getInputStream();
            saveAsFile(stream, "test.html", "UTF-8");
    }
    public static void saveAsFile(InputStream stream, String outputfile, String encoding) throws IOException {
            BufferedReader in = new BufferedReader(new InputStreamReader(stream, encoding));
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputfile), encoding));
            //PrintStream stream = new PrintStream(System.out, true, "UTF-8"); 
            //System.setOut(stream);
            in.lines().forEach(line -> System.out.println(line));
//            String line;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//                output.write(line + "\n");
//                output.write(line, 0, line.length());
//            }
            in.close();
            output.close();
        
    }
}
