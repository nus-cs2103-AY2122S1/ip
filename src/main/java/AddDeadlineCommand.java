import Tasks.Deadline;

public class AddDeadlineCommand extends Command {

    private String[] keyWords;
    public AddDeadlineCommand(String[] keywords) {
        this.keyWords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.keyWords[0], this.keyWords[1]);
        tasks.addTask(deadline);
        String[] messages = new String[] {
                "Got it. I've added this task:",
                "    " + deadline.toString(),
                String.format("Now you have %d tasks in the list.", tasks.getSize())
        };
        ui.printOut(messages);
        storage.save(tasks);
    }
}
