import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws DukeException {
        if(list.isEmpty()){
            throw new DukeException("Task list is empty");
        }
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task){
        this.list.add(task);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void markAsDone(int index) {
        this.list.get(index).markAsDone();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public int size(){
        return this.list.size();
    }

    public void showTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0 ; i < list.size() ; i++){
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

}
