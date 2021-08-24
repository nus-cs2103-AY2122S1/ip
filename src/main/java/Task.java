import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    protected String eventType;
    protected boolean isDone;
    protected String description;
    protected LocalDate date;
    protected int time;

    /**
     * Basic constructor for task (used during subclass instance)
     */
    public Task(){
        setNotDone();
    }

    /**
     * Basic constructor for task, takes in a string that describes the task
     * @param description contains details of the task description
     */
    public Task(String description){
        setNotDone();
        setDescription(description);
    }

    /**
     * Basic constructor for an empty task
     * @return Task that has an empty description
     */
    public static Task empty(){
        return new Task("");
    }

    /**
     * Sets the description of the task
     * @param input set task description
     */
    protected void setDescription(String input){
        this.description = input;
    }

    /**
     * Takes in a string that describes the time,
     * converts the time into LocalDate and sets the time of the task
     * @param input set time
     */
    protected void setDate(String input) throws InvalidInputException {
        if (input == null){
            this.date = null;
            this.time = -1;
        } else {
            try {
                String[] timeFormat = input.trim().split(" ");
                this.date = LocalDate.parse(timeFormat[0]);
                int hoursMins = Integer.parseInt(timeFormat[1]);
                if (hoursMins <2401 && hoursMins > 999) {
                    this.time = hoursMins;
                } else {
                    throw new InvalidInputException("Invalid time format (use 24hr format)");
                }
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Wrong date format(use YYYY-MM-DD)");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException("Missing time");
            }
        }
    }

    /**
     * Takes a string that indicates event type
     * @param input set event type
     */
    protected void setEventType(String input){
        this.eventType = "[" + input + "]";
    }

    /**
     * Sets the Task state to true
     * @return Task instance itself
     */
    public Task setDone(){
        this.isDone = true;
        return this;
    }

    /**
     * Sets the Task state to false
     * @return Task instance itself
     */
    public Task setNotDone(){
        this.isDone = false;
        return this;
    }

    /**
     * Returns task description
     * @return String
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the time attached to task
     * @return time
     */
    public String getDate() {
        return this.date.toString() + " " + this.time;
    }

    /**
     * Returns a string that describes the instance
     * @return String containing details of the task
     */
    @Override
    public String toString(){
        String state = isDone ? "[X] " : "[ ] ";
        return this.eventType + state + this.description;
    }
}

