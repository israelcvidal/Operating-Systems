/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.io.*;

public class Server implements Runnable{
	private Socket client;
	
	Server(Socket clientThread){
		this.client = clientThread;
	}
	public void run() {
		InputStream in = null;
		BufferedReader bin = null;
		StringTokenizer stkn;
		int n1 = 0, n2 = 0;
		String operador = null;
		
		// we have a connection
		
		try {
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			in = client.getInputStream();
			bin = new BufferedReader(new InputStreamReader(in));
			// write the Date to the socket
			String line;
			while( (line = bin.readLine()) != null){
				
				stkn = new StringTokenizer(line, ":");
				
				operador = stkn.nextToken();
				n1 = Integer.valueOf(stkn.nextToken());
				n2 = Integer.valueOf(stkn.nextToken());
				
				switch(operador){
				case("*"):
					out.println(n1*n2);
					break;
					
				case("+"):
					out.println(n1+n2);
					break;
				case("-"):
					out.println(n1-n2);
					break;
				case("/"):
					out.println(n1/n2);
					break;
				default:
					out.println("Operador invalido");
					
				}
				
			}
			out.close();
			client.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Socket client = null;
		ServerSocket sock = null;
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		
		try {
			sock = new ServerSocket(6013);
			// now listen for connections
			while (true) {				
				executor.execute((new Server(sock.accept())));
			}
			//executor.shutdown();
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
		finally {
			if (sock != null)
				sock.close();
			if (client != null)
				client.close();
		}
	}
}

