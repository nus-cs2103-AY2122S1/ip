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

    public String addTodo(String input) {
        Task t = new Todo(input);
        tasks.add(t);
        return line + "I've added this task:\n" + t +"\n" + "You have " + tasks.size() + " tasks left!\n" + line;
    }

    public String getListMessage() {
        String listMessage = "Here are your tasks... if you choose to do it...\n";

        for (int i = 0; i < tasks.size(); i++) {
            listMessage = listMessage + (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return line + listMessage + line;
    }

    public String completeTask(int index) {
        Task complete = tasks.get(index - 1);
        complete.completeTask();

        return line + "Well done! You finally completed" + complete.getName() + "!\n" + line;
    }

    public String addDeadline(String name, String deadline) {
        Task t = new Deadline(name, deadline);
        tasks.add(t);
        return line + "I've added this task:\n" + t +"\n" + "You have " + tasks.size() + " tasks left!\n" + line;
    }

    public String addEvent(String name, String time) {
        Task t = new Event(name, time);
        tasks.add(t);
        return line + "I've added this task:\n" + t +"\n" + "You have " + tasks.size() + " tasks left!\n" + line;
    }

    public String getCommand() {
        return line + "Unknown Command!\n" + line;
    }

}
