import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private int counter = 0;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.list.add(task);
        counter++;
    }

    public void markDone(int index) {
        int trueIndex = index - 1;
        list.get(trueIndex).isDone();
        String taskDoneMessage = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       " + "[" + list.get(trueIndex).showType() + "]" + list.get(trueIndex).checkDone() + " " + list.get(trueIndex).showTask() + "\n"
                + "    ____________________________________________________________";
        System.out.println(taskDoneMessage);
    }

    public void deleteTask(int index) {
        int trueIndex = index - 1;
        String listData = "[" + list.get(trueIndex).showType() + "]"
                + list.get(trueIndex).checkDone() + " "
                + list.get(trueIndex).showTask() + "\n";
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "       " + listData
                + "     Now you have " + (list.size() - 1) + " tasks in the list\n"
                + "    ____________________________________________________________");
        list.remove(trueIndex);
    }

    public int length() {
        return list.size();
    }

    public void showList() {
        System.out.println("    ____________________________________________________________");
        String fullList = "     " + "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
                String taskItem = "\n" + "     " + (i + 1) + "." + "[" + list.get(i).showType() + "]" + list.get(i).checkDone() + " " + list.get(i).showTask();
                fullList += taskItem;
        }
        System.out.println(fullList);
        System.out.println("    ____________________________________________________________");
    }

    public String refreshList() {
        String refreshList = "";
        for (int j = 0; j < list.size(); j++) {
            if (list.get(j).showType().equals("D") || list.get(j).showType().equals("E")) {
                refreshList += list.get(j).showType() + " | "
                        + (list.get(j).checkDone().equals("[X]") ? "1" : "0") + " | "
                        + list.get(j).showTaskOnly() + " | "
                        + list.get(j).showWhen()+ "\n";
            } else {
                refreshList += list.get(j).showType() + " | "
                        + (list.get(j).checkDone().equals("[X]") ? "1" : "0") + " | "
                        + list.get(j).showTask() + "\n";
            }
        }
        return refreshList;
    }

}
