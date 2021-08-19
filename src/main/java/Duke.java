import java.util.ArrayList;
import java.util.List;

public class Duke {
    protected static List<Task> todoList;

    public static void printLine() {
        System.out.println("--------------------------------------------------");
    }

    public static void printBigIcon() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    public static void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLine();
    }

    public static void main(String[] args) {
        todoList = new ArrayList<>();

        printLine();
        // printBigIcon();
        printHello();

        Command.process();
    }
}
