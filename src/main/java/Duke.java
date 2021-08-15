import java.util.Scanner;

public class Duke {
    private boolean readingInput;

    Duke() {
        this.readingInput = true;
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/

        Duke d = new Duke();

        String openingMessage = "-------------------------------------------- \n"
                        + "Hello! I'm Duke \n"
                        + "What can I do for you? \n"
                        + "-------------------------------------------- \n";
        System.out.println(openingMessage);

        Scanner sc = new Scanner(System.in);

        while (d.readingInput) {
            String input = sc.next();
            String output;

            switch (input) {
                case "list":
                    output = "-------------------------------------------- \n"
                            + "list \n"
                            + "-------------------------------------------- \n";
                    break;

                case "blah":
                    output = "-------------------------------------------- \n"
                            + "blah \n"
                            + "-------------------------------------------- \n";
                    break;

                case "bye":
                    output = "-------------------------------------------- \n"
                            + "Bye! Hope to see you again soon! \n"
                            + "-------------------------------------------- \n";
                    d.readingInput = false;
                    break;

                default:
                    output = "Error... Invalid input... Please try again \n";
            }
            System.out.println(output);
        }


    }
}
