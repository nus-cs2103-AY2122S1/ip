import java.util.Scanner;

public class Lifeline {

    private void greet() {
        String lifeline = " _      _____ ______ ______ _      _____ _   _ ______\n"
                + "| |    |_   _|  ____|  ____| |    |_   _| \\ | |  ____|\n"
                + "| |      | | | |__  | |__  | |      | | |  \\| | |__\n"
                + "| |      | | |  __| |  __| | |      | | | . ` |  __|\n"
                + "| |____ _| |_| |    | |____| |____ _| |_| |\\  | |____\n"
                + "|______|_____|_|    |______|______|_____|_| \\_|______|\n";
        System.out.println("Hello! I am\n" + lifeline);
        System.out.println("What can I help you with today?\n");
    }

    private void echo(String input) {
        System.out.println("You have said " + "\"" + input + "\".");
        System.out.println("Anything else?\n");
    }

    private void exit() {
        System.out.println("Goodbye! Thanks for chatting with me!\n");
    }

    public static void main(String[] args) {
        Lifeline lifeline = new Lifeline();
        lifeline.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println();
            lifeline.echo(input);
            input = sc.nextLine();
        }
        lifeline.exit();
    }
}
