package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class TaskWithDateTime extends Task {
    private String dateTimeInput;
    private String dateInput;
    private String timeInput;
    private LocalDate date;
    private LocalTime time;
    private String dateTimeOutput; // DateTime (of the task) to be printed

    public TaskWithDateTime(TaskType type, String description, String dateTimeInput) {
        this(type, description, dateTimeInput, false);
    }

    public TaskWithDateTime(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, isDone);
        this.dateTimeInput = dateTimeInput;
        processDateTimeInput();
    }

    private void processDateTimeInput() {
        boolean isTimeInputProper = true;

        String[] dateTimeInputParts = dateTimeInput.split("\\s+", 2);

        if (dateTimeInputParts.length == 2) {
            dateInput = dateTimeInputParts[0].trim();
            timeInput = dateTimeInputParts[1].trim();

            // Omit any additional whitespaces in between date and time inputs
            dateTimeInput = dateInput + " " + timeInput;
        } else {
            dateInput = dateTimeInputParts[0].trim();
            timeInput = "";

            // Omit any additional whitespaces that comes with date input
            dateTimeInput = dateInput;
        }

        // Process dateInput
        try {
            date = LocalDate.parse(dateInput);
        } catch (Exception e) {
            date = null;
        }

        // Process timeInput
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

        // Generate dateTimeOutput
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

    public abstract String dateTimeInfo();

    @Override
    public boolean isOnDate(String dateStr) {
        try {
            LocalDate dateToSearch = LocalDate.parse(dateStr);

            if (date != null) {
                return date.equals(dateToSearch);
            } else {
                return dateInput.equals(dateStr);
            }
        } catch (Exception e) {
            return dateInput.equals(dateStr);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + dateTimeInfo();
    }
}
