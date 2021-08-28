public class DoneCommand extends Command {

    private int taskNumber;
    private final boolean EXIT = false;

    public DoneCommand(int i) {
        this.taskNumber = i;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidValue {
        try {
            tasks.completeTask(taskNumber);
            System.out.printf("\tNice! I've marked this task as done:\n" + "\t%s\n", tasks.getTask(taskNumber));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidValue();
        }
    }

    public boolean isExit() {
        return EXIT;
    }
}
