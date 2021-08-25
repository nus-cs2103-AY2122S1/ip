package duchess.command;

import duchess.main.Duchess;
import duchess.main.DuchessException;
import duchess.task.Deadline;
import duchess.task.Task;
import java.time.LocalDateTime;

public class TasksCommand extends Command {

    public TasksCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking tasks before or after a date.
     * @param duchess The Duchess to return the output to.
     * @return The tasks which match the condition input.
     */
    public boolean handleLogic(Duchess duchess) {
        String invalidMessage = "The command \"tasks\" should be followed by " +
                "a keyword \"/after\" or \"/before\", a date and/or a time (e.g before 2/10/2019 2pm" +
                " or after today)";
        try {
            String[] timeSplit = getName().substring(1).split(" ", 2);
            String keyword = timeSplit[0];
            String date = timeSplit[1];
            boolean isBefore;
            if (keyword.equals("before"))
                isBefore = true;
            else if (keyword.equals("after"))
                isBefore = false;
            else {
                throw new DuchessException(invalidMessage);
            }
            LocalDateTime dateTime = date.equals("today") ? LocalDateTime.now()
                    : Deadline.convertStringToDate(date);
            String tasksToPrint = "";
            for (int i = 1; i < duchess.getDuchessList().getSize() + 1; i++) {
                Task t = duchess.getDuchessList().getTask(i);
                if (isBefore && t.getDateTime().isBefore(dateTime))
                    tasksToPrint += t + "\n";
                else if (!isBefore && t.getDateTime().isAfter(dateTime))
                    tasksToPrint += t + "\n";
            }
            duchess.getUi().prettyPrint(tasksToPrint.isBlank() ? "You have no tasks " + keyword + " " + date
                    : tasksToPrint);
        } catch (ArrayIndexOutOfBoundsException|DuchessException e) {
            if (e instanceof  ArrayIndexOutOfBoundsException)
                duchess.getUi().prettyPrint(invalidMessage);
            else
                duchess.getUi().prettyPrint(((DuchessException) e).getMessage());
        }
        return true;
    }
}
