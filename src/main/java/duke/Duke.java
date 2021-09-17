package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.DateTimeException;

/**
 * Duke is a task manager using CLI.
 *
 * @author Samuel Lau
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ArrayList<Task> list;
    private Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath filePath is the path of the text file to be loaded by Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            list = tasks.list;
        } catch (DukeException e) {
            ui.sayError(e);
            tasks = new TaskList();
            list = tasks.list;
        }
    }

    /**
     * Runs Duke.
     */
    public void run() {
        ui.sayHi();
        Scanner s = new Scanner(System.in);
        String command = s.nextLine();
        while(!command.equals("bye")) {
            try {
                try {
                    switch (Parser.parseCommand(command)) {
                        case "list":
                            ui.sayList(list);
                            command = s.nextLine();
                            break;
                        case "done":
                            int finished = Parser.parseNumber(command);
                            tasks.done(finished);
                            ui.sayCompleted(list.get(finished));
                            command = s.nextLine();
                            break;
                        case "todo":
                            if (command.equals("todo") || command.equals("todo ")) {
                                throw new DukeException(DukeException.Type.TODO);
                            }
                            Todo newTodo = new Todo(Parser.parseTodo(command));
                            tasks.add(newTodo);
                            ui.sayUpdates(newTodo, list.size());
                            command = s.nextLine();
                            break;
                        case "deadline":
                            String[] splitD = Parser.parseDeadline(command);
                            String description = splitD[0];
                            String date = splitD[1];
                            Deadline newDeadline = new Deadline(description, date);
                            tasks.add(newDeadline);
                            ui.sayUpdates(newDeadline, list.size());
                            command = s.nextLine();
                            break;
                        case "event":
                            String[] splitE = Parser.parseEvent(command);
                            String descriptionOfEvent = splitE[0];
                            String dateOfEvent = splitE[1];
                            Event newEvent = new Event(descriptionOfEvent, dateOfEvent);
                            tasks.add(newEvent);
                            ui.sayUpdates(newEvent, list.size());
                            command = s.nextLine();
                            break;
                        case "delete":
                            int del = Parser.parseNumber(command);
                            Task toDelete = list.get(del);
                            tasks.delete(del);
                            ui.sayDeletes(toDelete, list.size());
                            command = s.nextLine();
                            break;
                        case "find":
                            ArrayList<Task> keyTasks = Parser.parseFind(list, command);
                            ui.sayFind(keyTasks);
                            command = s.nextLine();
                            break;
                        default:
                            ui.sayWrongInput();
                            command = s.nextLine();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeException(DukeException.Type.INVALID);
                } catch (DateTimeException e) {
                    throw new DukeException(DukeException.Type.DATE);
                }
            } catch (DukeException e) {
                ui.sayError(e);
                command = s.nextLine();
            }
        }
        ui.sayBye();
        storage.writeAll(tasks);
    }

    /**
     * Main method. Duke object is constructed with a filePath
     * and run method is called.
     *
     * @param args Arguments from the command line.
     */
    public static void main(String[] args) {
            new Duke("./Data/Duke.txt").run();
    }
}
