package duke.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import duke.task.*;

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
    private TaskList tasks;
    /** Deals with loading and saving tasks from and to file. */
    private Storage storage;

    private MainWindow mainWindow;

    /** Default Constructor. */
    public Duke(String txtFile) {
        try {
            storage = new Storage(txtFile);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            mainWindow.showLoadingError();
        }
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        mainWindow.showWelcomeMessage();
    }

    /** Saves task to file then exits. */
    protected void dukeBye() throws DukeException {
        mainWindow.showByeMessage();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            mainWindow.showSavingError();
        }
    }

    /** Lists tasks. */
    protected void dukeList() {
        mainWindow.showListOfTasks(tasks.list());
    }

    /**
     * Marks task at index i as done.
     *
     * @param i Index of tasks to be marked.
     * @throws DukeException if no such tasks exist.
     */
    protected void dukeDone(int i) throws DukeException {
        tasks.markAsDone(i);
        mainWindow.showMarkAsDoneMessage(tasks.getStringDes(i));
    }

    /**
     * Deletes task at index i.
     *
     * @param i Index of tasks to be deleted.
     * @throws DukeException if no such tasks exist.
     */
    protected void dukeDelete(int i) throws DukeException {
        Task t = tasks.delete(i);
        mainWindow.showDeleteTaskMessage(t.toString(), tasks.size());
    }

    /**
     * Creates and adds Todo Object.
     *
     * @param desc Description of Todo Object.
     */
    protected void dukeTodo(String desc) {
        Todo todo = new Todo(desc);
        tasks.add(todo);
        mainWindow.showAddTaskMessage(todo.toString(), tasks.size());
    }

    /**
     * Creates and adds Deadline Object.
     *
     * @param desc Description of Deadline Object.
     * @param cal Calendar set to dueBy date of Deadline.
     */
    protected void dukeDeadline(String desc, Calendar cal) {
        Deadline deadline = new Deadline(desc, cal);
        tasks.add(deadline);
        mainWindow.showAddTaskMessage(deadline.toString(), tasks.size());
    }

    /**
     * Creates and adds Event Object.
     *
     * @param desc Description of Event Object.
     * @param cal Calendar set to time of Event.
     */
    protected void dukeEvent(String desc, Calendar cal) {
        Event event = new Event(desc, cal);
        tasks.add(event);
        mainWindow.showAddTaskMessage(event.toString(), tasks.size());
    }

    protected void dukeFind(String desc) {
        mainWindow.showListOfTasks(tasks.find(desc));
    }

}
