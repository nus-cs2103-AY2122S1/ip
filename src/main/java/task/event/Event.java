package task.event;

import task.Task;


/**
 * Represents an event under Task class
 * Allows users to get_type and get_task
 */
public class Event extends Task {
    public String type;
    public String date_time = "";
    public String task;

    /**
     * Constructor to create an event task for newly inputed messages
     * Sieve out the task and date separately
     *
     * @param message String the user input message
     */
    public Event (String message) {
        super(message);
        this.set_date_time();
        this.set_task();
        this.set_type();
    }


    /**
     * Constructor to create an event task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param b Boolean to differentiate between 2 constructors, always true
     */
    public Event (String message, boolean b) {
        super(message);
        this.set_date_time2();
        this.set_task2();
        this.set_type();
    }

    @Override
    public String get_type() {
        return this.type;
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
            if (this.message.substring(i, i+1).equals("t")) {
                start_index = i + 2;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = " " + message.substring(start_index,end_index) + this.getDate_time();
    }

    /**
     * Sieve out the task + date portion of the message
     * Only for Duke.txt lines
     *
     */
    @Override
    public void set_task2() {
        int start_index = 0;
        int end_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("e")) {
                start_index = i + 6;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                end_index = i - 1;
                break;
            }
        }
        this.task = message.substring(start_index,end_index) + this.getDate_time();
    }


    @Override
    public String get_task() {
        return this.task;
    }

    @Override
    public void set_type() {
        this.type = "E";
    }

    /**
     * Sieve out the date portion of the message
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
        this.date_time = " (at " + message.substring(start_index,message.length()) + ")";
    }

    /**
     * Sieve out the date portion of the message
     * Only for Duke.txt lines
     *
     */
    public void set_date_time2() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                start_index = i - 1;
                break;
            }
        }
        this.date_time = message.substring(start_index,message.length());
    }

    @Override
    public String getDate_time() {
        return date_time;
    }
}
