package duke.taskTypes;

import duke.exception.DukeException;

/**
 * Task class that sets description of task, date, time
 */
public class Todo extends Task{

    /**
     * Takes in a string Set the eventType and description of the instance
     * @param input
     */
    public Todo(String input, boolean isDone) throws DukeException {
        super(isDone);
        String key = input.trim();
        super.setEventType("T");
        super.setDescription(key);
        super.setDate(null);
    }

    /**
     * Returns a string that describes the instance for display
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        return super.toString();
    }

}
