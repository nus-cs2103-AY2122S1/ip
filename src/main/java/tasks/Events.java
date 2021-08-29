package tasks;

import exceptions.DukeException;
import viper.Instruction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Events(String description, String date, String time) throws DukeException {
        super(description, Instruction.EVENT);
        try {
            this.date = LocalDate.parse(date);
            this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e){
            throw new DukeException("Sorry, I do not understand what you are saying... Please follow the format:\n" +
                    "event presentation /at 2021-09-09 14:15");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " " + time + ")";
    }
}
