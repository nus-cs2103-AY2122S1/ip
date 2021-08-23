package duke.tasks;

import static duke.common.Formats.DT_OUTPUT_FORMAT;
import static duke.common.Formats.DT_DATA_FORMAT;

import java.time.LocalDateTime;

public class Event extends Task{
    private LocalDateTime startTime;

    public Event(boolean isDone, String description, LocalDateTime startTime) {
        super(isDone, description);
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (at: %s)", startTime.format(DT_OUTPUT_FORMAT));
    }

    @Override
    public String getData() {
        return String.format("E | %s | %s", super.getData(), startTime.format(DT_DATA_FORMAT));
    }
}
