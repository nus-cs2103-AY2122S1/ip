package duke.command;

import duke.Ui;
import duke.Storage;
import duke.notes.NotesList;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.exceptions.DukeException;

import java.time.DateTimeException;

public class AddCommand extends Command {
    String command;

    public AddCommand(String command) {
        this.command = command;
    }

    private String getTypeOfTask() {
        String typeOfTask = command.split(" ")[0];
        return typeOfTask;
    }

    private Integer getLengthOfCommand() {
        String[] parsedCommand = command.split(" ", 2);
        Integer lengthOfCommand = parsedCommand.length;
        return lengthOfCommand;
    }

    private String getTaskDescription() {
        String descriptionOfTask = command.split(" ", 2)[1];
        return descriptionOfTask;
    }

    private String executeTodoCommand(TaskList tasks, Ui ui, Storage storage) {
        try {
            if(getLengthOfCommand() == 1) {
                ToDo todo = new ToDo(getTypeOfTask());
            } else {
                String descriptionOfTodo = getTaskDescription();
                ToDo todo = new ToDo(descriptionOfTodo);
                tasks.addTask(todo);
                storage.appendToFile(todo);
                return ui.respondToTodo(tasks.getTasks(), todo);
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return "";
    }

    private String getDeadlineDescription(String taskDescription) {
        String descriptionOfDeadline = taskDescription.split(" /")[0];
        return descriptionOfDeadline;
    }

    private String getDeadlineTiming(String taskDescription, Ui ui) {
        try {
            String by = command.split("/by ")[1];
            return by;
        }  catch(DateTimeException e) {
            return ui.showError("You have keyed in an incorrect date or time! Please check :)");
        }

    }

    private String executeDeadlineCommand(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (getLengthOfCommand() == 1) {
                Deadline deadline = new Deadline(getTypeOfTask(), "");
            } else {
                String descriptionOfTask = getTaskDescription();
                String descriptionOfDeadline = getDeadlineDescription(descriptionOfTask);
                String by = getDeadlineTiming(descriptionOfTask, ui);
                Deadline deadline = new Deadline(descriptionOfDeadline, by);
                tasks.addTask(deadline);
                storage.appendToFile(deadline);
                return ui.respondToDeadline(tasks.getTasks(), deadline);
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return "";
    }

    private String getEventDescription(String taskDescription) {
        String descriptionOfDeadline = taskDescription.split(" /")[0];
        return descriptionOfDeadline;
    }

    private String getEventTiming(String taskDescription) {
        String by = command.split("/at ")[1];
        return by;
    }

    private String executeEventCommand(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (getLengthOfCommand() == 1) {
                Event event = new Event(getTypeOfTask(), "");
            } else {
                String descriptionOfTask = getTaskDescription();
                String description = getEventDescription(descriptionOfTask);
                String at = getEventTiming(descriptionOfTask);
                Event event = new Event(description, at);
                tasks.addTask(event);
                storage.appendToFile(event);
                return ui.respondToEvent(tasks.getTasks(), event);
            }
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
        return "";
    }

    @Override
    public String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage) {
       try {
            String typeOfTask = getTypeOfTask();
            if(typeOfTask.equals("todo")) {
                return executeTodoCommand(tasks, ui, storage);
            } else if(typeOfTask.equals("deadline")) {
                return executeDeadlineCommand(tasks, ui, storage);
            } else if(typeOfTask.equals("event")) {
                return executeEventCommand(tasks, ui, storage);
            } else {
                return ui.respondToInvalidCommand();
            }
        } catch(DateTimeException e) {
            return ui.showError("You have keyed in an incorrect date or time! Please check :)");
        }
    }

    /**@Override
    public String execute(NotesList notes, Ui ui, Storage storage) {
        return "";
    };*/

    /**@Override
    public Boolean isTaskRelatedCommand() {
        return true;
    };*/

    @Override
    public Boolean isExit() {
        return false;
    }
}
