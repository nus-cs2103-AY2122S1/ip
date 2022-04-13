package duke;


import duke.command.Command;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of tasks to do.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    private boolean hasExit;

    /**
     * Constructs a Duke Chatbot with a data storage file.
     */
    public Duke() {
        try {
            storage = new Storage("data/tasks.txt");
            tasks = new TaskList(storage.load());
            System.out.println(tasks.getListSize());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to user input.
     *
     * @param input User's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        String res = "";
        try {
            Command c = Parser.parse(input, tasks);
            res = c.execute(tasks, storage);
            hasExit = c.isExit();
        } catch (DukeException e) {
            res = e.toString();
        }
        return res;
    }

    /**
     * Checks if the user has given an exit command.
     *
     * @return True if an exit command was give, false otherwise.
     */
    public boolean hasExit() {
        return hasExit;
    }

}
