import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskDateAndTime {
    private String userInputDateAndTime;
    private LocalDate taskDateAndTime;

    public TaskDateAndTime(String userInputDateAndTime) {
        this.userInputDateAndTime = userInputDateAndTime;
        isValidDate();
    }

    public boolean isValidDate() {
        String[] userInputDate = userInputDateAndTime.split("/");
        if(userInputDate.length == 3) {
            taskDateAndTime = LocalDate.parse(userInputDate[2] + "-" +userInputDate[1] + "-" + userInputDate[0]);
        }
        return userInputDate.length == 3;
    }

    @Override
    public String toString() {
        return taskDateAndTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
