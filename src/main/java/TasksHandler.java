import commands.Command;
import commands.ExecutableCommand;
import commands.NonExecutableCommand;
import java.util.List;
import java.util.ArrayList;
import status.Status;


public class TasksHandler {
    private final List<Command> allTasks;

    public TasksHandler() {
        this.allTasks = new ArrayList<Command>();
    }

    protected void storeTasks(Command newCommand) {
        if (newCommand.isForStorage() && !newCommand.isExecutable()) {
            NonExecutableCommand comm = (NonExecutableCommand) newCommand;
            Command commandStored = comm.updateStatus(Status.NOT_COMPLETED.getStatus(), Status.STORED.getStatus());
            System.out.println(commandStored);
            this.allTasks.add(commandStored);
            System.out.println("Now you have " + this.allTasks.size() + " tasks in the list.");
        }
    }

    protected boolean handleTasks(Command newCommand) {
        if (newCommand.isExecutable()) {
            ExecutableCommand exeCommand = (ExecutableCommand) newCommand;
            exeCommand.execute(this.allTasks);
            return false;
        }
        NonExecutableCommand nonExeCommand = (NonExecutableCommand) newCommand;
        boolean exit = !nonExeCommand.isForStorage();
        if (exit) {
            System.out.println(nonExeCommand);
        }
        return exit;
    }

}
