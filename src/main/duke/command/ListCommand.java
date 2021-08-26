package duke.command;

import java.time.LocalDateTime;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class ListCommand extends Command {

    private LocalDateTime time = null;
    
    public ListCommand(String fullCommand) {
        String timeString = fullCommand.replace("list", "");
        if (!timeString.trim().isEmpty()) {
            this.time = LocalDateTime.parse(timeString.trim());
        }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (time != null && !t.time().equals(time)) {
                break;
            } 
            response += String.format("\n%d. %s", i + 1, t);
        }
        System.out.println(response);
    }

    public Boolean isExit() {
        return false;
    }

}
