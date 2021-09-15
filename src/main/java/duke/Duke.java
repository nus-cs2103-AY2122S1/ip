package duke;

import java.util.ArrayList;

/**
 * The Duke bot.
 *
 * @author Luo Zhijie
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private MainWindow main;

    /**
     * Constructor for Duke bot
     *
     * @param filePath Path to duke file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.convertFileToTaskList();
        ui = new Ui();
    }

    private void initializeReminder() {
        ui.setMainWindow(main);
        ui.addReminder(tasks);
    }

//    public void addReminder(Task task) {
//        TaskList taskList = new TaskList(new ArrayList<Task>());
//        taskList.add(task);
//        ui.addReminder(taskList);
//    }

    public Duke() {
    }

    public void setMain(MainWindow main) {
        this.main = main;
        initializeReminder();
    }

    /**
     * Greets the user at the start.
     *
     * @return Greeting message.
     */
    public String greeting() {
        return ui.greet();
    }

    /**
     * Says bye to user.
     *
     * @return Message that ends the duke conversation.
     */
    public String bye() {
        return ui.bye();
    }

    /**
     * Gets response from Duke.
     *
     * @param input Input string from user.
     */
    public String getResponse(String input) {
        try {
            tasks = storage.convertFileToTaskList();
        } catch (Exception e) {
            return e.getMessage();
        }
        Parser parser = new Parser(" ");
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
