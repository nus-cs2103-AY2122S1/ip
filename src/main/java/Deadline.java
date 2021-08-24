import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    Deadline(String toDo, LocalDate date, LocalTime time){
        super(toDo);
        this.date = date;
        this.time = time;
    }

    String getType() {
        return "D";
    }
    
    String getDate() {
        return date;
    }

    String getToWrite() {
        return this.getType() + " | " + super.getToWrite() + " | " + date;
    }

    @Override
    public String toString() {
        return("[D]" + super.toString() + "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " 
                + time.format(DateTimeFormatter.ofPattern("hh:mm a")) + ")");
    }
}