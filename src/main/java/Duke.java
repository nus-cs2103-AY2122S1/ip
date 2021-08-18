import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // shows the if the Duke chatbot has been activated
    private boolean activated;
    // Line Separator
    private final String SEP_LINE = "____________________________________________________________\n";
    // Standard boot response
    private final String bootMessage =
            SEP_LINE
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nMade by Dr-Octavius\n"
            + SEP_LINE
            + "Hello! I'm Duke\n"
            + "What can I do for you?\n"
            + SEP_LINE;
    // Text Storage
    private final List<String> history = new ArrayList<String>();

    // Duke Constructor
    public Duke() {
        this.activated = true;
    }

    // Checks if Duke Chatbot has been activated
    public boolean isActive() {
        return this.activated;
    }

    // Level-1: Greets user when Duke is initialised
    public void greet() {
        System.out.println(this.bootMessage);
    }

    // Level-1: Echoes Message input from user
    public String echo(String text) {
        return text;
    }

    // Level-1: Exit Message triggered by "bye"
    public String exit() {
        this.activated = false;
        return " Bye. Hope to see you again soon!";
    }

    public String decoder(String text) {
        String res;
        if (text.equals("bye")) {
            res = this.exit();
        } else {
            res = this.echo(text);
        }
        return this.messageWrapper(res);
    }

    public String messageWrapper(String text) {
        return this.SEP_LINE + text + "\n" + SEP_LINE;
    }

    public static void main(String[] args) {
        Scanner scannerObj = new Scanner(System.in);
        Duke chatBotObj = new Duke();
        chatBotObj.greet();
        while (chatBotObj.isActive()) {
            String userInput = scannerObj.nextLine();
            String output = chatBotObj.decoder(userInput);
            System.out.println(output);
        }
    }
}
