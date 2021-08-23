import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;
    private Ui ui;
    private int currIndex = 1;

    public TaskList(ArrayList list, Ui ui) {
        this.taskList = list;
        this.ui = ui;
    }

    public ArrayList<Task> currList() {
        return this.taskList;
    }
    public int size() {
        return this.taskList.size();
    }

    private void printItem(Task task) {
        System.out.println(currIndex + "." + task.printTask());
        currIndex++;
    }

    void printList() {
        taskList.forEach((task) -> printItem(task));
        currIndex = 1;
    }

    void doneItem(int index) throws InputError {
        try {
            if (index > taskList.size()) {
                throw new InputError("Invalid Number");
            }
            System.out.println("Good job for this thing done man:");
            Task currTask = taskList.get(index - 1);
            currTask.setComplete();
            System.out.println("   " + currTask.printTask());
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

    void addTodo(String str) {
        System.out.println("Alrighty! I have added this task:");
        taskList.add(new ToDo(str.substring(5)));
        System.out.println("   " + taskList.get(taskList.size() - 1).printTask());
        System.out.println("Now you have " + taskList.size() + " task(s) in total!");
    }

    void addDeadline(String str) {
        System.out.println("Alrighty! I have added this task:");
        taskList.add(new Deadline(str.substring(9, str.indexOf("/")), str.substring(str.indexOf("/") + 4)));
        System.out.println("   " + taskList.get(taskList.size() - 1).printTask());
        System.out.println("Now you have " + taskList.size() + " task(s) in total!");
    }

    void addEvent(String str) {
        System.out.println("Alrighty! I have added this task:");
        taskList.add(new Event(str.substring(6, str.indexOf("/")), str.substring(str.indexOf("/") + 4)));
        System.out.println("   " + taskList.get(taskList.size() - 1).printTask());
        System.out.println("Now you have " + taskList.size() + " task(s) in total!");
    }

    void deleteItem(int index) throws InputError {
        try {
            if (index > taskList.size()) {
                throw new InputError("Invalid Number");
            }
            System.out.println("Alrighty! I have deleted this task:");
            Task removed = taskList.remove(index - 1);
            System.out.println("   " + removed.printTask());
            System.out.println("Now you have " + taskList.size() + " task(s) in total!");
        } catch (InputError e) {
            ui.errorMessage(e);
        }
    }

}
