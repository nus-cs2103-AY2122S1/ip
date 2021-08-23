import java.util.Scanner;

public class Ui {
    private Scanner s;

    public Ui() {
        this.s = new Scanner(System.in);
        printIntro();
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
    }

    public String readCommand() {
        return s.nextLine().trim();
    }

    public void end() {
        System.out.println("    ______________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ______________________________________");
        s.close();
    }

}
