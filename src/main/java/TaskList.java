import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList;
    private Ui ui;

    public TaskList() {
        taskList = new ArrayList<>();
        ui = new Ui();
    }

    public void addTask(String task) {
        taskList.add(task);
        ui.displayLine();
        System.out.println("added: " + task);
        ui.displayLine();
    }

    public void listTasks() {
        ui.displayLine();
        int index = 1;
        for (String task : taskList) {
            System.out.println(String.format("%s. %s", index, task));
            index++;
        }
        ui.displayLine();
    }
}
