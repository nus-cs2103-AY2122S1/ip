package duke;

import java.util.List;

/**
 * @Parser is a parser that parses a task based on whichever type of task it is.
 */
public class Parser {
    /**
     * Parses a Task.
     *
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

    public Task parseFromDB(String input) throws DukeDateParseException{
        String[] splitData = input.split("\\|");
        if(splitData[0].equals(ToDo.TODO_LABEL)) {
            return new ToDo(splitData[1], splitData[2]);
        } else if(splitData[0].equals(Deadline.DEADLINE_LABEL)) {
            return new Deadline(splitData[1], splitData[2], splitData[3]);
        } else if(splitData[0].equals(Event.EVENT_LABEL)) {
            return new Event(splitData[1], splitData[2]);
        } else {
            throw new DukeArgumentException("Database error!");
        }
    }
}
