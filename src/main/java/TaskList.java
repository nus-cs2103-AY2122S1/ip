import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void completeTask(int taskID) {
        Ui.showCompletedMessage();
        this.tasks.get(taskID - 1).markAsDone();
        Ui.showLine();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        Ui.showAddedTask(newTask.toString(), this.tasks.size());
    }

    public void listItems() {
        Ui.showList();
        for (int i = 0; i < this.tasks.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". "
                    + this.tasks.get(i).toString());
        }
        Ui.showLine();
    }

    public void deleteTask(int taskID) throws IndexOutOfBoundsException{
        if (taskID <=0 || taskID > this.tasks.size()) {
            Ui.noTask();
            throw new IndexOutOfBoundsException();
        } else {
            Ui.showSuccessfulDelete();
            System.out.println(this.tasks.get(taskID - 1).toString());
            this.tasks.remove(taskID - 1);
            Ui.showUpdatedNumber(this.tasks.size());
        }
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task getIndividualTask(int id) {
        return this.tasks.get(id);
    }
}
