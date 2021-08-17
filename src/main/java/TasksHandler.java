import commands.*;
import java.util.List;
import java.util.ArrayList;

public class TasksHandler {
    private final List<Command> allTasks;

    public TasksHandler() {
        this.allTasks = new ArrayList<Command>();
    }

    protected void storeTasks(Command newCommand) {
        if (newCommand.isForStoring()) {
            this.allTasks.add(newCommand);
            System.out.println("added: " + newCommand);
        }
    }

    protected boolean handleTasks(Command newCommand) {
        if (!newCommand.isForStoring() && !newCommand.isExitCommand()) {
            displayAllTasks();
            return false;
        } else if (newCommand.isExitCommand()) {
            System.out.println(newCommand);
            return true;
        }
        return false;
    }

    private void displayAllTasks() {
        for (int i = 1; i <= this.allTasks.size(); i++) {
            Command com = this.allTasks.get(i - 1);
            StringBuilder sb = new StringBuilder("");
            sb.append(String.valueOf(i) + ".");
            sb.append(" " + com);
            System.out.println(sb.toString());
        }
    }

}
