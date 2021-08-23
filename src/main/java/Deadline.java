import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{
    private LocalDate deadlineDate;
    private LocalTime deadlineTime;
    public Deadline (String task, String taskTime) {
        super(task);
        String[] dateAndTime = taskTime.split(" ");
        System.out.println(dateAndTime[0]);
        System.out.println(dateAndTime[1]);
        this.deadlineDate = LocalDate.parse(dateAndTime[0]);
        this.deadlineTime = LocalTime.parse(dateAndTime[1]);
    }

    public Deadline (String task, boolean done, String taskTime) {
        super(task, done);
        String[] dateAndTime = taskTime.split(" ");
        this.deadlineDate = LocalDate.parse(dateAndTime[0]);
        this.deadlineTime = LocalTime.parse(dateAndTime[1]);
    }

    @Override
    public String toString() {
        String finished = " ";
        if (this.isDone()) {
            finished = "X";
        }
        return "[D]" + "[" + finished + "] " + this.getTaskName() + " (by: " + this.outputTaskTime() + ", "
                + deadlineTime.toString() + ")";
    }

    @Override
    public String toStoredString() {
        int finished = this.isDone() ? 1 : 0;
        return "D | " + finished + " | " + this.getTaskName() + " | " + deadlineDate + " " + deadlineTime;
    }

    private String outputTaskTime() {
        return deadlineDate.getMonth().toString() + " " + deadlineDate.getDayOfMonth() + " " + deadlineDate.getYear();
    }
}
