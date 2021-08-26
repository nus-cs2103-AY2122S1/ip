package duke;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList() {
        this.list = new ArrayList<>();
    }


    public void addTask(Task task){
        this.list.add(task);
    }

    public void removeTask(int i){
        this.list.remove(i);
    }

    public TaskList findTask(String input){
        TaskList searchResult = new TaskList();
        for(int i = 0; i < list.size(); i++){
            Task task = list.get(i);
            if(task.toString().contains(input)){
                searchResult.addTask(task);
            }
        }
        return searchResult;
    }


    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}