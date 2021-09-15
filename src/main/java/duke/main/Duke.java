package duke.main;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.io.File;
import java.io.IOException;

import duke.exception.DukeException;
import duke.command.Parser;
import duke.task.*;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Ui, storage and load TaskLists from specific filePath for Duke.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Run the programme of Duke.
     */
    public void run() {
        String input = "";
        ui.Greet();
        Scanner keyboardIn = new Scanner(System.in);
        do {
            try {
                input = keyboardIn.nextLine();
                Parser parser = new Parser(input);
                String operation = parser.getOperationType();
                switch (operation) {
                    case "bye": {
                        ui.bye();
                        break;
                    }
                    case "list": {
                        ui.list(tasks);
                        break;
                    }
                    case "done": {
                        int index = parser.getIndex();
                        tasks.markDone(index-1);
                        ui.done(tasks.get(index-1));
                        break;
                    }
                    case "delete": {
                        int index = parser.getIndex();
                        Task task = tasks.get(index-1);
                        tasks.delete(index-1);
                        ui.delete(tasks,task);
                        break;
                    }
                    case "todo": {
                        String description = parser.getTask();
                        Task task = new Todo(description);
                        tasks.add(task);
                        ui.add(tasks,task);
                        break;
                    }
                    case "event": {
                        String description = parser.getTask();
                        LocalDate time = parser.getTime();
                        Task task = new Event(description, time);
                        tasks.add(task);
                        ui.add(tasks,task);
                        break;
                    }
                    case "deadline": {
                        String description = parser.getTask();
                        LocalDate time = parser.getTime();
                        Task task = new Deadline(description, time);
                        tasks.add(task);
                        ui.add(tasks,task);
                        break;
                    }
                    default:  {

                    }
                }

            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        } while (!input.equals("bye"));
        storage.updateData();
    }



    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
