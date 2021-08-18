import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        displayLine();
    }

    public void sayBye() {
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }

    public void echo(String str) {
        displayLine();
        System.out.println(str);
        displayLine();
    }

    public void displayLine() {
        System.out.println("____________________________________________________________");
    }

    public String readInput() {
        return sc.nextLine();
    }

}
