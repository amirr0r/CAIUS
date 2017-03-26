import java.awt.RenderingHints.Key;
import java.util.Scanner;

public class Functions {
	private static Scanner sc;
	public static void presentation() {
		System.out.println("\t*************Hi dear user*************"
				+ "\nMy name is Caius and I will help you generate a completely encrypted message."
				+ "\nFor that, I will use a simple algorithm nicknamed the rot."
				+ "\nThe \"Rot\" is a simple letter substitution cipher,"
				+ "\nthat replaces a letter with the letter x letters after it in the alphabet"
				+ "\nFor example, the rot-13 substituate \'A\' to \'N\'.\n");
	}
	/**
	 * 
	 * @return which mode the user want to run
	 */
	public static int choixMode() {
		int choice;
		sc = new Scanner(System.in);
		System.out.println("Choose your mode : \n"
				+ "1.A simple Rot-X\n"
				+ "2.A encrypted message with a key\n"
				+ "Please enter 1 or 2 : ");
		choice = Integer.parseInt(sc.nextLine());
		while (choice < 1 || choice > 2) {
			System.out.println("Choose an option of the menu ! Try again : ");
			choice = Integer.parseInt(sc.nextLine());
		}
		return choice;
	}
	/**
	 * 
	 * @return the key composed of the rot-x for every characters
	 */
	public static int [] key() {
		String key;
		System.out.println("Good choice, enter your key (no more than 140 characters) :");
		key = sc.nextLine();
		while (key.length() > 140){
			System.out.println("I said no more than 140 characters !!!"
					+ "\nTry again :");
			key = sc.nextLine();
		}
		int [] t = new int[key.length()];
		for (int i = 0; i < key.length(); i++) {
			if(key.charAt(i) >= 'A' && key.charAt(i) <= 'Z')
				t[i] = (int) key.charAt(i) - 64;
			else if(key.charAt(i) >= 'a' && key.charAt(i) <= 'z')
				t[i] = (int) key.charAt(i) - 96;
			else if(key.charAt(i) >= '!' && key.charAt(i) <= '/')
				t[i] = (int) key.charAt(i) - 32;
			else if(key.charAt(i) >= ':' && key.charAt(i) <= 94)
				t[i] = (int) key.charAt(i) - 58;
			else if(key.charAt(i) >= '{' && key.charAt(i) <= '~')
				t[i] = (int) key.charAt(i) - 123;
			else if(key.charAt(i) >= '0' && key.charAt(i) <= '9')
				t[i] = (int) key.charAt(i) - 47;
			
		}
		return t;
	}
	/**
	 * 
	 * @param m->real message
	 * @param t->the key
	 * @return->the final message
	 */
	public static char [] transfertKey(String m, int [] t) {
		char [] newMessage = new char [m.length()];
		int j = 0;
		for (int i = 0; i < newMessage.length; i++) {
			if(m.charAt(i) >= 'A' && m.charAt(i) <= 'Z')
				newMessage[i] = (char) ((((m.charAt(i)-65)+t[j])%26)+65);
			else if(m.charAt(i) >= 'a' && m.charAt(i) <= 'z')
				newMessage[i] = (char) ((((m.charAt(i)-97)+t[j])%26)+97);
			else if(m.charAt(i) >= '!' && m.charAt(i) <= '/')
				newMessage[i] = (char) ((((m.charAt(i)-33)+t[j])%26)+33);
			else if(m.charAt(i) >= ':' && m.charAt(i) <= 94)
				newMessage[i] = (char) ((((m.charAt(i)-59)+t[j])%26)+59);
			else if(m.charAt(i) >= '{' && m.charAt(i) <= '~')
				newMessage[i] = (char) ((((m.charAt(i)-124)+t[j])%26)+124);
			else if(m.charAt(i) >= '0' && m.charAt(i) <= '9')
				newMessage[i] = (char) ((((m.charAt(i)-48)+t[j])%26)+48);
			else
				newMessage[i] = m.charAt(i);
			j++;
			if(j == t.length)
				j= 0;
		}
		return newMessage;
	}
	/*
	 * print the final message (mode 2)
	 */
	public static void transfert2() {
		int [] key = key();
		String m = message();
		char [] c = transfertKey(m, key);
		System.out.println("\nHere your new message : \n");
		for (int i = 0; i < c.length; i++)
			System.out.print(c[i]);
		System.out.println();
	}
	/**
	 * 
	 * @return how much the user wants rotate his message
	 */
	public static int rotNB() {
		sc = new Scanner(System.in);
		int rot = 0;
		System.out.print("So, how many letters would you like to rotate in your message ? ");
		rot = Integer.parseInt(sc.next());
		while (rot < 0) {
			System.out.print("The rot must be positive. Try again : ");
			rot = Integer.parseInt(sc.next());
		}
		return rot;
	}
	/**
	 * 
	 * @return the original message of the user whitout rot
	 */
	public static String message(){
		sc = new Scanner(System.in);
		String message;
		System.out.println("Please enter your message (no more than 140 characters) :");
		message = sc.nextLine();
		while (message.length() > 140){
			System.out.println("I said no more than 140 characters !!!"
					+ "\nTry again :");
			message = sc.nextLine();
		}
		return message;
	}
	/**
	 * @param s is the original message
	 * @return the message as a char board. It will help us for manipulate it
	 */
	public static char[] transfert(String s) {
		char [] copy = new char[s.length()];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = s.charAt(i);
		}
		return copy;
		
	}
	/**
	 * 
	 * @param c is the char board which we talked about at the previous function
	 * @param nb corresponds to how much the user wants to rotate
	 * @return the message as a char board with a nb-rotate for the capital letters
	 */
	public static char[] rotMaj(char [] c, int nb) {
		char [] rotMaj = new char [c.length];
		int change = 0;
		for (int i = 0; i < rotMaj.length; i++) { // table ascii : A = 65 et Z = 90
			if (c[i] >= 'A' && c[i] <= 'Z') {
				change = (int) c[i];
				change = change - 65;
				change = ((change + nb) % 26);
				change = change + 65;
				c[i] = (char) change;
			}
			rotMaj[i] = c[i];
		}
		return rotMaj;
	}
	/**
	 * 
	 * @param c is the char board which we talked about at the previous-previous function
	 * @param nb corresponds to how much the user wants to rotate
	 * @return the message as a char board with a nb-rotate for the lowercase letters
	 */
	public static char[] rotMinus(char [] c, int nb) {
		char [] rotMinus = new char [c.length];
		int change = 0;
		for (int i = 0; i < rotMinus.length; i++) {
			if (c[i] >= 'a' && c[i] <= 'z') { // table ascii : a = 97 et z = 122
				change = (int) c[i];
				change = change - 97;
				change = ((change + nb) % 26);
				change = change + 97;
				c[i] = (char) change;
			}
			rotMinus[i] = c[i];
		}
		return rotMinus;
	}
	/*
	 * print the message after his transformation
	 */
	public static void encryptedMessage() {
		int rotate = rotNB();
		String m = message();
		char [] c = transfert(m);
		c = rotMaj(c, rotate);
		c = rotMinus(c, rotate);
		System.out.println("\nHere your new message : \n");
		for (int i = 0; i < c.length; i++)
			System.out.print(c[i]);
		System.out.println();
	}
}
