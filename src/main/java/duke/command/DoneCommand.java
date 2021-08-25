package duke.command;

import duke.*;

public class DoneCommand extends Command{

    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IncompleteCommandException {
        String[] stringArr = input.split(" ");
        if (stringArr.length > 1) {
            String taskNumber = stringArr[1];
            if (Parser.isNumeric(taskNumber)) {
                int taskNum = Integer.parseInt(taskNumber);
                if (taskNum > 0 && (taskNum - 1) < taskList.getTotalNumberOfTask()) {
                    taskList.markTaskDoneById(taskNum - 1);

                } else {
                    ui.printErrorMessage("duke.task.Task number is not in the list!");
                }

            } else {
                ui.printErrorMessage("Please enter a valid task number!");
            }

        } else {
            throw new IncompleteCommandException("Please enter the task number after done! E.g \"done 2\"");
        }
        return true;
    }
}
