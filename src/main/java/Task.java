import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private String itemName;
    private boolean isDone = false;
    private LocalDateTime dateTime;

    public Task(String str) {
        itemName = str;
    }

    public Task(String str, LocalDateTime dateTime) {
        itemName = str;
        this.dateTime = dateTime;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void changeIsDone(boolean bool) {
        isDone = bool;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + itemName;
    }
}