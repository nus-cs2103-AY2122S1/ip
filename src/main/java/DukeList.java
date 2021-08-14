import java.util.ArrayList;

public class DukeList {
    private ArrayList<Task> list = new ArrayList<>();

    public String addTask(String input) {
        this.list.add(new Task(input));
        return "\tadded: " + input + "\n";
    }

    public String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;
        for (Task task: list) {
            String temp = "\t" + i + ". " + task.toString() + "\n";
            output.append(temp);
            i++;
        }
        return output.toString();
    }

    public String markTask(int index) {
        return "\tNice! I've marked this task as done:\n\t\t" + list.get(index - 1).markDone();
    }
}
