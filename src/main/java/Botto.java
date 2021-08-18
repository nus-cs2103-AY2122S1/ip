import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Botto {
    final String bot = "Botto";
    final String indentation = "   ";

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

            if(next.equals("list")) {
                botto.printList();
            } else if (next.startsWith("done")) {
                String integer = next.replaceAll("\\D+", "");
                botto.markAsDone(Integer.parseInt(integer) - 1);
            } else {
                botto.add(next);
            }

            botto.printDivider();
            next = scanner.nextLine();
        }

        botto.printDivider();
        botto.bye();
        botto.printDivider();
    }

    private void printDivider() {
        System.out.println(indentation + "------------------------------");
    }

    private void greet() {
        String greet = "Hello! I'm " + bot + "\n"
                + indentation + "What can I do for you?";

        System.out.println(indentation + greet);
    }

    private void printList() {
        System.out.println(indentation + "Here are the tasks in your list:");
        for(int i = 0; i < this.list.size(); i ++) {
            Task task = this.list.get(i);
            System.out.println(indentation + (i + 1) + ". [" + task.getStatusIcon() + "] "
                    + task.getDescription());
        }
    }

    private void add(String description) {
        Task task = new Task(description);
        this.list.add(task);
        System.out.println(indentation + "added: " + description);
    }

    private void markAsDone(int index) {
        Task subject = this.list.get(index);
        subject.markAsDone();
        System.out.println(indentation + "Nice! I've marked this task as done: ");
        System.out.println(indentation + "  [" + subject.getStatusIcon() + "] " + subject.getDescription());
    }

    private void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(indentation + bye);
    }


}
