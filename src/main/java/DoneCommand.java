public class DoneCommand implements Command {

    private String input;
    private TaskList taskList;

    public DoneCommand(String input, TaskList taskList) {
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        try {
            int index = Integer.parseInt(input.split(" ", 2)[1].trim());
            this.taskList.markTaskAsCompleted(index);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.invalidArguments();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArguments() {
        System.out.println("Invalid argument to the \"done\" function.\n");
    }
}
