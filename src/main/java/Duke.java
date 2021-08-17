import java.util.Scanner;

public class Duke {

    private static final String LINE = "----------------------------------------------";
    private Task[] taskList = new Task[100];
    private int size = 0;

    public void greeting() {
        // Credits to http://allaboutfrogs.org/gallery/frogstuff/ascii.html
        // for the frog ASCII text art!
        String frog =
                "    _____\n" +
                        "   /     \\______\n" +
                        "  | o     |     \\____\n" +
                        "  /\\_____/           \\___\n" +
                        " /                       \\\n" +
                        "|_______/                 \\\n" +
                        "  \\______   _       ___    \\\n" +
                        "        /\\_//      /   \\    |\n" +
                        "       // //______/    /___/\n" +
                        "      /\\/\\/\\      \\   / \\ \\\n" +
                        "                    \\ \\   \\ \\\n" +
                        "                      \\ \\   \\ \\\n" +
                        "                       \\ \\  /\\/\\\n" +
                        "                       /\\/\\\n";
        String greeting = "I am Jo the Frog! RIBBIT! \n";
        System.out.println(frog + greeting + "How may I help you?\n" + LINE);
    }

    public void echo(String input) {
        if (input.equals("bye")) {
            System.out.println("See you again in my frog hole! RIBBIT!");
            System.exit(0);
        } else if (input.startsWith("done")) {
            int index = input.charAt(5) - 49;
            taskList[index].markAsDone();
            System.out.println("You have swallowed that pesky fly! RIBBIT!");
            System.out.println("  " + taskList[index].toString());
        } else if (input.equals("list")) {
            System.out.println("Here is your menu for today:");
            for (int i = 0; i < size; i++) {
                System.out.println(i + 1 + "." + taskList[i].toString());
            }
        } else {
            if (input.startsWith("todo")) {
                taskList[size] = new ToDo(input.substring(5));
            } else if (input.startsWith("deadline")) {
                int index = input.indexOf("/by");
                taskList[size] = new Deadline(input.substring(9, index),
                        input.substring(index + 4));
            } else { // is an Event
                int index = input.indexOf("/at");
                taskList[size] = new Event(input.substring(6, index),
                        input.substring(index + 4));
            }

            System.out.println("A fly has been added to the menu:");
            System.out.println("  " + taskList[size].toString());
            size++;
            System.out.println("Now you have " + size + " flies to eat! RIBBIT!");
        }
    }

    public static void main(String[] args) {
        Duke jo = new Duke();
        jo.greeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                DukeException.checkInput(input);
                jo.echo(input);
            } catch (DukeException e) {
                System.out.println("ERROR: " + e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }
        scanner.close();
    }
}
