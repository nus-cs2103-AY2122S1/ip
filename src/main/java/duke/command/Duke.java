package duke.command;

import java.io.IOException;
import java.util.Calendar;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class is an implementation of Duke customised to be named LOTTERY-A
 * that stands for List of Tasks That Eventually Require Your Attention.
 * It is a CLI designed to work as a todolist.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Duke {
    /** Contains task list. */
    private TaskList listOfTasks;

    /** Deals with loading and saving tasks from and to file. */
    private Storage storage;

    /** Deals with GUI. */
    private MainWindow mainWindow;

    /**
     * Default Constructor.
     */
    public Duke(String txtFile) {
        try {
            storage = new Storage(txtFile);
            listOfTasks = new TaskList(storage.load());
        } catch (IOException e) {
            mainWindow.showLoadingError();
        }
    }

    /**
     * Sets the mainWindow of this instance of duke.
     *
     * @param mainWindow GUI viewController.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.showWelcomeMessage();
    }

    /** Saves task to file then exits. */
    protected void executeByeCommand() {
        mainWindow.showByeMessage();
        executeSave();
    }

    /** Lists tasks. */
    protected void executeListCommand() {
        mainWindow.showListOfTasks(listOfTasks.list());
    }

    /**
     * Marks task at index i as done.
     *
     * @param i Index of tasks to be marked.
     * @throws DukeException if no such tasks exist.
     */
    protected void executeDoneCommand(int i) throws DukeException {
        listOfTasks.markAsDone(i);
        mainWindow.showMarkAsDoneMessage(listOfTasks.getStringDes(i));
        executeSave();
    }

    /**
     * Deletes task at index i.
     *
     * @param i Index of tasks to be deleted.
     * @throws DukeException if no such tasks exist.
     */
    protected void executeDeleteCommand(int i) throws DukeException {
        Task t = listOfTasks.delete(i);
        mainWindow.showDeleteTaskMessage(t.toString(), listOfTasks.size());
        executeSave();
    }

    /**
     * Creates and adds Todo Object.
     *
     * @param desc Description of Todo Object.
     */
    protected void executeTodoCommand(String desc) {
        Todo todo = new Todo(desc);
        listOfTasks.add(todo);
        int currentListSize = listOfTasks.size();
        assert currentListSize > 0 : "tasks should never be empty after adding task";
        mainWindow.showAddTaskMessage(todo.toString(), currentListSize);
        executeSave();
    }

    /**
     * Creates and adds Deadline Object.
     *
     * @param desc Description of Deadline Object.
     * @param cal Calendar set to dueBy date of Deadline.
     */
    protected void executeDeadlineCommand(String desc, Calendar cal) {
        Deadline deadline = new Deadline(desc, cal);
        listOfTasks.add(deadline);
        int currentListSize = listOfTasks.size();
        assert currentListSize > 0 : "tasks should never be empty after adding task";
        mainWindow.showAddTaskMessage(deadline.toString(), currentListSize);
        executeSave();

    }

    /**
     * Creates and adds Event Object.
     *
     * @param desc Description of Event Object.
     * @param cal Calendar set to time of Event.
     */
    protected void executeEventCommand(String desc, Calendar cal) {
        Event event = new Event(desc, cal);
        listOfTasks.add(event);
        int currentListSize = listOfTasks.size();
        assert currentListSize > 0 : "tasks should never be empty after adding task";
        mainWindow.showAddTaskMessage(event.toString(), currentListSize);
        executeSave();
    }

    /**
     * Executes find command
     *
     * @param desc String being searched for.
     */
    protected void executeFindCommand(String desc) {
        mainWindow.showListOfTasks(listOfTasks.find(desc));
    }

    /**
     * Edits the description of task at index i
     *
     * @param desc New description.
     * @param i Index of tasks to be edited.
     */
    protected void executeEditDescriptionCommand(String desc, int i) throws DukeException {
        listOfTasks.editDescription(desc, i);
        executeSave();
        mainWindow.showEditTaskMessage(listOfTasks.getStringDes(i));
    }

    /**
     * Edits the date and time of task at index i
     *
     * @param cal New date and time.
     * @param i Index of tasks to be edited.
     */
    protected void executeEditTimeCommand(Calendar cal, int i) throws DukeException {
        listOfTasks.editTime(cal, i);
        executeSave();
        mainWindow.showEditTaskMessage(listOfTasks.getStringDes(i));
    }

    /**
     * Saves info in listOfTasks to storage.
     */
    private void executeSave() {
        try {
            storage.save(listOfTasks);
        } catch (IOException e) {
            mainWindow.showSavingError();
        }
    }

}
