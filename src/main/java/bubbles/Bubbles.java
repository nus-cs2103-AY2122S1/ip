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
    private String filePath = "data/bubbles.txt";
    private Storage storage;
    private Ui ui;

    private TaskList taskList;

    /**
     * A public constructor to initialize the Bubbles bot.
     */
    public Bubbles() {
        storage = new Storage();
        storage.loadFile(filePath);

        taskList = storage.getTaskList();

        ui = new Ui(storage);
    }

    /**
     * Handles the command from the user and returns the message that is the result
     * of executing the command.
     *
     * @param input Input command from the user.
     * @return Response message from the command, could be an error message.
     */
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
