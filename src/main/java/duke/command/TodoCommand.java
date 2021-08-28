package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.ui.Ui;
import duke.data.task.Todo;

/**
 * Class that encapsulates the "Todo" Command.
 *
 * @author Wang Hong Yong
 */
public class TodoCommand extends Command {
    private String input;

    /**
     * Constructor for EventCommand.
     *
     * @param tasklist Task Handler that handles the operation.
     * @param input command string to be executed.
     */
    public TodoCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    /**
     * Executes the "Todo" Command.
     */
    public void execute(){
        if (input.length() == 4) {
            throw new DukeException(Ui.getEmptyDescriptionMsg("todo"));
        }
        Todo t = new Todo(input.substring(5));
        taskList.addTask(t);
    }

}
