import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    
    private ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            String entry = String.format("%d. %s\n",
                    i+1, task.toString());
            result.append(entry);
        }
        return result.toString();
    }
    
    public int size() {
        return this.taskList.size();
    }
    
    public String listSchedule(LocalDate dateFilter) {
        ArrayList<Task> filteredTaskList = this.taskList.stream()
                .filter(Task::hasSchedule).collect(Collectors.toCollection(ArrayList::new));
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            String entry = String.format("%d. %s \n",
                    i+1, task.toString());
            result.append(entry);
        }
        return result.toString();

    }
    
    public void doneTask(int index) throws DukeException {
        Task holder = this.taskList.get(index - 1);
        holder.doneTask();
    }
    
    public void deleteTask(int index) throws DukeException {
        String holder = this.taskList.get(index - 1).toString();
        this.taskList.remove(index - 1);
        this.taskList.trimToSize();
        System.out.println("____________________________________________________________\n"
                + "okie! I've removed this annoying task: \n"
                + holder
                + "\nNow you have " + this.taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void addTask(Task task) {
        this.taskList.add(task);
    }
    
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    
}
