import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Level-4 -> A-Inheritance: Deadline Task Class
public class Deadline extends Task {

    public Deadline(String description, String at, TASK_TYPE type) {
        super(description,at,type);
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM d yyyy");
        String data = this.by.format(df);
        return super.toString().concat(" (by: ".concat(data).concat(")"));
    }
}


