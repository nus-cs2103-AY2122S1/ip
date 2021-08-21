package tiger.utils;


import java.time.LocalDate;
import java.time.LocalTime;

public class CustomDate {
    private LocalDate localDate;
    private LocalTime localTime;
    boolean hasTime;

    public CustomDate(LocalDate localDate, LocalTime localTime, boolean hasTime) {
        this.localDate = localDate;
        this.localTime = localTime;
        this.hasTime = hasTime;
    }

    @Override
    public String toString() {
        if (hasTime) {
            return String.format("%s, %s", this.localDate.toString(), this.localTime.toString());
        } else {
            return String.format("%s", this.localDate.toString());
        }
    }
}
