package commands;
import java.util.List;
import status.Status;

public class DoneCommand extends ExecutableCommand {
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";

    public DoneCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isForStorage() {
        return false;
    }

    public static Command markAsComplete(Command inputCommand) {
        NonExecutableCommand com = (NonExecutableCommand) inputCommand;
        NonExecutableCommand updatedCom = com.updateStatus(Status.COMPLETED.getStatus(), Status.STORED.getStatus());
        return updatedCom;
    }

    @Override
    public void execute(List<Command> commandList) {
        System.out.println(DONE_MESSAGE);
        String[] instructions = this.command_description.split(" ");
        int taskNumber = Integer.parseInt(instructions[1]);
        NonExecutableCommand com = (NonExecutableCommand) commandList.get(taskNumber - 1);
        NonExecutableCommand completedComm = com.updateStatus(Status.COMPLETED.getStatus(), Status.STORED.getStatus());
        commandList.set(taskNumber - 1, completedComm);
        System.out.println(completedComm);
    }
}
