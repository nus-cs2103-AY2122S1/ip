import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String LINE = "----------------------------------------------";
    private static final ArrayList<Task> taskList = new ArrayList<>();

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

    public void exit() {
        System.out.println("See you again in my frog hole! RIBBIT!");
    }

    public void addTask(String taskType, String description, String op) {
        if (taskType.equals("todo")) {
            taskList.add(new ToDo(description));
        } else if (taskType.equals("deadline")) {
            String[] s = op.split(" ", 2);
            LocalDate date = LocalDate.parse(s[0]);
            taskList.add(new Deadline(description, date, s[1]));
        } else { // is an Event
            taskList.add(new Event(description, op));
        }
    }

    public void readData(String s) {
        String[] args = s.split(",", 4);
        switch (args[0]) {
            case "T":
                addTask("todo", args[2], "");
                break;
            case "D":
                addTask("deadline", args[2], args[3]);
                break;
            case "E":
                addTask("event", args[2], args[3]);
                break;
            default:
                System.out.println("Shouldn't reach here");
                break;
        }
        if (args[1].equals("1")) {
            taskList.get(taskList.size() - 1).markAsDone();
        }
    }

    public void echo(String str) {

        String[] input = str.split(" ", 2);
        String arg = input[0];

        switch (arg) {
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
                System.out.println("Now you have " + taskList.size() + " flies to eat! RIBBIT!");
                break;
            }
            case "list":
                System.out.println("Here is your menu for today:");
                for (int i = 0; i < taskList.size(); i++) {
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
                    String[] taskDetail = input[1].split(" /at ");
                    addTask(input[0], taskDetail[0], taskDetail[1]);
                }
                System.out.println("A fly has been added to the menu:");
                System.out.println("  " + taskList.get(taskList.size() - 1).toString());
                System.out.println("Now you have " + taskList.size() + " flies to eat! RIBBIT!");
                break;
        }
    }

    public static void main(String[] args) {
        Duke jo = new Duke();
        jo.greeting();

        try {
            File data = new File("./src/main/data/duke.txt");
            Scanner s = new Scanner(data);
            while (s.hasNextLine()) {
                jo.readData(s.nextLine());
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File not found! :(");
            System.out.println(LINE);
        }

        Scanner scanner = new Scanner(System.in);
        for (String input = scanner.nextLine(); !input.equals("bye"); input = scanner.nextLine()) {
            try {
                DukeException.checkInput(input);
                jo.echo(input);
            } catch (DukeException e) {
                System.out.println("ERROR: " + e.getMessage());
            } finally {
                System.out.println(LINE);
            }
        }
        jo.exit();

        try {
            FileWriter myFile = new FileWriter("./src/main/data/duke.txt");
            for (Task task : taskList) {
                myFile.write(task.print() + "\n");
            }
            myFile.close();
        } catch (IOException e) {
            System.out.println("Given file is a directory not a file :(");
        }
        scanner.close();
    }
}
