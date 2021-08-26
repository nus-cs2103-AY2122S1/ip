package duke.taskTypes;

import duke.exceptions.InvalidEventFormatException;
import duke.exceptions.DukeException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Event extends Task{

    /**
     * Takes in a string and splits msg into based on /at pattern. Set the eventType and time of the instance
     * @param input string from the user
     */
    public Event(String input, boolean isDone) throws DukeException {
        super(isDone);
        List<String> results = Pattern.compile("/at").splitAsStream(input).map(x->x.trim()).collect(Collectors.toList());

        if (results.size() == 0) {
            throw new InvalidEventFormatException("Missing description and empty");
        } else if (results.size() == 1) {
            throw new InvalidEventFormatException("Invalid timestamp format");
        }
        String key = results.get(0);
        if (key.equals("")) {
            throw new InvalidEventFormatException(" Missing description");
        }
        super.setEventType("E");
        super.setDescription(key);
        super.setDate(results.get(1));
    }
    /**
     * Returns a string that describes the instance
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        return super.toString() + " (at: " + super.getDate() + ")";
    }

    @Override
    public String saveTask() {
        return super.saveTask() + " /at " + super.getDate();
    }
}
