package commands;
import java.util.List;

public class DeleteCommand extends ExecutableCommand {
    private static final String DELETE_MESSAGE = "Noted. I've removed this task:";

    public DeleteCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isForStorage() {
        return false;
    }

    @Override
    public void execute(List<Command> commandList) {
        System.out.println(DELETE_MESSAGE);
        String[] instructions = this.command_description.split(" ");
        int taskNumber = Integer.parseInt(instructions[1]);
        Command removedCommand = commandList.remove(taskNumber - 1);
        String taskRemaining = "Now you have " + commandList.size() + " tasks in the list.";
        System.out.println(removedCommand + "\n" + taskRemaining);
    }
    
}
