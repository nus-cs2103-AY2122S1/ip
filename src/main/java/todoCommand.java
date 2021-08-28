public class todoCommand extends Command {
    private String command;
    
    public todoCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 5) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a todo cannot be empty.");
            System.out.println(exp);
        } else {
            Task task = new Todo(command.substring(5));
            taskList.addTask(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            storage.writeToFile("./duke.txt", taskList);
        } 
    }
}