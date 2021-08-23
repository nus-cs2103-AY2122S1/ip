import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DukeDateTime {
    private enum F {
        DT_FULL(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        DT_SHORT(DateTimeFormatter.ofPattern("MM-dd HH:mm")),
        TIME(DateTimeFormatter.ofPattern("HH:mm")),
        DATE_SHORT(DateTimeFormatter.ofPattern("MM-dd")),
        DATE_LONG(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        public DateTimeFormatter format;
        public String regex ;
        F(DateTimeFormatter format) {
            this.format = format;
        }
    }
    
    private LocalDateTime dateTime = null;
    private LocalDate date = null;
    private LocalTime time = null;
    
    public DukeDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public DukeDateTime(LocalTime time) {
        this.time = time;
    }
    public DukeDateTime(LocalDate date) {
        this.date = date;
    }
    
    public static DukeDateTime parse(String arg) {
        // to edit
        return new DukeDateTime(LocalDateTime.now());
    }
    
    public String format(DateTimeFormatter formatter) {
        // to edit
        return toString();
    }
    
    @Override
    public String toString() {
        if (dateTime != null) return dateTime.toString();
        if (date != null) return date.toString();
        if (time != null) return time.toString();
        return new String();
    }
}
