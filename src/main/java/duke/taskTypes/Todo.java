package duke.taskTypes;

import duke.exception.DukeException;

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

    @Override
    public String toString(){
        return super.toString();
    }
}
