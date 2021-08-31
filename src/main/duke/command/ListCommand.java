package duke.command;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

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
<<<<<<< Updated upstream
            Task t = tasks.get(i);
            if (time != null && !t.time().equals(time)) {
=======
            Task t = (Task) tasks.get(i);
            if (time != null && !t.getTime().equals(time)) {
>>>>>>> Stashed changes
                break;
            } 
            response += String.format("\n%d. %s", i + 1, t);
        }
        ui.showMessage(response);
    }

<<<<<<< Updated upstream
    public Boolean isExit() {
=======
    /**
     * Check if user is ending the chatbot.
     * @return True if user is ending the chatbot.
     */
    public boolean isExit() {
>>>>>>> Stashed changes
        return false;
    }

}
