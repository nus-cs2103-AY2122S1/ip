import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    protected ArrayList<Task> toDoList;

    public Duke() {
        this.toDoList = new ArrayList<>(100);
    }

    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.greeting();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                scanner.close();
                duke.bye();
                break;
            }

            try {
                duke.runCommand(command);
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
            add(command.substring(5), TaskType.TODO);
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            add(command.substring(9), TaskType.DEADLINE);
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            add(command.substring(6), TaskType.EVENT);
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

    public void add(String description, TaskType taskType) {
        if (toDoList.size() < 100) {

            System.out.println("Got it. I've added this task:");

            Task task = new Task(description);

            switch (taskType) {
                case TODO: {
                    task = new Todo(description);
                    break;
                }
                case DEADLINE: {
                    String newDescription = description.substring(0, description.indexOf(" /by "));
                    String by = description.substring(description.indexOf("/by ") + 4);
                    task = new Deadline(newDescription, by);
                    break;
                }
                case EVENT: {
                    String newDescription = description.substring(0, description.indexOf(" /at "));
                    String at = description.substring(description.indexOf("/at ") + 4);
                    task = new Event(newDescription, at);
                    break;
                }
            }

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

    public void printNowSize() {
        if (toDoList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", toDoList.size());
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
}
