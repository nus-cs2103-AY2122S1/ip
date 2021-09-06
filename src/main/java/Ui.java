public class Ui {
    public void display(String msg) {
        System.out.println(msg);
    }

    public void display(Exception e) {
        this.display(e.getMessage());
    }

    public void addTask(Task task, TaskList taskList) {
        display("is added.");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    public void completeTask(Task task) {
        display("is done!");
        display(task.toString());
    }

    public void deleteTask(Task task, TaskList taskList) {
        display("is deleted!");
        display(task.toString());
        display("now is have " + taskList.size() + " task" +
                (taskList.size() == 1 ? "" : "s") + ".");
    }

    public void listTasks(TaskList taskList) {
        if (taskList.size() == 0) {
            display("is no tasks today.");
        } else {
            for (int i = 1; i <= taskList.size(); i++) {
                String taskDescription = taskList.getTask(i - 1).toString();
                display(i + "." + taskDescription + ".");
            }
        }
    }
}
