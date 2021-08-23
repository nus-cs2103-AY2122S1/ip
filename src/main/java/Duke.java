package main.java;

public class Duke {

    /**
     * Initialises app.
     * @param args empty args.
     */
    public static void main(String[] args) {
        DukeMessages messages = new DukeMessages();
        DukeEngine engine = new DukeEngine();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }
}
