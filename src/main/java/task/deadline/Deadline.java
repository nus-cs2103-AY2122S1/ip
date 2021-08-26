package task.deadline;

import task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline under Task class with date-time format
 * Allows users to get_type and get_task
 */
public class Deadline extends Task {
    public String type;
    public String task = "";
    public LocalDate date_time;
    public String date_time2;


    /**
     * Constructor to create a deadline task for newly inputed messages
     * Sieve out the task and date separately
     *
     * @param message String the user input message
     */
    public Deadline (String message) {
        super(message);
        this.set_date_time();
        this.set_task();
        this.set_type();
    }

    /**
     * Constructor to create a deadline task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param b Boolean to differentiate between 2 constructors, always true
     */
    public Deadline (String message, boolean b) {
        super(message);
        this.set_date_time2();
        this.set_task2();
        this.set_type();
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_type() {
        this.type = "D";
    }

    /**
     * Sieve out the task + date portion of the message
     * Only for newly inputed messages
     *
     */
    @Override
    public void set_task() {
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("d")) {
                start_index = i + 8;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = message.substring(start_index,end_index) + " (by " + this.get_date_time() + ")";
    }

    /**
     * Sieve out the task + date portion of the message
     * Only for lines in Duke.txt
     *
     */
    @Override
    public void set_task2() {
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("d")) {
                start_index = i + 9;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = message.substring(start_index,end_index) + " " + this.get_date_time2();
    }

    @Override
    public String get_task() {
        return this.task;
    }

    /**
     * Sieve out the date portion of the message as LocalDate object
     * Only for newly inputed message
     *
     */
    @Override
    public void set_date_time() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                start_index = i + 4;
                break;
            }
        }
        String date_timing = message.substring(start_index);
        this.date_time = LocalDate.parse(date_timing);
    }

    /**
     * Sieve out the date portion of the message
     * Only for Duke.txt messages
     *
     */
    public void set_date_time2() {
        int length = this.message.length();
        this.date_time2 = "(" + message.substring(length - 15);
    }

    /**
     * Print out the LocalDate object as month date year format
     * Only for newly inputed messages
     *
     * @return date string
     *
     */
    public String get_date_time() {
        return this.date_time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String get_date_time2() {
        return this.date_time2;
    }

}
