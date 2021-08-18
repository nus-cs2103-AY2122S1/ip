import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    // shows the if the Duke chatbot has been activated
    private boolean activated;
    // Line Separator
    private static String SEP_LINE = "____________________________________________________________\n";
    // Standard boot response
    private final String bootMessage =
            SEP_LINE
            .concat(
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\nMade by Dr-Octavius\n")
            .concat(SEP_LINE)
            .concat(
            "Hello! I'm Duke\n"
            + "What can I do for you?\n")
            .concat(SEP_LINE);
    // Text Storage
    private final List<String> storage = new ArrayList<String>();

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
        // Level-2: adds text to storage
        this.storage.add(text);
        return "added: ".concat(text);
    }

    // Level-1: Exit Message triggered by "bye"
    public String exit() {
        this.activated = false;
        return " Bye. Hope to see you again soon!";
    }

    // Level-2: Lists all items in storage
    public String list() {
        String out = "";
        for (int i = 0; i < this.storage.size(); i++) {
            if (i != 0) out = out.concat("\n");
            out = out.concat((i+1) + ". ");
            out = out.concat(this.storage.get(i));
        }
        return out;
    }

    // decodes input from user and invokes the relevant method call
    public String decoder(String text) {
        String res;
        if (text.equals("bye")) {
            res = this.exit();
        } else if (text.equals("list")) {
            res = this.list();
        } else {
            res = this.echo(text);
        }
        return this.messageWrapper(res);
    }

    public String messageWrapper(String text) {
        return this.SEP_LINE.concat(text).concat("\n").concat(SEP_LINE);
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
