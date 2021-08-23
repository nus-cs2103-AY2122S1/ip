import java.util.ArrayList;

public class ListCommand extends Command {

    private ArrayList<Task> taskList;

    public ListCommand(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        if (this.taskList.isEmpty()) {
            //if list is empty
            System.out.println("    There are no tasks in your list");
        } else { //if list !empty
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < this.taskList.size(); i++) {
                int currNum = i + 1;
                Task currTask = this.taskList.get(i);
                System.out.println("     " + currNum + ". " + currTask.toString());
            }
        }
    }
}
