import java.util.Scanner;

public class Deadline extends Task {

    private String deadline;
    private String description = " ";

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
                            "No deadline specified. Please specify a deadline after `/d`"
                    );
                }
            } else {
              this.description += next;
            }
        }
        if (this.deadline == null) {
            throw new WrongCommandFormatException(
                    "No deadline specified. Please specify a deadline after `/d`"
            );
        }
    }

    @Override
    public String getTypeIcon() {
        return "[D]";
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
}
