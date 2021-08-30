public class PrintCommand extends Command {
    private String date;

    public PrintCommand(String type, String date) {
        super(type);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            printTasksOnDate(taskList, ui);
        } catch (DukeException e) {
            ui.displayError(e.getMessage());
        }
    }

    public void printTasksOnDate(TaskList taskList, Ui ui) throws DukeException {
        if (taskList.size() != 0) {
            String response = ui.getTasksOnDate(date, taskList);
            ui.displayResponse(response);
        } else {
            throw new DukeException("There are no tasks on this date!");
        }
    }
}
