import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> list;

    public TodoList() {
        this.list = new ArrayList<Task>();
    }

    public String getList() {
        ArrayList<Task> currentList = this.list;
        StringBuilder output = new StringBuilder("Wahseh, these are all the tasks you haven't do ley! \n      ");
        int i = 1;
        for (Task task : currentList) {
            String checkBox = " [" + task.getStatus() + "] ";
            output.append(i).append(". ").append(checkBox).append(task.name).append("\n      ");
            i += 1;
        }

        return output.toString();
    }

    public void insertTask(String input) {
        Task task = new Task(input);
        list.add(task);
    }

    public Task complete(int index) {
        return list.get(index).completeTask();
    }

}
