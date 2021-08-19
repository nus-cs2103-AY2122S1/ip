public class AddTask extends DukeMessage{
    private String userString;

    public AddTask(String userStr) {
        userString = userStr;
        TaskList.getTaskList().addTask(userStr);
    }

    public void display() {
        System.out.println("added: " + userString);
        Duke.conversationState = true;
    }
}


