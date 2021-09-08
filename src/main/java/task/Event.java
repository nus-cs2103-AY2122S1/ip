package task;


/**
 * Represents an event under Task class.
 * Allows users to get_type and get_task.
 */
public class Event extends Task {
    private String type;
    private String date_time = "";
    private String task;

    /**
     * Constructor to create an event task for newly inputed messages.
     * Sieve out the task and date separately.
     *
     * @param message String the user input message.
     */
    public Event (String message) {
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }



    /**
     * Constructor to create an event task for Duke.txt lines.
     * Sieve out the task and date separately.
     *
     * @param message String the lines in Duke.txt file.
     * @param isDuke Boolean to differentiate between 2 constructors, always true.
     */
    public Event (String message, boolean isDuke) {
        super(message);
        this.setDateTime2();
        this.setTask2();
        this.setType();
    }

    @Override
    public String getType() {
        return this.type;
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
            if (getMessage().substring(i, i + 1).equals("t")) {
                startIndex = i + 2;
                break;
            }
        }
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("/")) {
                endIndex = i - 1;
                break;
            }
        }
        this.task = " " + getMessage().substring(startIndex, endIndex)
                + this.getDateTime();
    }

    /**
     * Sieve out the task + date portion of the message.
     * Only for Duke.txt lines.
     *
     */
    @Override
    public void setTask2() {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("e")) {
                startIndex = i + 6;
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

    /**
     * Sieve out the date portion of the message.
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
        this.date_time = " (at "
                + getMessage().substring(startIndex)
                    + ")";
    }


    /**
     * Sieve out the date portion of the message.
     * Only for Duke.txt lines.
     *
     */
    public void setDateTime2() {
        int startIndex = 0;
        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("(")) {
                startIndex = i - 1;
                break;
            }
        }
        this.date_time = getMessage().substring(startIndex);
    }

    @Override
    public String getDateTime() {
        return date_time;
    }
}
