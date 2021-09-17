package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements Serializable {
    static final int TODO_TASK = 5;
    static final int DEADLINE_TASK = 9;
    static final int EVENT_TASK = 6;
    static final int EVENT_TIMING = 4;
    private final ArrayList<Task> taskList;
    private final Ui ui;

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

    /**
     * Prints all the tasks in the task list in the order they were added.
     *
     * @return String representation of the task list.
     */
    public String printList() {
        assert(taskList.size() >= 0);
        String response = "Here are the items in your list";
        for (int i = 0; i < taskList.size(); i++) {
            int currIndex = i + 1;
            Task currTask = taskList.get(i);
            response = response + "\n" + currIndex + "." + currTask.printTask();
        }
        return response;
    }

    /**
     * Marks a Task as done in the TaskList.
     *
     * @param index Numbered Task in the TaskList to be marked as done.
     * @return String message indicating that Task has been marked as done.
     */
    public String doneTask(int index) {
        String response;
        Task currTask = taskList.get(index - 1);
        currTask.setComplete();
        response = "Good job for this thing done man: \n" + "   " + currTask.printTask();
        return response;
    }

    /**
     * Adds a To Do task to the TaskList.
     *
     * @param str to do instruction containing the to do task.
     * @return String message indicating that to do task has been added to the TaskList.
     */
    public String addTodo(String str) {
        String response;
        ToDo todoTask = new ToDo(str.substring(TODO_TASK));
        taskList.add(todoTask);
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask() + "\n"
                + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    /**
     * Adds a DeadLine task to the TaskList.
     *
     * @param str DeadLine instruction containing the DeadLine Task.
     * @return String message indicating that the DeadLine task has been added to the TaskList.
     */
    public String addDeadline(String str) {
        String response;
        int slashIndex = str.indexOf("/");
        DeadLine deadlineTask = new DeadLine(str.substring(DEADLINE_TASK, slashIndex - 1),
                str.substring(slashIndex + 4));
        taskList.add(deadlineTask);
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask()
                + "\n" + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    /**
     * Adds an Event task to the TaskList.
     *
     * @param str Event instruction containing the Event Task.
     * @return String message indicating that Event task has been added to the TaskList.
     */
    public String addEvent(String str) {
        String response;
        int slashIndex = str.indexOf("/");
        String event = str.substring(EVENT_TASK, slashIndex - 1);
        String timing = str.substring(slashIndex + EVENT_TIMING);
        Event eventTask = new Event(event, timing);
        taskList.add(eventTask);
        response = "Alrighty! I have added this task:\n" + "   " + taskList.get(taskList.size() - 1).printTask()
                + "\n" + "Now you have " + taskList.size() + " task(s) in total!";
        return response;
    }

    /**
     * Deletes a Task from the TaskList.
     *
     * @param index Numbered Task in the TaskList to be deleted.
     * @return String message indicating that the desired Task has been deleted from the TaskList.
     */
    public String deleteTask(int index) {
        String response = "";
        Task removed = taskList.remove(index - 1);
        response = "Alrighty! I have deleted this task:\n" + "   " + removed.printTask() + "\n"
                + "Now you have " + taskList.size() + " task(s) in total!";
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
        ArrayList<Task> foundArrayList;
        TaskList foundTaskList;
        List<Task> foundArray = taskList.stream().filter(task -> task.printTask().contains(str))
                .collect(Collectors.toList());
        foundArrayList = new ArrayList<Task>(foundArray);
        foundTaskList = new TaskList(foundArrayList, ui);

        return foundTaskList;
    }
}
