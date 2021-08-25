package duke;

public class ExitMessage extends DukeMessage{
    public void display() {
        TaskListCsvHandler.insertTasks();
        System.out.println("Ram Ram!");
        Duke.conversationState = false;
    }
}
