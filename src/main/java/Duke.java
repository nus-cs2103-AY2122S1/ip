import java.util.Scanner;
public class Duke {
    private String command;

    private Duke(String command) {
        this.command = command;
    }

    public void commanding() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if (s.equals("bye")) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Okay then! I hope to see you again soon master!");
            System.out.println("-------------------------------------------------------------------");
        } else {
            System.out.println("-------------------------------------------------------------------");
            System.out.println(s);
            System.out.println("-------------------------------------------------------------------");
            commanding();
        }
        scan.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello from\n" + logo + "How may I help you today master?\n");
        System.out.println("-------------------------------------------------------------------");

        Duke chatbot = new Duke("Servant");
        chatbot.commanding();
    }
}
