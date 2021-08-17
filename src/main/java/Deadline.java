import java.util.Scanner;

/**
 * Class to represent a task with a deadline.
 * Has an additional component of a deadline.
 *
 * @author Houten Teo
 * @version CS2103T week 2
 */
public class Deadline extends Task {

    private String deadline;
    private String description = " ";
    private boolean isDone;

    /**
     * constructor for the deadline class.
     * @param description the description of the task.
     * @param isDone whether or not the task is completed .
     * @throws WrongCommandFormatException Thrown when the user enters the
     *                                      command with the wrong format.
     */
    public Deadline(String description, boolean isDone) throws WrongCommandFormatException{
        super(description, isDone);
        Scanner s = new Scanner(description);
        while (s.hasNext()) {
            String next = s.next();
            if (next.equals("/at")) {
                throw new WrongCommandFormatException("Wrong keyword used. Please try again with /by");
            } else if (next.equals("/by")) {
                if (s.hasNextLine()) {
                    this.deadline = s.nextLine();
                } else {
                    throw new WrongCommandFormatException(
                            "No deadline specified. Please specify a deadline after `/by`"
                    );
                }
            } else {
              this.description += next;
            }
        }
         if (this.description.equals(" ")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please specify a task before `/by`"
            );
        } else if (this.deadline == null) {
            throw new WrongCommandFormatException(
                    "No deadline specified. Please specify a deadline after `/by`"
            );
        }
        this.isDone = isDone;
    }

    /**
     * Overridden method to get the type icon.
     * @return The String representation of the icon.
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Overridden method to get the type icon.
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
     * Overridden toString method
     * @return The String representation of the deadline object
     *          The type icon, status icon and description arranged
     *          in a line.
     */
    @Override
    public String toString() {
        String typeIcon = getTypeIcon();
        String statusIcon = getStatusIcon();
        return typeIcon
                + " "
                + statusIcon
                + this.description
                + " (by:"
                + this.deadline
                + ")";
    }

    /**
     * Overridden markComplete method to mark the deadline task as complete.
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
                            + this.deadline + ")"
            );
            System.out.println("You didn't overshoot the deadline right?");
            this.isDone = true;
        }
    }
}
