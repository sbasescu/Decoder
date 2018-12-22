import java.util.*;
import java.io.*;
public class DecodeDriver {
	
	
	public static void main(String[] args) throws IOException {
		Message [] MessageArr = new Message [args.length];
		for (int i = 0; i < args.length; i++) { //loops through args
			String str = "";
			Message m = new Message(); //creates new message object
			Scanner fr = new Scanner(new File(args[i])); //reads through file
			while (fr.hasNextLine())
				str += "\n" + fr.nextLine(); //sets string equal to contents of file
			m.setEncoded(str); //sets encoded message to the message object
			MessageArr[i] = m; 
			fr.close();
		}
		/*
		 * Goes through the command line arguments and for each one, calls the decode 
		 * method and prints out the decoded message. Also writes a file for each decoded 
		 * message. 
		 */
		for (int i = 0; i < MessageArr.length; i++) {
			Decode.decodeMessage(MessageArr[i]);
			System.out.println("Reading and decoding messages in " + args[i] + "...");
			System.out.println();
			System.out.println("Decoded Message:");
			System.out.println(MessageArr[i].getDecoded());
			System.out.println();
			BufferedWriter out = new BufferedWriter(new FileWriter("decoded" + i + ".txt"));
			out.write(MessageArr[i].getDecoded());
			out.close();
		}			
	}
}
