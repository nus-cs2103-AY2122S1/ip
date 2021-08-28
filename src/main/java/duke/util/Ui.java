package duke.util;

// import duke packages
import duke.DukeException;
import duke.task.TaskList;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private String logo;
    private String line;

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
     */
    public void showLoadingError() {
        System.out.println(line);
        System.out.println("oh no.. i seem to have trouble loading the file");
        System.out.println(line);
    }

    /**
     * Displays a list of tasks.
     *
     * @param cmdList Task list to be displayed.
     */
    public void printList(TaskList cmdList) {
        System.out.println(line);
        for (int i = 0; i < cmdList.getLength(); i++) {
            System.out.println((i + 1) + "." + cmdList.get(i));
        }
        System.out.println(line);
    }

    /**
     * Displays successful completion of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is completed.
     */
    public void printTaskDone(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("congrats on completing this task! :");
        System.out.println("  " + cmdList.get(i));
        System.out.println(line);
    }

    /**
     * Displays successful addition of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is added.
     */
    public void printTaskAdded(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("noted! ive added this task:");
        System.out.println("  " + cmdList.get(i));
        System.out.println("now you have " + cmdList.getLength() + " tasks in your list");
        System.out.println(line);
    }

    /**
     * Displays successful removal of task.
     *
     * @param cmdList List containing tasks.
     * @param i Index of task that is removed.
     */
    public void printTaskDeleted(TaskList cmdList, int i) {
        System.out.println(line);
        System.out.println("ok, ive deleted this task :");
        System.out.println("  " + cmdList.get(i));
        System.out.println("now you have " + (cmdList.getLength() - 1) + " tasks in your list");
        System.out.println(line);
    }

    /**
     * Displays welcome message.
     */
    public void sayHello() {
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("hello! i'm calico ☺");
        System.out.println("how can i assist you?");
        System.out.println(line);
    }

    /**
     * Displays DukeException message.
     *
     * @param e DukeException to be displayed.
     */
    public void showDukeError(DukeException e) {
        System.out.println(line);
        System.out.println(e);
        System.out.println(line);
    }

    /**
     * Displays goodbye message.
     */
    public void sayGoodbye() {
        System.out.println(line);
        System.out.println("bye friend!");
        System.out.println(line);
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
