package duke.command;

import duke.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AddCommand extends Command{
    private final String fullCommand;
    private final TaskEnum taskType;

    public AddCommand(String fullCommand, TaskEnum taskType) {
        this.fullCommand = fullCommand;
        this.taskType = taskType;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public ArrayList<String> execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask;
            if (taskType == TaskEnum.TODO) {
                if (fullCommand.length() <= 4 || fullCommand.substring(5).strip().equals("")) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                newTask = new Todo(fullCommand.substring(5).strip());
            } else if (taskType == TaskEnum.DDL) {
                newTask = Deadline.parseNewCommand(fullCommand);
            } else {
                newTask = Event.parseNewCommand(fullCommand);
            }
            return taskList.addTask(newTask);
        } catch (IllegalArgumentException | DateTimeParseException | IndexOutOfBoundsException | DukeException e) {
            ArrayList<String> resp = new ArrayList<>();
            resp.add(Ui.UI_ERROR_HEADING);
            resp.add("\t" + e.getMessage());
            return resp;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddCommand that = (AddCommand) o;

        if (fullCommand != null ? !fullCommand.equals(that.fullCommand) : that.fullCommand != null) return false;
        return taskType == that.taskType;
    }
}
