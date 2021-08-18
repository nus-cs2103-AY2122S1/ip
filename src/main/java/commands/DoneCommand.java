package commands;
import java.util.List;

public class DoneCommand extends ExecutableCommand {
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String COMPLETED = "[X]";

    public DoneCommand(String desc) {
        super(desc);
    }

    @Override
    public void execute(List<Command> commandList, List<Boolean> completedTask) {
        System.out.println(DONE_MESSAGE);
        String[] instructions = this.command_description.split(" ");
        int taskNumber = Integer.parseInt(instructions[1]);
        completedTask.set(taskNumber - 1, true);
        System.out.println(COMPLETED + " " + commandList.get(taskNumber - 1));
    }
}
