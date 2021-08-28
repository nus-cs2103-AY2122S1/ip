public class listCommand extends Command {
    private String command;
    
    public listCommand(String command) {
       super(command); 
       this.command = command;
    }
    
    public void execute(TaskList taskList, Storage storage) {
        taskList.getList();
    }
}