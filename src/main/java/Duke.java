import java.util.Scanner;
public class Duke {
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String greeting = "Hello! I'm Duke.\n" + "What can I do for you? :)";
        System.out.println(logo + greeting);

        String input;
        while (true) {
            input = getInput();
            if (input.toUpperCase().equals("BYE")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Bai bai!");

    }

}
