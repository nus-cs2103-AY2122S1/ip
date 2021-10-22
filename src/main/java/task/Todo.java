package task;


/**
 * Represents a todo under Task class.
 * Allows users to get_type and get_task.
 */
public class Todo extends Task {
    private String type;
    private String dateTime;
    private String task;



    /**
     * Constructor to create a todo task for newly inputed messages.
     * Sieve out the task and date separately.
     *
     * @param message String the user input message.
     */
    public Todo (String message) {
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }


    /**
     * Constructor to create a todo task for Duke.txt lines.
     * Sieve out the task and date separately.
     *
     * @param message String the lines in Duke.txt file.
     * @param isDuke Boolean to differentiate between 2 constructors, always true.
     */
    public Todo (String message, Boolean isDuke) {
        super(message);
        this.setDateTime();
        this.setTask2();
        this.setType();
    }

    @Override
    public void setType() {
        this.type = "T";
    }

    /**
     * Sieve out the task + date portion of the message.
     * Only for newly inputed messages.
     *
     */
    @Override
    public void setTask() {
        int startIndex = 0;

        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("o")) {
                startIndex = i + 4;
                break;
            }
        }

        this.task = " " + getMessage().substring(startIndex);
    }

    /**
     * Sieve out the task + date portion of the message.
     * Only for lines in Duke.txt.
     *
     */
    @Override
    public void setTask2() {
        int startIndex = 0;

        for (int i = 0; i < getMessage().length(); i++) {
            if (getMessage().substring(i, i + 1).equals("o")) {
                startIndex = i + 4;
                break;
            }
        }

        this.task = getMessage().substring(startIndex);
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setDateTime() {
        this.dateTime = "";
    }

    @Override
    public String getDateTime() {
        return dateTime;
    }
}
