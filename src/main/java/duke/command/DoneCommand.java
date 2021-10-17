package duke.command;


import duke.IncompleteCommandException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncompleteCommandException {
        String msg = "";
        String[] stringArr = input.split(" ");
        if (stringArr.length <= 1) {
            throw new IncompleteCommandException("Please enter the task number after done! E.g \"done 2\"");
        }
        String taskNumber = stringArr[1];
        if (Parser.isNumeric(taskNumber)) {
            int taskNum = Integer.parseInt(taskNumber);
            if (taskNum > 0 && (taskNum - 1) < taskList.getTotalNumberOfTask()) {
                msg = taskList.markTaskDoneById(taskNum - 1);

            } else {
                msg = "Task number is not in the list!";
            }

        } else {
           msg = "Please enter a valid task number!";
        }

        return msg;
    }
}
