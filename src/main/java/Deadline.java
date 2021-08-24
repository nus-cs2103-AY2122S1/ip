import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected String by;
    protected LocalDate date;
    //protected LocalTime time;

    public Deadline(String description, String by) throws DukeException1 {
        super(description);
        if(description.equals("deadline")) {
            throw new DukeException1();
        }
        this.by = by;
    }

    public String getInfo() {
        return getDescription() + "/" + this.by;
    }

    public String getType() {
        return "D";
    };

    public LocalDate getDate() {
        String date = by.split(" ")[0];
        this.date = LocalDate.parse(date.split("/")[2] + "-"+ date.split("/")[1] + "-" + date.split("/")[0]);
        /*if(date.split("/").length != 0) {
            return LocalDate.parse()
        } else {
            return LocalDate.parse(date);
        }*/

        return this.date;
    }

    /*public LocalTime getTime() {
        String time = by.split(" ")[1];
        this.time = LocalTime.parse(time);
    }*/

    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + by + ")";
        return "[D]" + super.toString() + "(by: " + getDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
