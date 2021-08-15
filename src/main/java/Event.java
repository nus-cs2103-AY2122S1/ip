import java.util.Scanner;

public class Event extends Task {
    public String taskName = "";
    private boolean state = false;
    public String time = "";

    public Event(String input) {
        Scanner line = new Scanner(input);
        line.next();
        while (line.hasNext()) {
            String currWord = line.next();
            if (currWord.equals("/at")) {
                break;
            }
            taskName = taskName + currWord + " ";
        }
        while (line.hasNext()) {
            time = " " + time + line.next();
        }
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return this.state;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getSymbol() {
        return "[E]";
    }

    @Override
    public String toString() {
        return this.taskName + "(at:" + this.time + ")";
    }
}
