package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;
    private Ui ui;
    private int currIndex = 1;
    private String stringList = "";

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
        return taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Size of task list ArrayList.
     */
    public int size() {
        assert(taskList.size() >= 0);
        return taskList.size();
    }

    private void printItem(Task task) {
        if (stringList.equals("")) {
            stringList = currIndex + "." + task.printTask();
        } else {
            stringList = stringList + "\n" + currIndex + "." + task.printTask();
        }
        currIndex++;
    }

    String printList() {
        assert(taskList.size() >= 0);
        taskList.forEach((task) -> printItem(task));
        String response = stringList;
        currIndex = 1;
        stringList = "";
        return response;
    }

    String doneItem(int index) throws InputError {
        String response = "";
        try {
            if (index <= 0) {
                throw new InputError("Hey you can't put less than 0!");
            }
            if (index > taskList.size()) {
                throw new InputError("Invalid Number");
            }
            Task currTask = taskList.get(index - 1);
            currTask.setComplete();
            response = "Good job for this thing done man: \n" + "   " + currTask.printTask();
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    String addTodo(String str) {
        String response = "";
        System.out.println("Alrighty! I have added this task:");
        taskList.add(new ToDo(str.substring(5)));
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask() + "\n"
                + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    String addDeadline(String str) {
        String response = "";
        taskList.add(new DeadLine(str.substring(9, str.indexOf("/") - 1), str.substring(str.indexOf("/") + 4)));
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask()
                + "\n" + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    String addEvent(String str) {
        String response = "";
        taskList.add(new Event(str.substring(6, str.indexOf("/") - 1), str.substring(str.indexOf("/") + 4)));
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask()
                + "\n" + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    String deleteItem(int index) throws InputError {
        String response = "";
        try {
            if (index <= 0) {
                throw new InputError("Hey you can't put less than 0!");
            }
            if (index > taskList.size()) {
                throw new InputError("Invalid Number");
            }
            Task removed = taskList.remove(index - 1);
            response = "Alrighty! I have deleted this task:\n" + "   " + removed.printTask() + "\n"
                    + "Now you have " + taskList.size() + " task(s) in total!";
        } catch (InputError e) {
            response = ui.errorMessage(e);
        }
        return response;
    }

    /**
     * Returns tasks that have the keyword in the task list.
     *
     * @param str Keyword to search.
     * @return A new TaskList with the tasks containing the keyword.
     *
     */
    public TaskList findTasks(String str) {
        List<Task> foundArray = taskList.stream().filter(task -> task.printTask().contains(str))
                .collect(Collectors.toList());
        ArrayList<Task> searchList = new ArrayList<Task>(foundArray);

        TaskList foundList = new TaskList(searchList, ui);
        return foundList;
    }
}
