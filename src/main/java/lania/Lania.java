package lania;

import lania.exception.LaniaException;
import lania.task.*;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;

/**
 * Represents the chatbot Lania that helps manage your tasks.
 */
public class Lania {

    /** Contains the task list */
    private TaskList tasks;
    /**  Deals with interactions with the user */
    private Ui ui;
    /** Deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /**
     * Constructor for the Lania object.
     *
     * @param filePath The location of the file in which data is stored
     */
    public Lania(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Store user input in the task list and display a message.
     *
     * @param t The task provided by the user.
     */
    public void update(Task t) {
        tasks.update(t);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
        ui.showUpdateMessage(tasks, t);
    }

    /**
     * Completes the given task number.
     *
     * @param i The task number to be completed.
     */
    public void complete(int i) {
        tasks.complete(i);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
        ui.showCompleteMessage(tasks, i);
    }

    /**
     * Removes given task number.
     *
     * @param i The task number to be completed.
     */
    public void remove(int i) {
        ui.showRemoveMessage(tasks, tasks.remove(i));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError();
        }
    }

    /**
     * Finds tasks that matches the keyword in the task list.
     *
     * @param s The keyword to match.
     */
    public void find(String s) {
        TaskList temp = tasks.find(s);
        ui.showListMessage(temp);
    }

    /**
     * Main part of the program. First, it tries to load the file
     * if it exists and display its contents. It then copies the
     * contents over into a TaskList.
     *
     * After greeting the user, the
     * program continuously waits for input unless the command 'bye'
     * is given which closes exits the program.
     *
     */
    public void run() {
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
        ui.showListMessage(tasks);
        ui.showGreetingMessage();
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        String command = new Parser().parseCommand(input);
        while(!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    ui.showListMessage(tasks);
                } else if (command.equals("find")) {
                    find(new Parser().parseTaskDescription(input));
                } else if (command.equals("done")) {
                    complete(new Parser().getIndex(input));
                } else if (command.equals("delete")) {
                    remove(new Parser().getIndex(input));
                } else {
                    if (command.equals("todo")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        update(new Todo(taskDescription));
                    } else if (command.equals("deadline")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        String[] task = new Parser().parseDeadline(taskDescription);
                        update(new Deadline(task[0], task[1]));
                    } else if (command.equals("event")) {
                        String taskDescription = new Parser().parseTaskDescription(input);
                        String[] task = new Parser().parseEvent(taskDescription);
                        update(new Event(task[0], task[1]));
                    } else {
                        throw new LaniaException("Sorry, but Lania does not know what that means.");
                    }
                }
            } catch (LaniaException e) {
                ui.showLaniaException(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeException();
            } finally {
                input = s.nextLine();
                command = new Parser().parseCommand(input);
            }
        }
        s.close();
        ui.showExitMessage();
    }

    /**
     * Lania object is created and starts running.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania("data/lania.txt");
        lania.run();
    }
}
