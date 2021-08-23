package duke.tasks;

public class Events extends Task {
    protected final String dayTime;

    /**
     * Contructor. Default having the isDone parameter to be set as false.
     * 
     * @param description description of the current Events object.
     * @param dayTime     description of the time and location of the current Events
     *                    object.
     */
    public Events(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    /**
     * Second Constructor which takes in additional boolean, to be able to set the
     * initial boolean status.
     * 
     * @param description of the current Events object.
     * @param dayTime     description of the time and location of the current Events
     *                    object.
     * @param isDone      determine whether the task is completed or not.
     */
    public Events(String description, String dayTime, boolean isDone) {
        super(description, isDone);
        this.dayTime = dayTime;
    }

    /**
     * Retrieves the description of the time and location of the current Events
     * object.
     * 
     * @return a String which describes the time and location of the current Events
     *         object.
     */
    public String getDayTime() {
        return this.dayTime;
    }

    /**
     * Retrieves the symbol of the current object. Different object and child have
     * different symbols that represents them.
     * 
     * @return a fixed parent symbol "E".
     */
    @Override
    public String getSymbol() {
        return "E";
    }

    /**
     * Marks the current Events as done.
     * 
     * @return a new Events object with the same description and dayTime, but setting
     *         isDone property to be true
     */
    @Override
    public Events markAsDone() {
        return new Events(this.getDescription(), this.getDayTime(), true);
    }

    /**
     * Describes the current Events object.
     * 
     * @return a description of the current Events object.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (at: " + this.getDayTime() + ")";
    }
}
