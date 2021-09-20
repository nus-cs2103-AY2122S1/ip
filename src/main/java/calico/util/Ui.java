package calico.util;

// import java packages
import java.util.ArrayList;

// import duke packages
import calico.CalicoException;
import calico.task.Task;
import calico.task.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private String logo;
    private String line;
    private String newline = "\n";

    /**
     * Creates an Ui.
     *
     * @param logo Logo of the chatbot.
     * @param line Line shown in output.
     */
    public Ui(String logo, String line) {
        this.logo = logo;
        this.line = line;
    }

    /**
     * Creates an Ui with a default logo and line.
     */
    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.line = "===========================";
    }

    /**
     * Displays an error in loading file.
     *
     * @return Output of loading error.
     */
    public String showLoadingError() {
        String message = "";
        message += line + newline;
        message += "oh no.. i seem to have trouble loading the file" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays a list of tasks.
     *
     * @param cmdList Task list to be displayed.
     * @return String representation of task list.
     */
    public String showList(TaskList cmdList) {
        String message = "";
        message += line + newline;
        for (int i = 0; i < cmdList.getLength(); i++) {
            message += (i + 1) + "." + cmdList.get(i) + newline;
        }
        message += line + newline;
        return message;
    }

    /**
     * Displays successful completion of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is completed.
     * @return Output of completing task.
     */
    public String showTaskDone(TaskList cmdList, int i) {
        String message = "";
        message += line + newline;
        message += "congrats on completing this task! :" + newline;
        message += "  " + cmdList.get(i) + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays successful addition of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is added.
     * @return Output of adding task.
     */
    public String showTaskAdded(TaskList cmdList, int i) {
        String message = "";
        message += line + newline;
        message += "noted! ive added this task:" + newline;
        message += "  " + cmdList.get(i) + newline;
        message += "now you have " + cmdList.getLength() + " tasks in your list" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays successful removal of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is removed.
     * @return Output of deleting task.
     */
    public String showTaskDeleted(TaskList cmdList, int i) {
        String message = "";
        message += line + newline;
        message += "ok, ive deleted this task :" + newline;
        message += "  " + cmdList.get(i) + newline;
        message += "now you have " + (cmdList.getLength() - 1) + " tasks in your list" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays welcome message.
     *
     * @return Welcome message.
     */
    public String sayHello() {
        String message = "";
        message += "hello! i'm calico :)\n" + newline;
        message += "how can i assist you?" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays DukeException message.
     *
     * @param e DukeException to be displayed.
     * @return DukeException message
     */
    public String showDukeError(CalicoException e) {
        String message = "";
        message += line + newline;
        message += e + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays goodbye message.
     *
     * @return Goodbye message.
     */
    public String sayGoodbye() {
        String message = "";
        message += line + newline;
        message += "bye friend!" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Displays successful query of matching tasks.
     *
     * @param results Successful matches.
     * @return Output of matching tasks.
     */
    public String showMatchingTasks(ArrayList<Task> results) {
        String message = "";
        message += line + newline;
        message += "ive found some matches for your search query:" + newline;
        for (int i = 0; i < results.size(); i++) {
            message += (i + 1) + "." + results.get(i) + newline;
        }
        message += line + newline;
        return message;
    }

    /**
     * Displays no matches found
     *
     * @return Output of no matches.
     */
    public String showNoMatch() {
        String message = "";
        message += line + newline;
        message += "im sorry i couldn't find any matches :(" + newline;
        message += line + newline;
        return message;
    }

    /**
     * Converts Ui to string format.
     *
     * @return Ui as a string.
     */
    @Override
    public String toString() {
        return "handles user interface";
    }
}
