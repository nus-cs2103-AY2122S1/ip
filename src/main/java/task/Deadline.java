package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline under Task class with date-time format.
 * Allows users to get_type and get_task.
 */
public class Deadline extends Task {
    private String type;
    private String task = "";
    private LocalDate dateTime;
    private String dateTime2;


    /**
     * Constructor to create a deadline task for newly inputed messages.
     * Sieve out the task and date separately.
     *
     * @param message String the user input message
     */
    public Deadline (String message) {
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }


    /**
     * Constructor to create a deadline task for Duke.txt lines.
     * Sieve out the task and date separately.
     *
     * @param message String the lines in Duke.txt file.
     * @param isDuke Boolean to differentiate between 2 constructors, always true.
     */
    public Deadline (String message, boolean isDuke) {
        super(message);
        this.setDateTime2();
        this.setTask2();
        this.setType();
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType() {
        this.type = "D";
    }

    /**
     * Sieve out the task + date portion of the message.
     * Only for newly inputed messages.
     *
     */
    @Override
    public void setTask() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("d")) {
                startIndex = i + 8;
                break;
            }
        }
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("/")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = getMessage().substring(startIndex, endIndex)
                + " (by "
                    + this.getDateTime()
                        + ")";
    }

    /**
     * Sieve out the task + date portion of the message.
     * Only for lines in Duke.txt.
     *
     */
    @Override
    public void setTask2() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("d")) {
                startIndex = i + 9;
                break;
            }
        }
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("(")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = getMessage().substring(startIndex, endIndex)
                + " "
                    + this.getDateTime2();
    }

    @Override
    public String getTask() {
        return this.task;
    }

    /**
     * Sieve out the date portion of the message as LocalDate object.
     * Only for newly inputed message.
     *
     */
    @Override
    public void setDateTime() {
        int startIndex = 0;
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("/")) {
                startIndex = i + 4;
                break;
            }
        }
        String dateTiming = getMessage().substring(startIndex);
        this.dateTime = LocalDate.parse(dateTiming);
    }


    /**
     * Sieve out the date portion of the message.
     * Only for Duke.txt messages.
     *
     */
    public void setDateTime2() {
        int length = getMessage().length();
        this.dateTime2 = "("
                + getMessage().substring(length - 15);
    }


    /**
     * Print out the LocalDate object as month date year format.
     * Only for newly inputed messages.
     *
     * @return date string
     *
     */
    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String getDateTime2() {
        return this.dateTime2;
    }

}
