package Duke.task;

import Duke.exception.NoSuchTaskException;
import Duke.ui.UI;

import java.util.ArrayList;


public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public void addTask(Task task){
        list.add(task);
    }

    public Task getTask(int taskNumber){
        return this.list.get(taskNumber - 1);
    }

    public int getTaskAmount(){
        return list.size();
    }

    public String listTaskAmount(){
        return "\nNow you have " + list.size() + " task(s) in the list.\n";
    }

    public void markDone(int taskPos) throws NoSuchTaskException {
        if(taskPos >= 0 && taskPos < list.size()) {
            list.get(taskPos).markComplete();
        }else{
            throw new NoSuchTaskException();
        }
    }

    public void deleteTask(int taskPos) throws NoSuchTaskException {

        if(taskPos >= 0 && taskPos < list.size()) {
            Task temp = list.get(taskPos);
            list.remove(taskPos);
            UI.printTaskDeleted(temp, this);
        }else{
            throw new NoSuchTaskException();
        }
    }

}
