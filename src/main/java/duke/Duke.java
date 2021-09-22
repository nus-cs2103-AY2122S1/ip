package duke;

import java.util.Scanner;

/**
 * Represents a Duke chatbot. It helps to collate tasks for the user.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final int DESCRIPTION = 0;
    private static final int DATE_TIME = 1;
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
     * Generates a response to the user input given.
     *
     * @param input Input given by user.
     * @return Response to the input given.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(input);

        assert (this.tasks.getNoOfTasks() >= 0) : "Number of tasks cannot be negative";
        try {
            if (parser.isList()) {
                return respondToList(parser);
            } else if (parser.isDone()) {
                return respondToDone(parser);
            } else if (parser.isToDo()) {
                return respondToToDo(parser);
            } else if (parser.isDeadline()) {
                return respondToDeadline(parser);
            } else if (parser.isEvent()) {
                return respondToEvent(parser);
            } else if (parser.isDelete()) {
                return respondToDelete(parser);
            } else if (parser.isFind()) {
                return respondToFind(parser);
            } else {
                throw new DukeException("I do not know what you want to do!");
            }
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    private String respondToList(Parser parser) {
        return this.ui.listAll(this.tasks);
    }

    private String respondToDone(Parser parser) {
        try {
            this.tasks.done(parser.getSecondPartInInt());
            this.storage.save(parser.getCommand());
            this.storage.assertFile();
            return this.ui.doneTask(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

    private String respondToToDo(Parser parser) {
        try {
            ToDo task = new ToDo(parser.getSecondPart());
            this.tasks.add(task);
            this.storage.save(parser.getCommand());
            this.storage.assertFile();
            return this.ui.addTask(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

    private String respondToDeadline(Parser parser) {
        try {
            Deadline task = new Deadline(parser.splitSecondPartForDeadline()[DESCRIPTION],
                    parser.splitSecondPartForDeadline()[DATE_TIME]);
            this.tasks.add(task);
            this.storage.save(parser.getCommand());
            this.storage.assertFile();
            return this.ui.addTask(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

    private String respondToEvent(Parser parser) {
        try {
            Event task = new Event(parser.splitSecondPartForEvent()[DESCRIPTION],
                    parser.splitSecondPartForEvent()[DATE_TIME]);
            this.tasks.add(task);
            this.storage.save(parser.getCommand());
            this.storage.assertFile();
            return this.ui.addTask(this.tasks);

        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

    private String respondToDelete(Parser parser) {
        try {
            this.tasks.delete(parser.getSecondPartInInt());
            return this.ui.deleteTask(this.tasks);
        } catch (DukeException e) {
            return this.ui.showError(e);
        }
    }

    private String respondToFind(Parser parser) {
        try {
            TaskList taskList = this.tasks.find(parser.getSecondPart());
            return this.ui.findTask(taskList);

        } catch (DukeException e) {
            return this.ui.showError(e);
        }

    }
}
