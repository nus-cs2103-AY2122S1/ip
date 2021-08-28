package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;
import duke.data.task.Todo;

public class TodoCommand extends Command {
    private String input;
    public TodoCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
        if (input.length() == 4) {
            throw new DukeException(Ui.emptyDescriptionMsg("todo"));
        }
        Todo t = new Todo(input.substring(5));
        taskList.addTask(t);
    }

}
