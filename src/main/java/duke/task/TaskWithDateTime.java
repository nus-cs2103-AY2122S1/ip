package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents tasks on a date/time
 */
public abstract class TaskWithDateTime extends Task {
    private String dateTimeInput;
    private String dateInput;
    private String timeInput;
    private LocalDate date;
    private LocalTime time;
    private String dateTimeOutput; // DateTime (of the task) to be printed

    /**
     * Initializes an instance of TaskWithDateTime class with task type, description, and date/time.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput The input date/time by which the task has to be completed
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput) {
        this(type, description, dateTimeInput, false);
    }

    /**
     * Initializes an instance of TaskWithDateTime class with task type, description,
     * date/time, and whether the task has been completed.
     * @param type Type of task
     * @param description Task description
     * @param dateTimeInput The input date/time by which the task has to be completed
     * @param isDone A flag to indicate if the task has been completed
     */
    public TaskWithDateTime(TaskType type, String description, String dateTimeInput, boolean isDone) {
        super(type, description, isDone);
        this.dateTimeInput = dateTimeInput;
        processDateTimeInput();
    }

    /**
     * Processes the input date/time string to find the specific details of the
     * date and time of the task.
     */
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

    /**
     * Gets input date/time string.
     * @return Inout date/time string
     */
    public String getDateTimeInput() {
        return dateTimeInput;
    }

    /**
     * Gets the date/time information in output format.
     * @return Output date/time string
     */
    public String getDateTimeOutput() {
        return dateTimeOutput;
    }

    public abstract String dateTimeInfo();

    /**
     * Finds if the date/time of a task is equal to a specified date/time.
     * @param dateStr The specified date/time
     * @return A flag to indicate if the date/time of the task is same as the given value
     */
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

    /**
     * Gets a string representation of the date/time info.
     * @return A string representation of the date/time info
     */
    @Override
    public String toString() {
        return super.toString() + " " + dateTimeInfo();
    }
}
