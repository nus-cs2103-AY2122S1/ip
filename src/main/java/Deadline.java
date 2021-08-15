import java.util.Scanner;

public class Deadline extends Task {

    private String deadline;
    private String description = " ";
    private boolean isDone;

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
        if (this.deadline == null) {
            throw new WrongCommandFormatException(
                    "No deadline specified. Please specify a deadline after `/by`"
            );
        } else if (this.description.equals(" ")) {
            throw new WrongCommandFormatException(
                    "No task specified. Please specify a task before `/by`"
            );
        }
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String getStatusIcon() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    @Override
    public String getTaskName() {
        return this.description;
    }

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

    @Override
    public void markComplete() {
        if (this.isDone) {
            System.out.println("`" + this.description + "`" + " is already completed.");
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
