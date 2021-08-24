public class AddCommand extends Command{
    private String response;
    private int type;

    public AddCommand(String response, int type) {
        this.response = response;
        this.type = type;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to event operations.
     *
     * @return Whether the input is related to event or not.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Task("");
        switch (type) {
            case 1:
                task = new Todo(response.substring(5));
                break;
            case 2:
                String[] parts2 = response.substring(9).split(" /by ");
                String content2 = parts2[0];
                String time2 = parts2[1];
                task = new Deadline(content2, time2);
                break;
            case 3:
                String[] parts3 = response.substring(6).split(" /at ");
                String content3 = parts3[0];
                String time3 = parts3[1];
                task = new Event(content3, time3);
                break;
        }
        tasks.addElement(task);
        String taskString = task.toString();
        storage.store(taskString);
        ui.showTasks(taskString, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
