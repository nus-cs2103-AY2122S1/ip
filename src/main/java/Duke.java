import java.util.Scanner;

public class Duke {

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

    public static void main(String[] args) {
        System.out.println("Sup! I'm Luka, your personal assistant.\n");
        echo();
    }
}
