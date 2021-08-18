import java.util.Scanner;

public class Duke {
    private boolean isOpen;
    private String dukeLogo;

    private Duke() { // constructor for Duke chat bot object
        this.dukeLogo = "      ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n";
    }

    private void openDukeChatBot() {
        this.isOpen = true;
        System.out.println(
                "\t____________________________________________________________\n" +
                this.dukeLogo +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    private void closeDukeChatBot() {
        this.isOpen = false;
        System.out.println(
                "\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________\n"
        );
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.openDukeChatBot();
        Scanner sc = new Scanner(System.in);
        while (d.isOpen) {
            String userInput = sc.next();
            if (userInput.equals("bye")) {
                d.closeDukeChatBot();
                sc.close();
            } else {
                System.out.println(userInput);
            }

        }
    }
}
