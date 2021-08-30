package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;
    private Ui ui;
    private int currIndex = 1;

    /**
     * Creates a task list object.
     *
     * * @param list Lists of tasks as an Array List.
     * @param ui UI to handle user interactions.
     */
    public TaskList(ArrayList<Task> list, Ui ui) {
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
        taskList.add(new DeadLine(str.substring(9, str.indexOf("/") - 1), str.substring(str.indexOf("/") + 4)));
        System.out.println("   " + taskList.get(taskList.size() - 1).printTask());
        System.out.println("Now you have " + taskList.size() + " task(s) in total!");
    }

    void addEvent(String str) {
        System.out.println("Alrighty! I have added this task:");
        taskList.add(new Event(str.substring(6, str.indexOf("/") - 1), str.substring(str.indexOf("/") + 4)));
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

    private void addTasks(Task task, ArrayList<Task> list, String word) {
        if (task.getTask().equals(word)) {
            list.add(task);
        }
    }

    /**
     * Returns tasks that have the keyword in the task list.
     *
     * @param str Keyword to search.
     * @return A new TaskList with the tasks containing the keyword.
     *
     */
    public TaskList findTasks(String str) {
        ArrayList<Task> resultList = new ArrayList<Task>();
        List<Task> foundArray = taskList.stream().filter(task -> task.printTask().contains(str))
                .collect(Collectors.toList());
        resultList = new ArrayList<Task>(foundArray);

        return new TaskList(resultList, ui);
    }

}
