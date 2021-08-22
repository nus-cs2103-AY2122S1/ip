package duke.command;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Storage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This class is an implementation of Project duke.command.Duke customised to be named LOTTERY-A
 * that stands for List of Tasks That Eventually Require Your Attention.
 * It is a CLI designed to work as a todolist of sorts.
 *
 * CS2103T ip
 * AY21/22 Semester 1
 *
 * @author Kishendran Vendar Kon (Group G05)
 */
public class Duke {
    /** Arraylist that represents list of Tasks. */
    private TaskList tasks;
    private Storage storage;
    private MyParser parser;
    private Scanner keyboard;
    private boolean isScannerOpen;
    /**
     * Core function of bot that opens scanner and reads user input to decide what to do next.
     */
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

    protected void dukeBye() throws DukeException{
        Ui.showByeMessage();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            Ui.showSavingError();
        }
        isScannerOpen = false;
    }

    protected void dukeList() {
        tasks.list();
    }

    protected void dukeDone(int i) throws DukeException {
        tasks.markAsDone(i);
    }

    protected void dukeDelete(int i) throws DukeException {
        tasks.delete(i);
    }

    protected void dukeTodo(String desc) {
        Todo todo = new Todo(desc);
        tasks.add(todo);
        Ui.showAddTaskMessage(todo.toString(), tasks.size());
    }

    protected void dukeDeadline(String desc, Calendar cal){
        Deadline deadline = new Deadline(desc, cal);
        tasks.add(deadline);
        Ui.showAddTaskMessage(deadline.toString(), tasks.size());
    }

    protected void dukeEvent(String desc, Calendar cal){
        Event event = new Event(desc, cal);
        tasks.add(event);
        Ui.showAddTaskMessage(event.toString(), tasks.size());
    }

    public static void main(String[] args) {
        new Duke("duke.txt").runDuke();
    }
}
