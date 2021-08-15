import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Deadline extends Task{
    private String eventType;
    private String time;

    public Deadline(String input){
        super();
        List<String> results = Pattern.compile("/by").splitAsStream(input).map(x->x.trim()).collect(Collectors.toList());
        String key = results.get(0);
        String time = results.get(1);
        super.setDescription(key);
        this.eventType = "[D]";
        this.time = "(by: " + time + ")";
    }

    @Override
    public String toString(){
        return this.eventType + super.toString() + " " + this.time;
    }
}
