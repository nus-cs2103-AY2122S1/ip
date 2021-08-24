import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
        try {
            Scanner scanner = new Scanner(System.in);
            boolean run = true;
            while (run) {
                String input = scanner.nextLine();
                printLine();
                String[] inputArr = input.split(" ", 2);
                try {
                    if (inputArr.length < 1) {
                        throw new DukeException("You didn't put any commands.");
                    }
                    String[] messageArr = new String[2];
                    switch (inputArr[0]) {
                    case "todo":
                        if (inputArr.length < 2) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        addTask(new Todo(inputArr[1]));
                        break;
                    case "deadline":
                        if (inputArr.length < 2) {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        messageArr = inputArr[1].split(" /by ", 2);
                        if (messageArr.length < 2) {
                            throw new DukeException("Deadline needs a /by clause after the description.");
                        }
                        addTask(new Deadline(messageArr[0], messageArr[1]));
                        break;
                    case "event":
                        if (inputArr.length < 2) {
                            throw new DukeException("The description of a event cannot be empty.");
                        }
                        messageArr = inputArr[1].split(" /at ", 2);
                        addTask(new Event(messageArr[0], messageArr[1]));
                        break;
                    case "done":
                        if (inputArr.length < 2) {
                            throw new DukeException("Please specify which task to delete.");
                        }
                        int number = Integer.parseInt(inputArr[1]);
                        if (list.get(number - 1) == null) {
                            throw new DukeException("This task doesn't exist");
                        }
                        System.out.println("    Nice! I've marked this task as done: ");
                        list.get(number - 1).markedAsDone();
                        System.out.println("      " + list.get(number - 1).toString());
                        break;
                    case "list":
                        listItems();
                        break;
                    case "bye":
                        System.out.println("    " + "Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case "remove":
                        if (inputArr.length < 2) {
                            throw new DukeException("Please specify which task to delete.");
                        }
                        int removeIndex = Integer.parseInt(inputArr[1]);
                        if (list.get(removeIndex - 1) == null) {
                            throw new DukeException("This task doesn't exist");
                        }
                        System.out.println("    Noted. I've removed this task: ");
                        System.out.println("      " + list.get(removeIndex - 1).toString());
                        list.remove(removeIndex - 1);
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        break;
                    case "save":
                        File dir = new File("./data/");
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        File file = new File("./data/duke.txt");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        try {
                            FileWriter writer = new FileWriter("./data/duke.txt");
                            writeToFile(writer);
                            writer.close();
                            System.out.println("    File saved to /data/duke.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new DukeException("Write file error");
                        }
                        break;
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException de) {
                    System.out.println("    " + de.getMessage());
                }
                printLine();
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private static void writeToFile(FileWriter writer) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            writer.write(num + "." + list.get(i).toString() + "\n");
        }
    }

    private static void listItems() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int num = i + 1;
            System.out.println("      " + num + "." + list.get(i).toString());
        }
    }

    private static void addTask(Task t) {
        System.out.println("     Got it. I've added this task: ");
        list.add(t);
        System.out.println("      " + t.toString());
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
