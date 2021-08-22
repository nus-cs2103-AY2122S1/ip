import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    TaskList(ArrayList<Task> list){
        this.list = list;
    }

    public void add(Task t){
        list.add(t);
    }

    public Task get(int getNum){
        return list.get(getNum);
    }

    public int size(){
        return list.size();
    }

    public void remove(int delNum){
        list.remove(delNum);
    }

}
