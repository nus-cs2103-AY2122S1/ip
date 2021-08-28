import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> userList;
    
    public TaskList() {
        userList = new ArrayList<>();
    }
    
    public void getList() {
        if (userList.isEmpty()) {
            System.out.println("You don't have any tasks in the list!");
        } else {
            int count = 1;
            for (int i = 0; i < userList.size(); i++) {
                Task t = userList.get(i);
                System.out.println(count + ". " + t.toString());
                count++;
            }
        }
    }
    
    public int size() {
        return userList.size();
    }
    
    public Task getTask(int val) {
        return userList.get(val);
    }
    
    public void addTask(Task task) {
        userList.add(task);
    }
    
    public void removeTask(int val) {
        userList.remove(val);
    }
}












