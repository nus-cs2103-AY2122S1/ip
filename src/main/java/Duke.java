import java.util.Scanner;

public class Duke {

    private static String[] myList = new String[100];
    private static int myListLength = 0;

    private static void echo() {
        Scanner myScanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            String input = myScanner.nextLine();
            if (input.equals("bye")) {
                done = true;
                System.out.println("Bye for now!");
            } else {
                System.out.println(input);
            }
        }
    }

    private static void add() {
        Scanner myScanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            String input = myScanner.nextLine();
            if (input.equals("bye")) {
                done = true;
                System.out.println("Bye for now!");
            } else if (input.equals("list")) {
                for (int i = 0; i < myListLength; i++) {
                    System.out.println(i + 1 + ". " + myList[i]);
                }
            } else {
                myList[myListLength] = input;
                myListLength++;
                System.out.println("added: " + input);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Sup! I'm Luka, your personal assistant.\n");
        add();
    }
}
