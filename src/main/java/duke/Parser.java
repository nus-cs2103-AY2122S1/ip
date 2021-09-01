package duke;

/**
 * @Parser is a parser that parses a task based on whichever type of task it is.
 */
public class Parser {
    /**
     * Parses a Task.
     * @param task
     * @return
     * @throws DukeNoDateException
     */
    public String parseTask(Task task) throws DukeNoDateException {
        String taskType = task.getTaskType();
        String isCompleted = String.valueOf(task.isDone);
        String description = task.description;
        String date = "";
        if (task instanceof Deadline || task instanceof Event) {
            try {
                date = task.getDate();
            } catch (DukeNoDateException e) {
                throw e;
            }
        }
        String data = taskType + "|" + isCompleted + "|" + description + (date.equals("") ? "" : "|" + date);
        return data;
    }
}
