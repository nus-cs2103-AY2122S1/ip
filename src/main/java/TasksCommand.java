import java.time.LocalDateTime;

public class TasksCommand extends Command {

    public TasksCommand(String name) {
        super(name);
    }

    /**
     * Handles the logic for checking and creating ToDo tasks.
     * @param input
     * @param duchess
     * @return
     */
    public boolean handleLogic(String input, Duchess duchess) {
        String invalidMessage = "The command \"tasks\" should be followed by " +
                "a keyword \"/after\" or \"/before\", a date and/or a time (e.g before 2/10/2019 2pm" +
                "or after today)";
        try {
            String[] timeSplit = input.substring(6).split(" ", 2);
            String keyword = timeSplit[0];
            String date = timeSplit[1];
            boolean isBefore;
            if (keyword.equals("/before"))
                isBefore = true;
            else if (keyword.equals("/after"))
                isBefore = false;
            else {
                System.out.println(keyword + "\n" + date);
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
            duchess.getUi().prettyPrint(tasksToPrint.isBlank() ? "You have no tasks " + keyword.substring(1) + " " + date
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
