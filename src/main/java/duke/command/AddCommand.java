package duke.command;

import duke.Ui;
import duke.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException1;

public class AddCommand extends Command {
    String command;

    public AddCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String typeOfTask = command.split(" ")[0];
            String[] parsedCommand = command.split(" ", 2);
            Integer lengthOfCommand = parsedCommand.length;
            String typeOfIncompleteTask = command.split(" ", 2)[0];
            String descriptionOfTask = command.split(" ", 2)[1];
            if(typeOfTask.equals("todo")) {
                if(lengthOfCommand == 1) {
                    ToDo todo = new ToDo(typeOfIncompleteTask);
                } else {
                    ToDo todo = new ToDo(descriptionOfTask);
                    tasks.addTask(todo);
                    storage.appendToFile(todo);
                    return ui.respondToTodo(tasks.getTasks(), todo);
                }
            } else if(typeOfTask.equals("deadline")) {
                if (lengthOfCommand == 1) {
                    Deadline deadline = new Deadline(typeOfIncompleteTask, "");
                } else {
                    String descriptionOfDeadline = descriptionOfTask.split(" /")[0];
                    String by = command.split("/by ")[1];
                    Deadline deadline = new Deadline(descriptionOfDeadline, by);
                    tasks.addTask(deadline);
                    storage.appendToFile(deadline);
                    return ui.respondToDeadline(tasks.getTasks(), deadline);
                }
            } else if(typeOfTask.equals("event")) {
                if (lengthOfCommand == 1) {
                    Event event = new Event(command.split(" ", 2)[0], "");
                } else {
                    String description = command.split(" ", 2)[1].split(" /")[0];
                    String at = command.split("/at ")[1];
                    Event event = new Event(description, at);
                    tasks.addTask(event);
                    storage.appendToFile(event);
                    return ui.respondToEvent(tasks.getTasks(), event);
                }
            } else {
                ui.respondToInvalidCommand();
            }
        } catch (DukeException1 e) {
            return ui.showError(e.getMessage());
        }
        return "";
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
