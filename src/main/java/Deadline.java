import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Deadline extends Task{
    private final String eventType;
    private final String time;

    /**
     * Takes in a string and splits msg into based on /by pattern. Set the eventType and time of the instance
     * @param input string from the user
     */
    public Deadline(String input) throws IndexOutOfBoundsException {
        super();
        List<String> results = Pattern.compile("/by").splitAsStream(input).map(x->x.trim()).collect(Collectors.toList());
        String key = results.get(0);
        String time = "(by: " + results.get(1) + ")";
        super.setDescription(key);
        this.eventType = "[D]";
        this.time = time;
    }

    /**
     * Returns a string that describes the instance
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        return this.eventType + super.toString() + " " + this.time;
    }
}
