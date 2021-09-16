package duke;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.DateTimeException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ArrayList<Task> list;
    private Ui ui;

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
                            Todo toAdd = new Todo(Parser.parseTodo(command));
                            tasks.add(toAdd);
                            ui.sayUpdates(toAdd, list.size());
                            command = s.nextLine();
                            break;
                        case "deadline":
                            String[] splitD = Parser.parseDeadline(command);
                            String first = splitD[0];
                            String second = splitD[1];
                            Deadline toAdd2 = new Deadline(first, second);
                            tasks.add(toAdd2);
                            ui.sayUpdates(toAdd2, list.size());
                            command = s.nextLine();
                            break;
                        case "event":
                            String[] splitE = Parser.parseEvent(command);
                            String one = splitE[0];
                            String two = splitE[1];
                            Event toAdd3 = new Event(one, two);
                            tasks.add(toAdd3);
                            ui.sayUpdates(toAdd3, list.size());
                            command = s.nextLine();
                            break;
                        case "delete":
                            int del = Parser.parseNumber(command);
                            Task toDelete = list.get(del);
                            tasks.delete(del);
                            ui.sayDeletes(toDelete, list.size());
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
    public static void main(String[] args) {
            new Duke("./Data/Duke.txt").run();
    }
}
