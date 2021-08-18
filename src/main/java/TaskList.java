import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int counter = 0;

    public TaskList(int length) {
        list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        list.add(task);
        counter++;
    }

    public Task extractTask(int index) {
        return list.get(index);
    }

    public void deleteTask(int index) {
        int trueIndex = index - 1;
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + list.get(trueIndex).showType()
                            + list.get(trueIndex).checkDone() + " "
                            + list.get(trueIndex).showTask() + "\n"
                + "     Now you have " + (list.size() - 1) + " tasks in the list\n"
                + "    ____________________________________________________________");
        list.remove(trueIndex);
    }

    public int length() {
        return list.size();
    }

    public void showList() {
        System.out.println("    ____________________________________________________________\n"
                        + "     " + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
                String taskItem = "     " + (i + 1) + "." + list.get(i).showType() + list.get(i).checkDone() + " " + list.get(i).showTask();
                System.out.println(taskItem);
        }
        System.out.println("    ____________________________________________________________");
    }


}
