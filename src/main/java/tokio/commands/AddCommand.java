package tokio.commands;

import java.io.IOException;

import tokio.exceptions.DukeException;
import tokio.storage.Storage;
import tokio.tasks.Deadlines;
import tokio.tasks.Events;
import tokio.tasks.TaskList;
import tokio.tasks.Todos;
import tokio.ui.Ui;

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
            if (splitLine.length < 2 || splitLine[1].isBlank()) {
                throw new DukeException("Oh no!! Something is missing from this " + splitLine[0] + " !!!");
            }
            switch (Instruction.comparesTo(splitLine[0])) {
            case TODO:
                Todos addTodo = new Todos(splitLine[1].trim());
                tasks.addTask(addTodo);
                storage.writeTask(addTodo);
                return ui.printAddCommand(addTodo, tasks);
            case DEADLINE:
                String[] descDateArray = splitLine[1].split("/by ");
                if (descDateArray.length < 2) {
                    return ui.printSomethingMissing("deadline");
                }
                Deadlines addDeadline = new Deadlines(descDateArray[0].trim(), descDateArray[1]);
                tasks.addTask(addDeadline);
                storage.writeTask(addDeadline);
                return ui.printAddCommand(addDeadline, tasks);
            case EVENT:
                String[] descTimeArray = splitLine[1].split("/at ");
                if (descTimeArray.length < 2) {
                    return ui.printSomethingMissing("event");
                }
                String[] dateTimeArray = descTimeArray[1].split(" ");
                if (dateTimeArray.length < 2) {
                    return ui.printSomethingMissing("event");
                }
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
