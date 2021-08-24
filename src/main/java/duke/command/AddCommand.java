public class AddCommand extends Command{
    private final String fullCommand;
    private final String taskType;

    public AddCommand(String fullCommand, String taskType) {
        this.fullCommand = fullCommand;
        this.taskType = taskType;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = null;
            if (taskType.equals("todo")) {
                newTask = new Todo(fullCommand.substring(5).strip());
            } else if (taskType.equals("deadline")) {
                newTask = new Deadline(Deadline.extractTaskDescription(fullCommand), 
                        Deadline.extractTaskTime(fullCommand));
            } else if (taskType.equals("event"))  {
                newTask = new Event(Event.extractTaskDescription(fullCommand), 
                        Event.extractTaskTime(fullCommand));
            }
            taskList.addTask(newTask);
        } catch (UnclearInstructionException e) {
            System.err.println(e.getMessage());
        }
    }
    
}