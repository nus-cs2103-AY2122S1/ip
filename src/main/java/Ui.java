import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void hello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getUserInput() {
        return sc.nextLine();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
