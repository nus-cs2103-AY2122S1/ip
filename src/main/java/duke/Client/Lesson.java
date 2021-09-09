package duke.Client;

import java.time.Duration;
import java.time.LocalTime;

public class Lesson {
    public final String subject;
    public final Duration duration;
    public final LocalTime lessonTime;
    public final Day day;

    Lesson(String subject, Duration duration, LocalTime lessonTime, Day day) {
        this.subject = subject;
        this.duration = duration;
        this.lessonTime = lessonTime;
        this.day = day;
    }



}
