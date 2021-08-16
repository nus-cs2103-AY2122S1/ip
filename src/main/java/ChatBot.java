import java.util.ArrayList;

public class ChatBot {

    static String line = "______________________________________\n";
    private ArrayList<Task> tasks = new ArrayList<>();

    public String getStartMessage() {
        return line + "Hello! I'm Duke\n" + "What can I do for you?\n" + line;
    }

    public String getExitMessage() {
        return line + "bye! for now...\n" + line;
    }

    public String addItems(String input) {
        Task t = new Task(input);
        tasks.add(t);
        return line + "added: " + input +"\n" + line;
    }

    public String getListMessage() {
        String listMessage = "Here are your tasks... if you choose to do it...\n";

        for (int i = 0; i < tasks.size(); i++) {
            String status = tasks.get(i).getDone();
            String message = tasks.get(i).getMessage();
            listMessage = listMessage + (i + 1) + ".[" + status + "] " + message + "\n";
        }
        return line + listMessage + line;
    }

    public String completeTask(int index) {
        Task complete = tasks.get(index - 1);
        complete.completeTask();

        return line + "Well done! You finally completed " + complete.getMessage() + "!\n" + line;
    }

}
