package duke;

import java.util.Scanner;

/**
 * Represents a Duke chatbot. It helps to collate tasks for the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor that constructs a Duke object.
     *
     * @param filePath File Path for Storage to obtain saved data. If data does not exist, a new file will be created
     *                 with that filePath.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);

        try {
            if (parser.isList()) {
                return this.ui.listAll(this.tasks);
            } else if (parser.isDone()) {
                try {
                    // Run based on done command
                    this.tasks.done(parser.getSecondPartInInt());
                    this.storage.save(parser.getCommand());
                    return ui.doneTask(this.tasks.getMostRecent());

                } catch (DukeException e) {
                    return ui.showError(e);

                }
            } else if (parser.isToDo()) {
                try {
                    // Run based on todo command
                    ToDo task = new ToDo(parser.getSecondPart());
                    this.tasks.add(task);
                    this.storage.save(parser.getCommand());
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isDeadline()) {
                try {
                    // Run based on deadline command
                    Deadline task = new Deadline(parser.splitSecondPartForDeadline()[0],
                            parser.splitSecondPartForDeadline()[1]);
                    this.tasks.add(task);
                    this.storage.save(parser.getCommand());
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isEvent()) {
                try {
                    // Run based on event command
                    Event task = new Event(parser.splitSecondPartForEvent()[0],
                            parser.splitSecondPartForEvent()[1]);
                    this.tasks.add(task);
                    this.storage.save(parser.getCommand());
                    return ui.addTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isDelete()) {
                try {
                    // Run based on delete command
                    this.tasks.delete(parser.getSecondPartInInt());
                    return ui.deleteTask(this.tasks.getMostRecent(), this.tasks);

                } catch (DukeException e) {
                    // Display error
                    return ui.showError(e);

                }
            } else if (parser.isFind()) {
                try {
                    TaskList taskList = this.tasks.find(parser.getSecondPart());
                    return ui.findTask(taskList);

                } catch (DukeException e) {
                    return ui.showError(e);

                }
            } else {
                throw new DukeException("I do not know what you want to do!");
            }
        } catch (DukeException e) {
            // Display error
            return ui.showError(e);

        }
    }
}
