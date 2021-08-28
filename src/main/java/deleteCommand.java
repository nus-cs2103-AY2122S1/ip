public class deleteCommand extends Command {
    private String command;
    
    public deleteCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", ""));
        Task task = taskList.getTask(value-1);
        taskList.removeTask(value-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        storage.writeToFile("./duke.txt", taskList);
    }
}