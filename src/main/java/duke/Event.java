package duke;

import java.time.format.DateTimeFormatter;

public class Event extends TimedTask {
    public static final String EVENT_NAME = "event";
    public static final String EVENT_INITIAL = "E";
    public static final String EVENT_TIME_MARKER = "at";

    public Event(String name, String time){
        super(name, time);
    }

    @Override
    public String format(DateTimeFormatter dtformatter){
        return String.format("[%s][%s] %s (at: %s)",
                EVENT_INITIAL, this.isDone ? "X" : " ", name, dtformatter.format(time));
    }

    public Event(String name, String time, boolean isDone){
        super(name, time, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(
                ",", EVENT_NAME, name, time.toString(), String.valueOf(isDone));
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s (at: %s)",
                EVENT_INITIAL, this.isDone ? "X" : " ", getName(), time);
    }
}
