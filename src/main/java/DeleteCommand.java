public class DeleteCommand extends Command{

    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() > 7) {
            String[] stringArr = input.split(" ");
            if (Parser.isNumeric(stringArr[1])) {
                int taskId = Integer.parseInt(stringArr[1]) - 1;
                Task taskToBeDeleted = taskList.getTaskById(taskId);
                taskList.removeTaskById(taskId);
                ui.taskRemovedMessage(taskToBeDeleted, taskList.getTotalNumberOfTask());
            }
        }
        return false;
    }

}
