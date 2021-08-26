import commands.Command;
import commands.ExecutableCommand;
import commands.NonExecutableCommand;
import java.util.List;
import java.util.ArrayList;
import status.Status;
public class TasksHandler {
    private final List<Command> allTasks;

    public TasksHandler(ArrayList<Command> allTasks) {
        this.allTasks = allTasks;
    }

    protected void storeTasks(Command newCommand) {
        if (newCommand.isForStorage() && !newCommand.isExecutable()) {
            NonExecutableCommand comm = (NonExecutableCommand) newCommand;
            Command commandStored = comm.updateStatus(Status.NOT_COMPLETED.getStatus(), Status.STORED.getStatus());
            System.out.println("Got it. I've added this task:");
            System.out.println(commandStored);
            this.allTasks.add(commandStored);
            System.out.println("Now you have " + this.allTasks.size() + " tasks in the list.");
        }
    }

    protected void setToAllTasksList(ArrayList<Command> inputTasks) {
        this.allTasks.addAll(inputTasks);
    } 


    protected boolean handleTasks(Command newCommand) {
        if (newCommand.isExecutable()) {
            ExecutableCommand exeCommand = (ExecutableCommand) newCommand;
            exeCommand.execute(this.allTasks);
            return false;
        }
        boolean exit = !newCommand.isForStorage();
        if (exit) {
            System.out.println(newCommand);
        }
        return exit;
    }
    
}
