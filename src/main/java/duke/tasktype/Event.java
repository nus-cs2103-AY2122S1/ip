package duke.tasktype;

import java.util.Scanner;

import duke.Ui;
import duke.exception.WrongCommandFormatException;

/**
 * Class to represent an duke.tasks.Event task.
 * An event has an additional component of a date.
 *
 * @author Houten Teo
 * @version CS2103T week 6
 */
public class Event implements Task {

    private final String taskType = "EVENT";
    private String command;
    private String description = " ";
    private String timeframe;
    private boolean isDone;

    /**
     * Constructor for the duke.tasks.Event task.
     * @param description the description of the task.
     * @param isDone whether the task is completed.
     * @throws WrongCommandFormatException thrown when the user enters the
     *                                     command with the wrong format.
     */
    public Event(String description, boolean isDone) throws WrongCommandFormatException {
        this.command = description;
        Scanner s = new Scanner(description);

        while (s.hasNext()) {
            readCommand(s);
        }

        if (this.description.equals(" ")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please specify a task before `/at`"
            );
        } else if (this.timeframe == null) {
            throw new WrongCommandFormatException(
                    "No timeframe specified. Please specify a timeframe after `/at`"
            );
        } else {
            this.isDone = isDone;
        }
    }

    /**
     * Reads the event command passed into the constructor
     * @param s The scanner containing the command
     * @throws WrongCommandFormatException
     */
    public void readCommand(Scanner s) throws WrongCommandFormatException {
        String next = s.next();
        if (next.equals("/by")) {
            throw new WrongCommandFormatException("Wrong keyword used. Please try again with /at");
        } else if (next.equals("/at")) {
            parseTimeframe(s);
        } else {
            this.description += next + " ";
        }
    }

    /**
     * Checks if there is any input for the timeframe of the event.
     * If not, throws an exception.
     * @param s The scanner containing the timeframe.
     * @throws WrongCommandFormatException
     */
    public void parseTimeframe(Scanner s) throws WrongCommandFormatException {
        if (s.hasNextLine()) {
            this.timeframe = s.nextLine();
        } else {
            throw new WrongCommandFormatException(
                    "No timeframe specified. Please specify a deadline after `/at`"
            );
        }
    }

    /**
     * Returns the timeframe of this event.
     * @return The timeframe of this event.
     */
    public String getTimeframe() {
        return this.timeframe;
    }

    /**
     * Returns the specific type icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }

    /**
     * Returns the status icon.
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
     * Returns the event description.
     * @return The event description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the event task.
     * @return The string representation of the event task.
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
     * Marks the task as completed
     * If the task has already been completed, inform the user.
     * Else indicate that the task has been correctly marked.
     */
    @Override
    public String markComplete() {
        if (this.isDone) {
            return Ui.getTaskAlrCompletedMessage(this);
        } else {
            this.isDone = true;
            return Ui.getMarkCompleteEventMessage(this);
        }
    }

    /**
     * Converts the task into string format to be stored in Data.txt
     * @return The string representation of the task
     */
    @Override
    public String createData() {
        String data = getTypeIcon()
                + ""
                + getStatusIcon()
                + " "
                + this.command;
        return data;
    }

    /**
     * Returns the task type.
     * @return The string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return this.taskType;
    }

    /**
     * Checks if the task has been marked as completed.
     * @return True if the task is completed and false otherwise.
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }
}
