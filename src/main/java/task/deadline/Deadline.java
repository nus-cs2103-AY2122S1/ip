package task.deadline;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String type;
    private String task = "";
    private LocalDate dateTime;
    private String dateTime2;


    public Deadline (String message) {
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }

<<<<<<< HEAD

    public Deadline (String message, boolean b) {
=======
    /**
     * Constructor to create a deadline task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param isDuke Boolean to differentiate between 2 constructors, always true
     */
    public Deadline (String message, boolean isDuke) {
>>>>>>> 7f3ded1 (Follow Coding Standard)
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

    @Override
    public void setTask() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("d")) {
                startIndex = i + 8;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = message.substring(startIndex,endIndex)
                + " (by "
                    + this.getDateTime()
                        + ")";
    }

    @Override
    public void setTask2() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("d")) {
                startIndex = i + 9;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = message.substring(startIndex,endIndex)
                + " "
                    + this.getDateTime2();
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void setDateTime() {
        int startIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                startIndex = i + 4;
                break;
            }
        }
        String dateTiming = message.substring(startIndex);
        this.dateTime = LocalDate.parse(dateTiming);
    }

<<<<<<< HEAD
    public void set_date_time2() {
=======
    /**
     * Sieve out the date portion of the message
     * Only for Duke.txt messages
     *
     */
    public void setDateTime2() {
>>>>>>> 7f3ded1 (Follow Coding Standard)
        int length = this.message.length();
        this.dateTime2 = "("
                + message.substring(length - 15);
    }

<<<<<<< HEAD
    public String get_date_time() {
        return this.date_time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
=======
    /**
     * Print out the LocalDate object as month date year format
     * Only for newly inputed messages
     *
     * @return date string
     *
     */
    public String getDateTime() {
        return this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
>>>>>>> 7f3ded1 (Follow Coding Standard)
    }

    public String getDateTime2() {
        return this.dateTime2;
    }

}
