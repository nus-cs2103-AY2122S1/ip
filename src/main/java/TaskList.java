import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list;
    private int numTask;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task t) {
        list.add(t);
        numTask++;
    }

    public Task deleteTask(int i) {
        numTask--;
        return list.remove(i);
    }

    public void printList() {
        System.out.println("Here are the tasks in your list: \n");
        for(int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println( index + ". " + list.get(i));
        }
    }

    public void setDone(int i) {
        this.list.get(i).setDone();
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public int getNumTask() {
        return numTask;
    }






}
