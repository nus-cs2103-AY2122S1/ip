public class DoneTaskCommand extends Command {

    public DoneTaskCommand (String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        doneTask(this.getUserInput(), taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }


    private void doneTask(String userInput,TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] inputArray = userInput.split(" ");
        Task completedTask = taskList.get(Integer.parseInt(inputArray[1]) - 1);
        completedTask.markAsDone();
        storage.saveData(taskList);
        String doneMessage = String.format("Nice! I've marked this task as done:\n%s", completedTask);
        ui.printMessage(doneMessage);
    }

}
