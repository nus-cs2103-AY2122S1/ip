import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class TaskWithDateTime extends Task {
    private final String dateTimeInput;
    private final String dateInput;
    private final String timeInput;
    private LocalDate date;
    private LocalTime time;
    private String dateTimeOutput; // DateTime (of the task) to be printed

    public TaskWithDateTime(TaskType type, String description, String dateTimeInput) {
        this(type, description, dateTimeInput, false);
    }

    public TaskWithDateTime(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, isDone);
        this.dateTimeInput = dateTimeInput;

        boolean isTimeInputProper = true;

        String[] dateTimeInputParts = dateTimeInput.split("\\s+", 2);

        if (dateTimeInputParts.length == 2) {
            dateInput = dateTimeInputParts[0];
            timeInput = dateTimeInputParts[1];
        } else {
            dateInput = dateTimeInputParts[0];
            timeInput = "";
        }

        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            date = null;
        }

        if (!timeInput.equals("")) {
            try {
                int timeInputInInt = Integer.parseInt(timeInput);

                if ((timeInputInInt < 0 || timeInputInInt > 2359)) {
                    isTimeInputProper = false;
                    time = null;
                } else {
                    if (timeInput.length() != 4) {
                        isTimeInputProper = false;
                        time = null;
                    } else {
                        int hour = timeInputInInt / 100;
                        int min = timeInputInInt - (hour * 100);

                        String timeInputInTimeFormat = "";

                        if (hour < 10) {
                            timeInputInTimeFormat += "0" + hour;
                        } else {
                            timeInputInTimeFormat += hour;
                        }

                        if (min < 10) {
                            timeInputInTimeFormat += ":" + "0" + min;
                        } else {
                            timeInputInTimeFormat += ":" + min;
                        }

                        DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_LOCAL_TIME;
                        time = LocalTime.parse(timeInputInTimeFormat, timeFormatter);
                    }
                }
            } catch (Exception e) {
                time = null;
            }
        } else {
            time = null;
        }

        dateTimeOutput = "";

        if (date == null && time == null) {
            dateTimeOutput = dateTimeInput;
        } else {
            if (date != null) {
                dateTimeOutput += date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else {
                dateTimeOutput += dateInput;
            }

            if (time != null) {
                dateTimeOutput += ", " + time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            } else {
                if (!timeInput.equals("")) {
                    if (isTimeInputProper) {
                        dateTimeOutput += ", " + timeInput;
                    } else {
                        dateTimeOutput += ", " + timeInput + " [Note: Invalid time format]";
                    }
                }
            }
        }
    }

    public String getDateTimeInput() {
        return dateTimeInput;
    }

    public String getDateTimeOutput() {
        return dateTimeOutput;
    }

    public abstract String dateTimeDetails();

    @Override
    public boolean isOnDate(String specificDateStr) {
        try {
            LocalDate dateToSearch = LocalDate.parse(specificDateStr);

            if (this.date != null) {
                return this.date.equals(dateToSearch);
            } else {
                return this.dateInput.equals(specificDateStr);
            }
        } catch (Exception e) {
            return this.dateInput.equals(specificDateStr);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + dateTimeDetails();
    }
}
