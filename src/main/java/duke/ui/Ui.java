package duke.ui;

import duke.task.Priority;
import duke.task.Task;
import duke.task.TaskList;

/** A class that manages the UI */
public class Ui {
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\____|_|\\_\\___|\n\n";
    public static final String MESSAGE_GREETING = "Hello from\n" + LOGO;
    public static final String MESSAGE_HELP = "How can I help you?";
    public static final String MESSAGE_ADD_TASK = "Added the following task:\n"
            + "  %1$s\n"
            + "You have %2$d task(s) in the list";
    public static final String MESSAGE_DELETE_TASK = "Deleted the following task:\n"
            + "  %1$s\n"
            + "You have %2$d task(s) in the list";
    public static final String MESSAGE_LIST_TASKS = "Your current task(s):\n"
            + "%1$s";
    public static final String MESSAGE_FOUND_TASKS = "Found the following task(s):\n"
            + "%1$s";
    public static final String MESSAGE_MARK_TASK = "Marked task %1$d as done";
    public static final String MESSAGE_PRIORITIZE_TASK = "Prioritized task %1$d become %2$s";
    public static final String MESSAGE_LOADING_SUCCESS = "Data loaded";
    public static final String MESSAGE_LOADING_FAIL = "Failed to load data";
    public static final String MESSAGE_GOODBYE = "Good bye!";

    private MainWindow mainWindow;

    /**
     * Constructs UI that takes input from stdin and prints to stdout.
     */
    public Ui(MainWindow mw) {
        this.mainWindow = mw;
    }

    public void sayGreet() {
        this.sayInfo(MESSAGE_GREETING);
    }

    public void sayHelp() {
        this.say(MESSAGE_HELP);
    }

    public void sayAddTask(Task t, int numTask) {
        this.say(String.format(MESSAGE_ADD_TASK, t.toString(), numTask));
    }

    public void sayDeleteTask(Task t, int numTask) {
        this.say(String.format(MESSAGE_DELETE_TASK, t.toString(), numTask));
    }

    public void sayMarkDoneTask(int taskNum) {
        this.say(String.format(MESSAGE_MARK_TASK, taskNum));
    }

    public void sayPrioritizeTask(int taskNum, Priority priority) {
        this.say(String.format(MESSAGE_PRIORITIZE_TASK, taskNum, priority));
    }

    public void sayList(TaskList tl) {
        this.say(String.format(MESSAGE_LIST_TASKS, tl.toString()));
    }

    public void sayFound(TaskList tl) {
        this.say(String.format(MESSAGE_FOUND_TASKS, tl.toString()));
    }

    public void sayLoadingSuccess() {
        this.sayInfo(MESSAGE_LOADING_SUCCESS);
    }

    public void sayLoadingFail() {
        this.sayError(MESSAGE_LOADING_FAIL);
    }

    public void sayGoodBye() {
        this.say(MESSAGE_GOODBYE);
    }

    /** Prints what Duke says */
    public void say(String... s) {
        String msg = "Duke: ";

        for (String line : s) {
            msg += line + "\n";
        }

        this.mainWindow.showDukeMessage(msg);
    }

    /** Prints error messages */
    public void sayError(String s) {
        this.mainWindow.showDukeMessage("ERROR: " + s);
    }

    /** Prints info messages */
    public void sayInfo(String s) {
        this.mainWindow.showDukeMessage("INFO: " + s);
    }
}
