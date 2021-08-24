import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printReply("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void printReply(String string) {
        System.out.println(new Reply(string));
    }

    public void showLoadingError(String fileName) {
        printReply(fileName + " not found. File has been created.");
    }

    public String nextLine() {
        return scanner.nextLine();
    }
}
