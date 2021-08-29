package tasks;

import exceptions.DukeException;
import viper.Instruction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDate date;
    
    public Deadlines(String description, String date) {
        super(description, Instruction.DEADLINE);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + 
                this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
