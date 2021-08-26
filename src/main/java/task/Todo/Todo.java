package task.Todo;

import task.Task;

/**
 * Represents a todo under Task class
 * Allows users to get_type and get_task
 */
public class Todo extends Task {
    public String type;
    public String date_time;
    public String task;


    /**
     * Constructor to create a todo task for newly inputed messages
     * Sieve out the task and date separately
     *
     * @param message String the user input message
     */
    public Todo (String message) {
        super(message);
        this.set_date_time();
        this.set_task();
        this.set_type();
    }

    /**
     * Constructor to create a todo task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param b Boolean to differentiate between 2 constructors, always true
     */
    public Todo (String message, Boolean b) {
        super(message);
        this.set_date_time();
        this.set_task2();
        this.set_type();
    }

    @Override
    public String get_type() {
        return this.type;
    }

    @Override
    public void set_type() {
        this.type = "T";
    }

    /**
     * Sieve out the task + date portion of the message
     * Only for newly inputed messages
     *
     */
    @Override
    public void set_task() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("o")) {
                start_index = i + 4;
                break;
            }
        }
        this.task = " " + message.substring(start_index,this.message.length());
    }

    /**
     * Sieve out the task + date portion of the message
     * Only for lines in Duke.txt
     *
     */
    @Override
    public void set_task2() {
        int start_index = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("o")) {
                start_index = i + 4;
                break;
            }
        }
        this.task = message.substring(start_index,this.message.length());
    }

    @Override
    public String get_task() {
        return this.task;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void set_date_time() {
        this.date_time = "";
    }

    @Override
    public String getDate_time() {
        return date_time;
    }
}
