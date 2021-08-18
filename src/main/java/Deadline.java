public class Deadline extends Task {
    protected String toCompleteBy;
    protected String taskType;

    public Deadline(String description, String toCompleteBy) {
        super(description);
        this.toCompleteBy = toCompleteBy;
        this.taskType = "D";
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
        System.out.println("[" + this.taskType + "][" + this.getStatusIcon() + "] " + this.description);
    }

    public static String getDeadlineDescription(String input) throws MissingDueDateDescriptionException {
        String[] strArr = input.split(" /by", 2);
        if (strArr.length < 2) {
            System.out.println("---------------------------------------------");
            System.out.println("OOPS!!! The due date of a deadline cannot be empty.");
            System.out.println("---------------------------------------------");
            throw new MissingDueDateDescriptionException();
        } else {
            return strArr[0] + " (by:" + strArr[1] + ")";
        }
    }

    public static String getDueDate(String input) {
        String[] strArr = input.split("/by", 2);
        return strArr[1];
    }
}
