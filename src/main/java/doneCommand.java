public class doneCommand extends Command {
    private String command;

    public doneCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        if (value > taskList.size()) {
            System.out.println("Sorry the task doesn't exist yet, please try again!");
        } else {
            Task t = taskList.getTask(value - 1);
            t.markAsDone();
            Ui.doneResponse(t);
        }
    }
}