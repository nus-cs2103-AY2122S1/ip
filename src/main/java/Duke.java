import java.util.Scanner;

public class Duke {

    /**
     *
     */
    private static void echo(String input) {
        System.out.println(input);
    }
    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            String input = sc.next();

            switch (input) {
                case "bye":
                    System.out.println("Seeya!");
                    exit = true;
                    break;
                default:
                    echo(input);
                    break;
            }

        }

        sc.close();
    }
}
