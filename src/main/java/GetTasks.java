import java.util.ArrayList;
import java.util.List;

public class GetTasks extends DukeMessage{
    public void display() {
        ArrayList<String> taskList = (ArrayList<String>) TaskList.getTaskList().getTasks();
        int count = 1;
        for(String task : taskList) {
            System.out.println(count + ". " + task);
            count = count + 1;
        }
    }
}
