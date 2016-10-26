/**
 * Client program requesting current date from server.
 *
 */
 
import java.net.*;
import java.io.*;

public class Client
{
	static int cId = 0;
	public static void main(String[] args) throws IOException {
		InputStream in = null;
		BufferedReader bin = null;
		Socket sock = null;
		PrintWriter out = null;
		BufferedReader stdin;
		
		try {
			sock = new Socket("127.0.0.1",6013);
			in = sock.getInputStream();
			out = new PrintWriter(sock.getOutputStream(), true);
			bin = new BufferedReader(new InputStreamReader(in));
			stdin = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Operador:numero1:numero2");
			out.println(stdin.readLine());
			System.out.println(bin.readLine());
			
		}
		catch (IOException ioe) {
			System.err.println(ioe);
		}
        finally {
        	sock.close();
        }
	}
}
