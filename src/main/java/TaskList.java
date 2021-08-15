import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList = new ArrayList<>();

    public void add(String task) {
        taskList.add(task);
        Duke.printMessage("Added: " + task);
    }

    public void list() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". " + taskList.get(i) + "\n");
        }

        // delete the last new line character
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        Duke.printMessage(stringBuilder.toString());
    }
}
