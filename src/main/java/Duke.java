import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static final String LINE = "----------------------------------------------";
    private ArrayList<Task> taskList = new ArrayList<>();
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

    public void echo(String str) {

        String[] input = str.split(" ", 2);
        String arg = input[0];

        switch (arg) {
            case "bye":
                System.out.println("See you again in my frog hole! RIBBIT!");
                System.exit(0);
            case "done": {
                int index = Integer.parseInt(input[1]) - 1;
                taskList.get(index).markAsDone();
                System.out.println("You have swallowed that pesky fly! RIBBIT!");
                System.out.println("  " + taskList.get(index).toString());
                break;
            }
            case "delete": {
                int index = Integer.parseInt(input[1]) - 1;
                System.out.println("Rotten flies deserve to die!");
                System.out.println("  " + taskList.get(index).toString());
                taskList.remove(index);
                size--;
                System.out.println("Now you have " + size + " flies to eat! RIBBIT!");
                break;
            }
            case "list":
                System.out.println("Here is your menu for today:");
                for (int i = 0; i < size; i++) {
                    System.out.println(i + 1 + "." + taskList.get(i).toString());
                }
                break;
            default:
                String task = input[1];
                if (arg.equals("todo")) {
                    taskList.add(new ToDo(task));
                } else if (input[0].equals("deadline")) {
                    String[] taskDetail = task.split("/by ");
                    String[] s = taskDetail[1].split(" ", 2);
                    LocalDate date = LocalDate.parse(s[0]);
                    taskList.add(new Deadline(taskDetail[0], date, s[1]));
                } else { // is an Event
                    String[] taskDetail = task.split("/at");
                    taskList.add(new Event(taskDetail[0], taskDetail[1]));
                }

                System.out.println("A fly has been added to the menu:");
                System.out.println("  " + taskList.get(size).toString());
                size++;
                System.out.println("Now you have " + size + " flies to eat! RIBBIT!");
                break;
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
