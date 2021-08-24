import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date date;

    public Deadline(String description, String by) throws Exception{
        super(description);
        date = new SimpleDateFormat("dd/MM/yyyy").parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.toString() + ")";
    }
}