package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime by;
    private Boolean hasTime = true;

    public Deadline(String description, String by) {
        super(description);
        this.by = parseTime(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public LocalDateTime parseTime(String time) {
        String newDate;
        String[] str = time.split(" ");
        String[] oldDate = str[0].split("/");
        LocalDateTime localTime;
        if (str.length > 1) {
            String hour = str[1].substring(0,2);
            String min = str[1].substring(2,4);

            localTime = LocalDateTime.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]), Integer.parseInt(oldDate[0]),
                    Integer.parseInt(hour), Integer.parseInt(min));
        } else {
            localTime = LocalDate.of(Integer.parseInt(oldDate[2]), Integer.parseInt(oldDate[1]), Integer.parseInt(oldDate[0]))
                    .atStartOfDay();
            hasTime = false;
        }
        return localTime;
    }

    public String writeToFile() {
        String s = "D" + " | ";
        if (this.isDone) {
            s += "1";
        } else {
            s += "0";
        }
        s = s + " | " + description + " | " + by;
        return s;
    }

    @Override
    public String toString() {
        String string = "[D]" + super.toString() + "(by: " ;
        if (hasTime) {
            string += by.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy")) + ")";
        } else {
            string += by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
        }
        return string;
    }

    public static void main(String[] args) {
        Deadline d = new Deadline("return book", "2/12/2019 1800");
        System.out.println(d);
    }
}
