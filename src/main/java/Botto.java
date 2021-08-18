import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Botto {
    final static String bot = "Botto";
    final static String indentation = "   ";
    final static String[] commands = {"list", "done", "todo", "deadline", "event", "bye"};

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
            String command = findCommand(next);

            switch(command) {
                case "list":
                    botto.printList();
                    break;
                case "done":
                    String integer = next.replaceAll("\\D+", "");
                    botto.markAsDone(Integer.parseInt(integer) - 1);
                    break;
                case "todo":
                    botto.add(TaskType.TODO, next.split(" ", 2)[1]);
                    break;
                case "deadline":
                    botto.add(TaskType.DEADLINE, next.split(" ", 2)[1]);
                    break;
                case "event":
                    botto.add(TaskType.EVENT, next.split(" ", 2)[1]);
                    break;
            }

            botto.printDivider();
            next = scanner.nextLine();
        }

        botto.printDivider();
        botto.bye();
        botto.printDivider();
    }

    private static String findCommand(String command) {
        for(String x: commands) {
            if(command.startsWith(x)) {
                return x;
            }
        }
        return "";
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

    private void add(TaskType type, String description) {
        String[] array = description.split("/", 2);

        Task task = type == TaskType.TODO ? new Todo(description)
                : type == TaskType.DEADLINE ? new Deadline(array[0].substring(0,array[0].length()-1), array[1].substring(3))
                : new Event(array[0].substring(0,array[0].length()-1), array[1].substring(3));

        this.list.add(task);
        System.out.println(indentation + "Got it! I've added this task:\n"
                + indentation + "  " + task + "\n"
                + indentation + "Now you have " + this.list.size() + " tasks in the list.");
    }

    private void markAsDone(int index) {
        Task subject = this.list.get(index);
        subject.markAsDone();
        System.out.println(indentation + "Nice! I've marked this task as done:");
        System.out.println(indentation + "  " + subject);
    }

    private void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(indentation + bye);
    }


}
