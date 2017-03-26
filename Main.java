import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		String answer = "";
		Scanner sc = new Scanner(System.in);
		Functions.presentation();
		while (!answer.equals("no")) {
			if (Functions.choixMode() == 1)
				Functions.encryptedMessage();
			else
				Functions.transfert2();
			System.out.print("\nWould you like to retry ? [yes/no] : ");
			answer = sc.nextLine();
			while (!answer.equals("yes") && !answer.equals("no")) {
				System.out.print("Please enter yes or no : ");
				answer = sc.nextLine();
			}
		}
		sc.close();
		System.out.println("\nBye Bye !\nThank you for using Caius");
	}
}
