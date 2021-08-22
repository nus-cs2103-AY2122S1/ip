import java.util.Scanner;

public class UserInterface {
    Scanner sc;

    UserInterface() {
        this.sc = new Scanner(System.in);
    }

    public String nextCommand() {
        return sc.nextLine();
    }

    public void printInitialGreeting() {
        System.out.println("Hello I'm Duke\n" +
                "What can I do for you?");
    }

    public void printGoodByeGreeting() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }



}
