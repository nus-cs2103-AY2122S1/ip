package duke;

/**
 * A chat bot that is capable of recording tasks.
 * It records 3 types of tasks:
 * Todos: Tasks without any date attached.
 * Deadlines: Tasks that need to be done before a specific date.
 * Events: Tasks that start at a specific time and ends at a specific time.
 */
public class Duke {

    /**
     * Runs Duke, the chat bot.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {
        Ui dukeUi = new Ui();
        dukeUi.start();
    }
}
