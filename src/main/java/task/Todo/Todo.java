package task.Todo;

import task.Task;

public class Todo extends Task {
    private String type;
    private String dateTime;
    private String task;


<<<<<<< HEAD
    public Todo (String message) {
=======
    /**
     * Constructor to create a todo task for newly inputed messages
     * Sieve out the task and date separately
     *
     * @param message String the user input message
     */
    public Todo (
            String message) {
>>>>>>> 7f3ded1 (Follow Coding Standard)
        super(message);
        this.setDateTime();
        this.setTask();
        this.setType();
    }

<<<<<<< HEAD
    public Todo (String message, Boolean b) {
=======
    /**
     * Constructor to create a todo task for Duke.txt lines
     * Sieve out the task and date separately
     *
     * @param message String the lines in Duke.txt file
     * @param isDuke Boolean to differentiate between 2 constructors, always true
     */
    public Todo (
            String message,
                Boolean isDuke) {
>>>>>>> 7f3ded1 (Follow Coding Standard)
        super(message);
        this.setDateTime();
        this.setTask2();
        this.setType();
    }

    @Override
    public void setType() {
        this.type = "T";
    }

    @Override
    public void setTask() {
        int startIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("o")) {
                startIndex = i + 4;
                break;
            }
        }
        this.task = " " + message.substring(startIndex);
    }

    @Override
    public void setTask2() {
        int startIndex = 0;
        for (int i = 0; i < this.message.length(); i++) {
            if (this.message.substring(i, i+1).equals("o")) {
                startIndex = i + 4;
                break;
            }
        }
        this.task = message.substring(startIndex);
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
