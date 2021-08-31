package duke.main;

import java.time.LocalDateTime;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Find;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.DukeException;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    /** Storage component for Duke */
    private Storage storage;
    /** UI component for Duke */
    private Ui ui;
    /** TaskList component for Duke */
    private TaskList taskList;
    /** Parser component for Duke */
    private Parser parser;
    /** Find component for Duke */
    private Find finder;

    /**
     * Constructor for a duke.main.Duke class.
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
        this.finder = new Find();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response;

       if (!this.parser.isBye(input)) {
            String[] splitInput = parser.splitType(input);
            if (this.parser.isDone(splitInput[0])) {
                int index = parser.getIndex(splitInput);
                String returnString = taskList.markDone(index);
                response = ui.formatMessage(returnString);
            } else if (parser.isList(splitInput[0])) {
                response = ui.formatList(taskList);
            } else if (parser.isTodo(splitInput[0])) {
                try {
                    parser.parseTodo(splitInput);
                    taskList.add(new Todo(splitInput[1]));
                    response = ui.formatSpecialTasks(taskList);
                } catch (DukeException e) {
                    response = ui.formatMessage(e.getMessage());
                }
            } else if (parser.isDeadline(splitInput[0])) {
                try {
                    String[] furtherSplits = parser.parseDeadline(splitInput);
                    LocalDateTime by = parser.parseTime(furtherSplits[1]);
                    taskList.add(new Deadline(furtherSplits[0], by));
                    response = ui.formatSpecialTasks(taskList);
                } catch (DukeException e) {
                    response = ui.formatMessage(e.getMessage());
                }
            } else if (parser.isEvent(splitInput[0])) {
                try {
                    String[] furtherSplits = parser.parseEvent(splitInput);
                    LocalDateTime at = parser.parseTime(furtherSplits[1]);
                    taskList.add(new Event(furtherSplits[0], at));
                    response = ui.formatSpecialTasks(taskList);
                } catch (DukeException e) {
                    response = ui.formatMessage(e.getMessage());
                }
            } else if (parser.isDelete(splitInput[0])) {
                try {
                    int index = parser.parseDelete(splitInput);
                    parser.checkTaskIndex(index, taskList);
                    Task deleted = taskList.delete(index);
                    response = ui.formatDelete(deleted, taskList);
                } catch (DukeException e1) {
                    response = ui.formatMessage(e1.getMessage());
                } catch (NumberFormatException e2) {
                    DukeException newException = new DukeException("Please input a number!");
                    response = ui.formatMessage(newException.getMessage());
                }
            } else if (parser.isFind(splitInput[0])) {
                try {
                    parser.parseFind(splitInput);
                    TaskList matched = finder.findMatch(splitInput[1], taskList);
                    response = ui.formatFind(matched);
                } catch (DukeException e) {
                    response = ui.formatMessage(e.getMessage());
                }
            } else {
                try {
                    if (parser.isBlah(input)) {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    taskList.add(new Task(input));
                    response = ui.formatMessage(String.format("added: %s", input));
                } catch (DukeException e) {
                    response = ui.formatMessage(e.getMessage());
                }
            }
            storage.Save(taskList);
        } else {
           response = ui.formatMessage("Bye. Hope to see you again soon!");
        }
        return response;
    }
}
