public class TaskList {
    private Task[] list;
    private int counter = 0;

    public TaskList(int length) {
        list = new Task[length];
    }

    public void addTask(Task task) {
        list[counter] = task;
        counter++;
    }

    public void showList() {
        System.out.println("    ____________________________________________________________\n"
                        + "     " + "Here are the tasks in your list:");
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                String taskItem = "     " + (i + 1) + "." + list[i].showType() + list[i].checkDone() + " " + list[i].showTask();
                System.out.println(taskItem);
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public Task extractTask(int index) {
        return list[index];
    }

    public int length() {
        int count = 0;
        for (int i = 0; i < list.length; i ++) {
            if (list[i] != null) {
                count ++;
            }
        }
        return count;
    }
}
