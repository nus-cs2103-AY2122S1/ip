package duke.command;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Storage;
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
    private TaskList tasks;
    /** Deals with loading and saving tasks from and to file. */
    private Storage storage;
    /** Deals with making sense of user commands. */
    private MyParser parser;
    /** Reads user input. */
    private Scanner keyboard;
    private boolean isScannerOpen;

    /** Default Constructor. */
    public Duke(String txtFile) {
        parser = new MyParser();
        try {
            storage = new Storage(txtFile);
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            Ui.showLoadingError();
        }
        keyboard = new Scanner(System.in);
        isScannerOpen = true;
    }

    /** Launches Duke */
    public void runDuke() {
        Ui.showWelcomeMessage();
        while (isScannerOpen) {
            String command = keyboard.next();
            String description = keyboard.nextLine();

            try {
                parser.parse(command, description, this);
            } catch (DukeException e) {
                System.out.print(e);
            }

        }
    }

    /** Saves task to file then exits. */
    protected void dukeBye() throws DukeException {
        Ui.showByeMessage();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            Ui.showSavingError();
        }
        isScannerOpen = false;
    }

    /** Lists tasks. */
    protected void dukeList() {
        tasks.list();
    }

    /**
     * Marks task at index i as done.
     *
     * @param i Index of tasks to be marked.
     * @throws DukeException if no such tasks exist.
     */
    protected void dukeDone(int i) throws DukeException {
        tasks.markAsDone(i);
    }

    /**
     * Deletes task at index i.
     *
     * @param i Index of tasks to be deleted.
     * @throws DukeException if no such tasks exist.
     */
    protected void dukeDelete(int i) throws DukeException {
        tasks.delete(i);
    }

    /**
     * Creates and adds Todo Object.
     *
     * @param desc Description of Todo Object.
     */
    protected void dukeTodo(String desc) {
        Todo todo = new Todo(desc);
        tasks.add(todo);
        Ui.showAddTaskMessage(todo.toString(), tasks.size());
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
        Ui.showAddTaskMessage(deadline.toString(), tasks.size());
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
        Ui.showAddTaskMessage(event.toString(), tasks.size());
    }

    protected void dukeFind(String desc) {
        tasks.find(desc);
    }

    public static void main(String[] args) {
        new Duke("duke.txt").runDuke();
    }
}
