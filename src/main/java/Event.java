import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalTime startTime;
    protected LocalTime endTime;
    protected LocalDate date;
    private String detectedDateFormat;
    private static final String[] dateFormats = {
            "dd/MM/yyyy",
            "dd-MM-yyyy",
            "yyyy/MM/dd",
            "yyyy-MM-dd",
            "dd MMM yyyy"
    };
    private static final String[] timeFormats = {
            "HHmm",
            "hh:mm a"
    };

    public Event(String restOfInput) {
        super(restOfInput.split("\\(", 2)[0].trim());
        String dateTimeDuration = restOfInput.substring(restOfInput.indexOf(":")+2,restOfInput.indexOf(")"));
        String date = dateTimeDuration.split(" from: ")[0];
        String timeDuration = dateTimeDuration.split(" from: ")[1];

        if (isDate(date)) {
            this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedDateFormat));
            isDuration(timeDuration);
        }
    }

    public Event(String restOfInput, Boolean empty) throws DukeException {
        super(restOfInput.split("/at", 2)[0].trim());

        if (empty || restOfInput.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        if (restOfInput.contains("/at")) {
            String description = restOfInput.split("/at",2)[0].trim();
            String dateAndTimeDuration = restOfInput.split("/at",2)[1].trim();

            if (description.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            } else if (dateAndTimeDuration.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
            }

            if (dateAndTimeDuration.contains(" ")) {
                String date = dateAndTimeDuration.split(" ", 2)[0];
                String timeDuration = dateAndTimeDuration.split(" ", 2)[1];
                if (isDate(date)) {
                    this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(detectedDateFormat));
                    if (!isDuration(timeDuration)) {
                        throw new DukeException("☹ OOPS!!! Please enter a valid time duration!" +
                                " Valid formats are (HHmm-HHmm or hh:mm a-hh:mm a)");
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! Please enter a valid date in duration!");
                }
            } else {
                throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The duration of an event cannot be empty.");
        }

    }

    private boolean isDuration (String duration) {
        if (duration.contains("-")) {
            String startTime = duration.split("-", 2)[0];
            String endTime = duration.split("-", 2)[1];
            Boolean startIsTime = false;
            Boolean endIsTime = false;
            for (String i : timeFormats) {
                try {
                    this.startTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern(i));
                    startIsTime = true;
                } catch (Exception e) {
                }
            }

            for (String i : timeFormats) {
                try {
                    this.endTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern(i));
                    endIsTime = true;
                } catch (Exception e) {
                }
            }

            return startIsTime && endIsTime;
        } else {
            return false;
        }

    }

    private boolean isDate(String dateString) {
        boolean isDate = false;

        for (String i : dateFormats) {
            try {
                LocalDate.parse(dateString, DateTimeFormatter.ofPattern(i));
                detectedDateFormat = i;
                isDate = true;
            } catch (Exception e) {
            }
        }
        return isDate;
    }

    @Override
    public String toString() {
            return String.format("[E]%s (at: %s from: %s)", super.toString(),
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + "-"
                            + this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a")));

    }
}
