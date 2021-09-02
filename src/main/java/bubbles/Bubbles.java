package bubbles;

import bubbles.tasks.TaskList;
import bubbles.util.Message;
import bubbles.util.Storage;
import bubbles.util.Ui;

/**
 * Bubbles - a Personal Assistant Chatbot that
 * helps a user to keep track of various tasks,
 * namely ToDos, Deadlines and Events.
 */
public class Bubbles {
    private String FILEPATH = "data/bubbles.txt";
    private Storage storage;
    private Ui ui;

    private TaskList taskList;

    /**
     * A public constructor to initialize the Bubbles bot.
     */
    public Bubbles() {
        storage = new Storage();
        storage.loadFile(FILEPATH);

        taskList = storage.getTaskList();

        ui = new Ui(storage);
    }

    /**
     * Main method for the Bubbles bot.
     * Creates a new Bubbles object and gets the bot running.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new Bubbles().run();
    }

    private void run() {
        System.out.println(Message.separateMessage(Message.WELCOME.toString()));

        String input;
        while (!(input = ui.readCommand()).equals("bye")) {
            System.out.println(taskList.taskListener(input));
        }

        ui.exit();
    }

    public String getResponse(String input) {
        if (input.equals("bye")) {
            storage.writeTasks();
            return Message.EXIT.toString();
        }

        String message;
        message = taskList.taskListener(input);

        return message;
    }
}
