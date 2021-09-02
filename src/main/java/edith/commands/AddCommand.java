package edith.commands;

import edith.exceptions.DukeException;
import edith.storage.Storage;
import edith.tasks.Deadlines;
import edith.tasks.Events;
import edith.tasks.TaskList;
import edith.tasks.Todos;
import edith.ui.Ui;

import java.io.IOException;

/**
 * Adds todo, deadline and event command to tasklist and storage.
 */
public class AddCommand extends Command {
    protected String cmdLine;

    public AddCommand(String cmdLine) {
        this.cmdLine = cmdLine;
    }
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] splitLine = cmdLine.split(" ", 2);
        try {
            if (splitLine[1].isBlank()) {
                throw new DukeException("Oh no!! Something is missing from this " + splitLine[0] + " !!! ☹");
            }
            switch (Instruction.comparesTo(splitLine[0])) {
            case TODO:
                Todos addTodo = new Todos(splitLine[1].trim());
                tasks.addTask(addTodo);
                storage.writeTask(addTodo);
                return ui.printAddCommand(addTodo, tasks);
            case DEADLINE:
                String[] descDateArray = splitLine[1].split("/by ");
                Deadlines addDeadline = new Deadlines(descDateArray[0].trim(), descDateArray[1]);
                tasks.addTask(addDeadline);
                storage.writeTask(addDeadline);
                return ui.printAddCommand(addDeadline, tasks);
            case EVENT:
                String[] descTimeArray = splitLine[1].split("/at ");
                String[] dateTimeArray = descTimeArray[1].split(" ");
                Events addEvent = new Events(descTimeArray[0].trim(), dateTimeArray[0], dateTimeArray[1]);
                tasks.addTask(addEvent);
                storage.writeTask(addEvent);
                return ui.printAddCommand(addEvent, tasks);
            default:
                return ui.printInvalidTypeError();
            }
        } catch (IOException | DukeException e) {
            return e.getMessage();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
