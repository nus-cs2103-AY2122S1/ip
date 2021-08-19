import main.java.DukeMessages;
import main.java.DukeEngine;

public class Duke {

    public static void main(String[] args) {
        DukeMessages messages = new DukeMessages();
        DukeEngine engine = new DukeEngine();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }
}
