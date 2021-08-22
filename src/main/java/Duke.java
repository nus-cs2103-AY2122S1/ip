package main.java;

public class Duke {

    public static void main(String[] args) {
        DukeMessages messages = new DukeMessages();
        DukeEngine engine = new DukeEngine();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }
}
