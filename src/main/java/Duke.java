import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printFormatted("Hola! I'm Blitz :) \n\tWhat can I do for you?");
        String command = scanner.nextLine();
        while(!command.equals("bye")) {
            printFormatted(command);
            command = scanner.nextLine();
        }
        printFormatted("Adiós. Hope to see you again soon!");
    }

    public static void printFormatted(String str) {
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.println("\n \t" + str);
        System.out.print('\t');
        for(int i = 0; i < 40; i++) {
            System.out.print('_');
        }
        System.out.println('\n');
    }
}
