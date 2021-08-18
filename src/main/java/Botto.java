import java.util.Scanner;

public class Botto {
    final String bot = "Botto";
    final String indentation = "    ";

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
            botto.echo(next);
            botto.printDivider();
            next = scanner.nextLine();
        }

        System.out.println(next);
        botto.printDivider();
        botto.bye();
        botto.printDivider();
    }

    private void printDivider() {
        System.out.println(indentation + "------------------------------------------");
    }

    private void greet() {
        String greet = "Hello! I'm " + bot + "\n" + indentation + "What can I do for you?";
        System.out.println(indentation + greet);
    }

    private void echo(String command) {
        System.out.println(indentation + command);
    }

    private void bye() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(indentation + bye);
    }


}
