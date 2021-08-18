import commands.*;
import java.util.List;
import java.util.ArrayList;

public class TasksHandler {
    private final List<Command> allTasks;
    private final List<Boolean> isTaskCompleted;

    public TasksHandler() {
        this.allTasks = new ArrayList<Command>();
        this.isTaskCompleted = new ArrayList<Boolean>();
    }

    

    protected void storeTasks(Command newCommand) {
        if (!newCommand.isExecutable()) {
            NonExecutableCommand comm = (NonExecutableCommand) newCommand;
            if (!comm.isExitCommand()) {
                boolean TaskCompleted = false;
                this.allTasks.add(newCommand);
                this.isTaskCompleted.add(TaskCompleted);
                System.out.println("added: " + newCommand);
            }
        }
    }

    protected boolean handleTasks(Command newCommand) {
        if (newCommand.isExecutable()) {
            ExecutableCommand exeCommand = (ExecutableCommand) newCommand;
            exeCommand.execute(this.allTasks, this.isTaskCompleted);
            return false;
        }
        NonExecutableCommand nonExeCommand = (NonExecutableCommand) newCommand;
        boolean exit = nonExeCommand.isExitCommand();
        if (exit) {
            System.out.println(nonExeCommand);
        }
        return exit;
    }

}
