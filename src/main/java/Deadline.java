import java.util.Scanner;

public class Deadline extends Task {

    private String deadline;
    private String description = " ";

    public Deadline(String description, boolean isDone) {
        super(description, isDone);
        Scanner s = new Scanner(description);
        while (s.hasNext()) {
            String next = s.next();
            if (next.equals("/at")) {
                System.out.println("Wrong keyword used. Please try again with /by");
            } else if (next.equals("/by")) {
                Scanner s2 = new Scanner(s.nextLine());
                if (s2.hasNextLine()) {
                    this.deadline = s2.nextLine();
                } else {
                    System.out.println("No deadline specified. Please specify a deadline after `/d`");
                }
            } else {
              this.description += next;
            }
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
