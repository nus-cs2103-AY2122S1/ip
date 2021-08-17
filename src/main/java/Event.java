import java.util.Scanner;

/**
 * Class to represent an Event task.
 * An event has an additional component of a date.
 *
 * @author Houten Teo
 * @version CS2103T week 2
 */
public class Event extends Task {

    private String description = " ";
    private String timeframe;
    private boolean isDone;

    /**
     * Constructor for the Event task.
     * @param description the description of the task.
     * @param isDone whether or not the task is completed.
     * @throws WrongCommandFormatException thrown when the user enters the
     *                                     command with the wrong format.
     */
    public Event(String description, boolean isDone) throws WrongCommandFormatException {
        super(description, isDone);
        Scanner s = new Scanner(description);
        while (s.hasNext()) {
            String next = s.next();
            if (next.equals("/by")) {
                throw new WrongCommandFormatException("Wrong keyword used. Please try again with /at");
            } else if (next.equals("/at")) {
                if (s.hasNextLine()) {
                    this.timeframe = s.nextLine();
                } else {
                    throw new WrongCommandFormatException(
                            "No timeframe specified. Please specify a deadline after `/at`"
                    );
                }
            } else {
                this.description += next;
            }
        }
        if (this.description.equals(" ")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please specify a task before `/at`"
            );
        } else if (this.timeframe == null) {
            throw new WrongCommandFormatException(
                    "No timeframe specified. Please specify a timeframe after `/at`"
            );
        }
        this.isDone = isDone;
    }

    /**
     * Overridden method to get the specific type icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Overriden method to get the status icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Overridden toString method.
     * @return The string representation of the event object.
     *         The type icon, status icon and decription arranged
     *         in a line.
     */
    @Override
    public String toString() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        return typeIcon
                + " "
                + statusIcon
                + this.description
                + " (at:"
                + this.timeframe
                + ")";
    }

    /**
     * Overridden markComplete method.
     * If the task has already been completed, inform the user.
     * Else indicate that the task has been correctly marked.
     */
    @Override
    public void markComplete() {
        if (this.isDone) {
            System.out.println("`" + this.description.substring(1) + "`" + " is already completed.");
        } else {
            System.out.println("Completed: "
                    + this.description
                    + " (by:"
                    + this.timeframe + ")"
            );
            System.out.println("WEW that's another task completed");
            this.isDone = true;
        }
    }
}
