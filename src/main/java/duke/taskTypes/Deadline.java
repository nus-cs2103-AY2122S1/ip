package duke.taskTypes;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.EmptyDescriptionException;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Deadline extends Task{
    /**
     * Takes in a string and splits msg into based on /by pattern. Set the eventType and time of the instance
     * @param input string from the user
     */
    public Deadline(String input, boolean isDone) throws DukeException {
        super(isDone);
        List<String> results = Pattern.compile("/by").splitAsStream(input).map(x->x.trim()).collect(Collectors.toList());
        String key;

        if (results.size() == 0){
            throw new InvalidFormatException("Missing description and timestamp");
        } else if (results.size() == 1){
            throw new EmptyTimeException("Invalid timestamp format");
        }

        key = results.get(0);
        if (key.equals("")){
            throw new EmptyDescriptionException("Missing description");
        }

        super.setEventType("D");
        super.setDescription(key);
        super.setDate(results.get(1));
    }

    /**
     * Returns a string that describes the instance
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        return super.toString() + " (by: " + super.getFormatDate() + ")";
    }

    @Override
    public String saveTask() {
        return super.saveTask() + " /by " + super.getDate();
    }
}
