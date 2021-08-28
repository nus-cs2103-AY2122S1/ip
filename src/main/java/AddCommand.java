public class AddCommand extends Command {

    private final boolean EXIT = false;
    private String taskType;
    private String taskInfo;


    public AddCommand(String input) {
        this.taskType = input.split(" ")[0];

        switch (input.split(" ")[0]) {
            case "todo":
                this.taskInfo = input.substring(5);
                break;
            case "deadline":
                this.taskInfo = input.substring(9);
                break;
            case "event":
                this.taskInfo = input.substring(6);
                break;
            default:
                System.out.println("Something went wrong...");
                break;
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;

        switch(taskType) {
            case "todo":
                newTask = new Todo(taskInfo, false);
                break;
            case "deadline":
                String deadlineDescription = taskInfo.split(" /by ")[0];
                String deadlineDate = taskInfo.split(" /by ")[1];
                newTask = new Deadline(deadlineDescription, deadlineDate, false);
                break;
            case "event":
                String eventDescription = taskInfo.split(" /at ")[0];
                String eventDate = taskInfo.split(" /at ")[1];
                newTask = new Event(eventDescription, eventDate, false);
                break;
            default:
                System.out.println("Something went wrong...");
        }

        tasks.addTask(newTask);
        storage.write(tasks.getTaskList());
    }

    public boolean isExit() {
        return EXIT;
    }
}
