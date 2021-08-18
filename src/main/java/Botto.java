import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Botto {
    final static String bot = "Botto";
    final static String indentation = "   ";
    final static String[] commands = {"list", "done", "todo", "deadline", "event", "delete", "bye"};

    // used enum here
    enum TaskType {
        TODO, DEADLINE, EVENT
    }

    private List<Task> list = new LinkedList<>();

    public static void main(String[] args) {
        Botto botto = new Botto();
        Scanner scanner = new Scanner(System.in);

        botto.printDivider();
        botto.greet();
        botto.printDivider();

        String next = scanner.nextLine();

        while(!next.equals("bye")) {
            botto.printDivider();

            try {
                switch (findCommand(next)) {
                    case "list":
                        botto.printList();
                        break;
                    case "done":
                        botto.markAsDone(next.replaceAll("\\D+", ""));
                        break;
                    case "todo":
                        botto.add(TaskType.TODO, next);
                        break;
                    case "deadline":
                        botto.add(TaskType.DEADLINE, next);
                        break;
                    case "event":
                        botto.add(TaskType.EVENT, next);
                        break;
                    case "delete":
                        botto.delete(next.replaceAll("\\D+", ""));
                        break;
                }
            } catch (DukeException e) {
                System.out.println(indentation + e.getMessage());
            }

            botto.printDivider();
            next = scanner.nextLine();
        }

        botto.printDivider();
        botto.bye();
        botto.printDivider();
    }

    private static String findCommand(String command) throws DukeException {
        for(String x: commands) {
            if(command.startsWith(x)) {
                return x;
            }
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void printDivider() {
        System.out.println(indentation + "------------------------------");
    }

    private void greet() {
        String greet = "Hello! I'm " + bot + ".\n"
                + indentation + "What can I do for you?";

        System.out.println(indentation + greet);
    }

    private void printList() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i ++) {
            Task task = this.list.get(i);
            System.out.println(indentation + (i + 1) + ". " + task);
        }
    }

    private void add(TaskType type, String next) throws DukeException {
        String description;

        try {
            description = next.split(" ", 2)[1];
        } catch (Exception e) {
            String task = type == TaskType.TODO ? "a todo"
                    : type == TaskType.DEADLINE? "a deadline"
                    : "an event";
            throw new DukeException("☹ OOPS!!! The description of " + task + " cannot be empty.");
        }

        String[] array = description.split("/", 2);
        Task task;

        try {
            task = type == TaskType.TODO ? new Todo(description)
                    : type == TaskType.DEADLINE ? new Deadline(array[0].substring(0, array[0].length() - 1), array[1].substring(3))
                    : new Event(array[0].substring(0, array[0].length() - 1), array[1].substring(3));
        } catch (Exception e) {
            String message = "☹ OOPS!!! The command is in wrong format.\n" +
                    indentation + "Please enter in this format: [deadline/event] [title] /[by/at] [date]";
            throw new DukeException(message);
        }

        this.list.add(task);
        System.out.println(indentation + "Got it! I've added this task:\n"
                + indentation + "  " + task + "\n"
                + indentation + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void markAsDone(String integer) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(integer) - 1;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! You have to specify the task.");
        }

        Task subject;

        try {
            subject = this.list.get(index);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The task does not exist.");
        }
        subject.markAsDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + "  " + subject);
    }

    private void delete(String integer) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(integer) - 1;
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! You have to specify which task to delete.");
        }

        Task subject;

        try {
            subject = this.list.get(index);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The task does not exist.");
        }

        this.list.remove(index);
        System.out.println(indentation + "Noted. I've removed this task:");
        System.out.println(indentation + "  " + subject);
        System.out.println(indentation + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(indentation + bye);
    }


}
