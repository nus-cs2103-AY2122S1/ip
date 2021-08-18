import java.util.Scanner;

public class Duke {

    private boolean activated;
    private final String SEP_LINE = "____________________________________________________________\n";
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

    public Duke() {
        this.activated = true;
    }

    public boolean isActive() {
        return this.activated;
    }

    public void boot() {
        System.out.println(this.bootMessage);
    }

    public String echo(String text) {
        return text;
    }

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
        chatBotObj.boot();
        while (chatBotObj.isActive()) {
            String userInput = scannerObj.nextLine();
            String output = chatBotObj.decoder(userInput);
            System.out.println(output);
        }
    }
}
