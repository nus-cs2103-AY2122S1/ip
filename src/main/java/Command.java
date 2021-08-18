public class Command {
    private String input;
    private Ui ui;

    public Command(String input) {
        this.input = input;
        ui = new Ui();
    }

    public void execute(TaskList taskList) {
        switch (input) {
            case "bye":
                ui.sayBye();
                break;
            case "list":
                taskList.listTasks();
                break;
            default:
                taskList.addTask(input);
        }
    }

    public boolean shouldExit() {
        return input.equals("bye");
    }
}
