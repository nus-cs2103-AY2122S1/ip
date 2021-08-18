import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String introString = "Hey there! I'm Good Duke. How many I help you today?";
    private static String outroString = "That was an excellent chat - I look forward to seeing you again soon!";

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static String taskListString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return output;
    }

    public static String addedString(Task task) {
        return String.format("Alright, I've added this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, taskList.size());
    }

    public static String doneString(Task task) {
        return String.format("Certainly, I've marked this task as done: \n\t%s\n", task);
    }

    public static String deletedString(Task task) {
        return String.format("Certainly, I've deleted this task: \n\t%s\nNow, you have %d tasks in the list.\n", task, taskList.size());
    }

    public static void print(String str) {
        String horizontalLine = "________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(str);
        System.out.println(horizontalLine);

    }

    public static Command parseUserInput(String input) throws DukeException {
        String[] strings = input.split(" ");
        String operation = strings[0];
        switch (operation) {
            case "bye":
            case "list":
                return new Command(operation);
            case "done":
                if (strings.length == 1) {
                    throw new DukeException("You need to indicate which task number should be marked as done.");
                }
                try {
                    int doneTaskNum = Integer.parseInt(strings[1]);
                    return new Command(strings[0], doneTaskNum);
                } catch (NumberFormatException e) {
                    throw new DukeException("The task to be marked as done should be indicated its list index.");
                }
            case "todo":
                if (strings.length == 1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                return new Command(strings[0], input.substring(5));
            case "deadline":
                if (strings.length == 1) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String[] descriptionAndBy = input.substring(9).split("/by ");
                if (descriptionAndBy.length == 1) {
                    throw new DukeException("The done-by date of a deadline cannot be empty.");
                }
                return new Command(strings[0], descriptionAndBy[0], descriptionAndBy[1]);
            case "event":
                if (strings.length == 1) {
                    throw new DukeException("The description of an event cannot be empty.");
                }
                String[] descriptionAndAt = input.substring(6).split("/at ");
                if (descriptionAndAt.length == 1) {
                    throw new DukeException("The timing of an event cannot be empty.");
                }
                return new Command(strings[0], descriptionAndAt[0], descriptionAndAt[1]);
            case "delete":
                if (strings.length == 1) {
                    throw new DukeException("You need to indicate which task number should be deleted.");
                }
                try {
                    int delTaskNum = Integer.parseInt(strings[1]);
                    return new Command(strings[0], delTaskNum);
                } catch (NumberFormatException e) {
                    throw new DukeException("The task to be deleted should be indicated its list index.");
                }
            default:
                throw new DukeException("Sorry, I do not understand this command.");
        }
    }

    public static void main(String[] args) throws DukeException {
        print(introString);
        Command command;
        label:
        while (true) {
            String userEntry = sc.nextLine();
            try {
                command = parseUserInput(userEntry);
            } catch (DukeException e) {
                print(e.toString());
                continue;
            }
            Task task;
            switch (command.getOperation()) {
                case "bye":
                    print(outroString);
                    break label;
                case "list":
                    print(taskListString());
                    break;
                case "done":
                    task = taskList.get(command.getIndex() - 1);
                    task.setDone(true);
                    print(doneString(task));
                    break;
                case "todo":
                    task = new ToDo(command.getDescription());
                    taskList.add(task);
                    print(addedString(task));
                    break;
                case "deadline":
                    task = new Deadline(command.getDescription(), command.getTime());
                    taskList.add(task);
                    print(addedString(task));
                    break;
                case "event":
                    task = new Event(command.getDescription(), command.getTime());
                    taskList.add(task);
                    print(addedString(task));
                    break;
                case "delete":
                    task = taskList.get(command.getIndex() - 1);
                    taskList.remove(command.getIndex() - 1);
                    print(deletedString(task));
                    break;
            }
        }
    }
}
