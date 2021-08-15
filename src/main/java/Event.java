import java.util.Scanner;

public class Event extends Task {

    private String description = "";
    private String timeframe;
    private boolean isDone;

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
        if (this.timeframe == null) {
            throw new WrongCommandFormatException(
                    "No timeframe specified. Please specify a timeframe after `/at`"
            );
        }
        this.isDone = isDone;
    }

    @Override
    public String getTypeIcon() {
        return "[E]";
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
                + " (at:"
                + this.timeframe
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
                    + this.timeframe + ")"
            );
            System.out.println("WEW that's another task completed");
            this.isDone = true;
        }
    }
}
