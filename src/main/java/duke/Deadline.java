package duke;

import java.time.format.DateTimeFormatter;

public class Deadline extends TimedTask {
    public static final String DEADLINE_NAME = "deadline";
    public static final String DEADLINE_INITIAL = "D";
    public static final String DEADLINE_TIME_MARKER = "by";

    public Deadline(String name, String time){
        super(name, time);
    }

    @Override
    public String format(DateTimeFormatter dtformatter){
        return String.format("[%s][%s] %s (by: %s)",
                DEADLINE_INITIAL, this.isDone ? "X" : " ", getName(), dtformatter.format(time));
    }

    public Deadline(String name, String time, boolean isDone){
        super(name, time, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(
                ",", DEADLINE_NAME, name, time.toString(), String.valueOf(isDone));
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (by: %s)",
                DEADLINE_INITIAL, this.isDone ? "X" : " ", getName(), time);
    }
}
