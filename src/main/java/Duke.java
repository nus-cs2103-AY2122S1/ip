import java.util.Scanner;

/**
 * A chat bot that is capable of recording tasks.
 * It records 3 types of tasks:
 * Todos: Tasks without any date attached.
 * Deadlines: Tasks that need to be done before a specific date.
 * Events: Tasks that start at a specific time and ends at a specific time.
 */
public class Duke {

    /**
     * When run, opens a chat bot that greets the user and echos any user's input. If the input
     * is bye the chat bot is closed.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Ui dukeUi = new Ui();
        dukeUi.start();
    }
}
