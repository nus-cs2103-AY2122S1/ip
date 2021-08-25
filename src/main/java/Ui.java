import java.util.Scanner;

// Ui: deals with interactions with the user
public class Ui {

    private final String lines = "--------------------------------------------------------------------------------------------";


    public Ui() {
    }


    public void reply(String dukeReply) {
        System.out.println(dukeReply);
    }

    public void showLoadingError(String message) {
        this.reply(message);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public String readCommand() {
        System.out.println("\nInput:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printReplyOpening() {
        System.out.println(lines);
        System.out.println("Output: \n");
    }

    public void printReplyClosing() {
        System.out.println(lines);
    }

    public void showClosingMessage() {
        System.out.println("Good bye, see you soon!");
    }
}
