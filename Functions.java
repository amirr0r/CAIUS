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
	public static int rotNB() {
		sc = new Scanner(System.in);
		int rot = 0;
		System.out.print("So, how many letters would you like to rotate in your message ? ");
		rot = Integer.parseInt(sc.next());
		// voir si c'est possible avec des négatifs
		return rot;
	}
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
	public static char[] transfert(String s) {
		char [] copy = new char[s.length()];
		for (int i = 0; i < copy.length; i++) {
			copy[i] = s.charAt(i);
		}
		return copy;
		
	}
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
