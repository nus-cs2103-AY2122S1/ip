import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Botto {
    final String bot = "Botto";
    final String indentation = "   ";

    private List<String> list = new LinkedList<>();

    public static void main(String[] args) {
        Botto botto = new Botto();
        Scanner scanner = new Scanner(System.in);

        botto.printDivider();
        botto.greet();
        botto.printDivider();

        String next = scanner.nextLine();

        while(!next.equals("bye")) {
            System.out.println(next);
            botto.printDivider();
            switch (next) {
                case ("list"):
                    botto.printList();
                    break;
                default:
                    botto.add(next);
                    break;
            }
            botto.printDivider();
            next = scanner.nextLine();
        }

        System.out.println(next);
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
        for(int i = 0; i < this.list.size(); i ++) {
            System.out.println(indentation + (i + 1) + ": " + this.list.get(i));
        }
    }

    private void add(String task) {
        this.list.add(task);
        System.out.println(indentation + "added: " + task);
    }

    private void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(indentation + bye);
    }


}
