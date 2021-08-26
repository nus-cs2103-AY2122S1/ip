package task.event;

import task.Task;

public class Event extends Task {
    private String type;
    private String date_time = "";
    private String task;


    public Event (String message) {
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }


<<<<<<< HEAD
    //constructor for Duke.txt
    public Event (String message, boolean b) {
=======
    /**
     * Constructor to create an event task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param isDuke Boolean to differentiate between 2 constructors, always true
     */
    public Event (String message, boolean isDuke) {
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
    public void setTask() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("t")) {
                startIndex = i + 2;
                break;
            }
        }
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("/")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = " "
                + message.substring(startIndex,endIndex)
                    + this.getDateTime();
    }

    //set task for Duke.txt
    @Override
    public void setTask2() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("e")) {
                startIndex = i + 6;
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
                + this.getDateTime();
    }


    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public void setType() {
        this.type = "E";
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
        this.date_time = " (at "
                + message.substring(startIndex)
                    + ")";
    }

<<<<<<< HEAD
    public void set_date_time2() {
        int start_index = 0;
=======
    /**
     * Sieve out the date portion of the message
     * Only for Duke.txt lines
     *
     */
    public void setDateTime2() {
        int startIndex = 0;
>>>>>>> 7f3ded1 (Follow Coding Standard)
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("(")) {
                startIndex = i - 1;
                break;
            }
        }
        this.date_time = message.substring(startIndex);
    }

    @Override
    public String getDateTime() {
        return date_time;
    }
}
