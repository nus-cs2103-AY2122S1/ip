import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected ArrayList<Task> toDoList;

    public Duke() {
        this.toDoList = new ArrayList<>(100);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        greeting();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                scanner.close();
                bye();
                break;
            }

            try {
                runCommand(command);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    public void runCommand(String command) throws DukeException {
        if (command.equals("list")) {
            list();
        } else if (command.startsWith("done")) {
            done(command.substring(5));
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            try {
                add(command.substring(5), TaskType.TODO);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HH:MM");
            }
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                add(command.substring(9), TaskType.DEADLINE);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HH:MM");

            }
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            try {
                add(command.substring(6), TaskType.EVENT);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please write your date and time in the following format: " +
                        "D/MM/YYYY HH:MM");
            }
        } else if (command.startsWith("delete")) {
            delete(command.substring(7));
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void add(String description, TaskType taskType) throws DateTimeParseException {
        if (toDoList.size() < 100) {

            Task task;

            if (taskType == TaskType.TODO) {
                task = new Todo(description);
            } else if (taskType == TaskType.DEADLINE) {
                String newDescription = description.substring(0, description.indexOf(" /by "));
                String by = description.substring(description.indexOf("/by ") + 4);
                task = new Deadline(newDescription, by);
            } else {
                String newDescription = description.substring(0, description.indexOf(" /at "));
                String at = description.substring(description.indexOf("/at ") + 4);
                task = new Event(newDescription, at);
            }

            System.out.println("Got it. I've added this task:");
            toDoList.add(task);
            System.out.println(task);
            printNowSize();
        }
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        int num = 1;
        for (Task task: toDoList) {
            System.out.printf("%d.%s%n", num, task);
            num++;
        }
    }


    public void delete(String num) {
        int listNum = Integer.parseInt(num);
        if (listNum <= toDoList.size()) {
            Task deletedTask = toDoList.remove(listNum - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(deletedTask);
            printNowSize();
        }
    }

    public void done(String num) {
        int listNum = Integer.parseInt(num);
        if (listNum <= toDoList.size()) {
            toDoList.get(listNum - 1).setIsDone(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(toDoList.get(listNum - 1));
        }
    }

    public void printNowSize() {
        if (toDoList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", toDoList.size());
        }
    }
}
