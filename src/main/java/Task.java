/**
 * The Task class encapsulates all the details of each task.
 */

public class Task {
    private final String message;
    private Boolean completed;

    public static Task createTask(String command, String input) throws NoSuchCommandException {
        switch (command) {
            case "todo":
                return new ToDo(input);
            case "event":
                String[] message_and_timePeriod = input.split("/at ");
                return new Event(message_and_timePeriod[0], message_and_timePeriod[1]);
            case "deadline":
                String[] message_and_endTime = input.split("/by ");
                return new Deadline(message_and_endTime[0], message_and_endTime[1]);
            default:
                throw new NoSuchCommandException("Wrong command");
        }
    }

    public Task(String message){
        this.message = message;
        this.completed = false;
    }

    /**
     * Overrides toString() method.
     * @return String representation of the task object.
     */
    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ")  + "] " + this.message;
    }

    /**
     * Sets the task to completed.
     */
    public void doTask() {
        this.completed = true;
    }
}
