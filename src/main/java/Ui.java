import java.util.Scanner;

public class Ui {
    private Scanner s;

    public Ui() {
        s = new Scanner(System.in);
        printIntro();
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        showLine();
    }

    public static void showLine() {
        System.out.println("__________________________________________");
    }

    public void showError(String error) {
        System.out.println("    ______________________________________");
        System.out.printf("     %s\n", error);
        System.out.println("    ______________________________________");
    }

    public String readCommand() {
        return s.nextLine().trim();
    }

    public void end() {
        s.close();
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
    }
}
