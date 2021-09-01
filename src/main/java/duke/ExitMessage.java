package duke;

public class ExitMessage extends DukeMessage{
    public String display() {
        TaskListCsvHandler.insertTasks();
        return "Ram Ram!";
    }
}
