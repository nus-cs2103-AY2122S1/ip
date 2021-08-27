package tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import ui.Ui;

public final class TaskList{
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> inputTasks) {
        this.tasks = inputTasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the String representation of the date/deadline as input by user
     *
     * @param words the String array representation of the input by user
     * @return the concatenated String that is the date specified by user
     */
    public String filterInfo(ArrayList<String> words) {
        String temp = "";
        for (int i = 1; i < words.size(); i++) {
            if (!(words.get(i).equals("/at") || words.get(i).equals("/by"))
                    || words.get(0).equalsIgnoreCase("todo")) {
                temp += words.get(i) + " ";
            } else {
                break;
            }
        }
        return temp;
    }

    /**
     * Identifies the start of String representation of date of Event
     * by finding the "/at" expression
     *
     * @param args the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws IllegalArgumentException thrown when encountering a String Array without
     * the presence of "/at" expression, which is not a valid input for Event
     */
    public String searchForEventDay(ArrayList<String> args) throws IllegalArgumentException {
        for (int i = 2; i < args.size(); i++) {
            if (args.get(i).equals("/at")) {
                if (i + 1 >= args.size()) {
                    throw new IllegalArgumentException("Oh no, date is missing :(");
                } else {
                    return getDate(args, i + 1);
                }
            }
        }
        throw new IllegalArgumentException("event");
    }

    /**
     * Identifies the start of String representation of date for Deadline
     * by finding the "/by" expression
     *
     * @param arg the String array representation of the input by user
     * @return the String representation of the date by passing it to method
     * getDate which requires a starting index as an argument
     * @throws IllegalArgumentException thrown when encountering a String Array without
     * the presence of "/by" expression, which is not a valid input for Deadline
     */
    public String lookForDeadline(ArrayList<String> arg) throws IllegalArgumentException {
        for (int i = 2; i < arg.size(); i++) {
            if (arg.get(i).equals("/by")) {
                if (i + 1 >= arg.size()) {
                    throw new IllegalArgumentException("Uh oh, deadline is missing :(");
                } else {
                    return getDate(arg, i + 1);
                }
            }
        }
        throw new IllegalArgumentException("deadline");
    }

    /**
     * Creates a String representation of date by
     * concatenating a String from the starting index to the last index
     *
     * @param s the String array representation of the input by user
     * @param start the starting index
     * @return String representation of date as input by user
     */
    public String getDate(ArrayList<String> s,int start) {
        String temp = "";
        for (int i = start; i < s.size(); i++) {
            if (i + 1 < s.size()) {
                temp += s.get(i) + " ";
            } else {
                temp += s.get(i);
            }
        }
        return temp;
    }

    /**
     * Adds tasks to the stored list of tasks.
     *
     * @param t the task to be added/e
     */
    public void addTask(Task t){
        if (tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(t);
        Ui.showInput("Successfully added:", t.getType() + t.getStatus() + " " + t.getTask());
    }

    /**
     * Displays the entire list of tasks sequentially
     */
    public void getList() {
        if (tasks != null && !tasks.isEmpty()) {
            String temp = "The current list has these items:\n";
            for (int i = 0; i < tasks.size(); i++) {
                temp += "     " + (i + 1) + "." + tasks.get(i).getType() + tasks.get(i).getStatus() + " "
                        + tasks.get(i).getTask() + "\n";
            }
            Ui.showInput(temp, "There are " + tasks.size() + " task(s) now, keep up!");
        } else {
            Ui.showInput("There are no items in your list, keep adding them!");
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param ind the index of task to be deleted
     */
    public void deleteTask(int ind) {
        Task t = tasks.remove(ind);
        Ui.showInput("Noted, the following task has been deleted: ",
                t.getType() + t.getStatus() + " " + t.getTask(),
                "Nice! there are " + tasks.size() + " task(s) left.");
    }

    /**
     * Checks if any tasks are due on a particular day.
     *
     * @param s the date which user wants to check
     */
    public void anyTaskDue(String s) {
        ArrayList<Task> dueItems = new ArrayList<>();
        if (tasks.isEmpty()) {
            Ui.showInput("No tasks yet!");
        } else {
            String[] date = s.split("/");
            LocalDate ref = LocalDate.parse(date[0] + "-" + date[1] + "-" + date[2]);
            for (Task t : tasks) {
                if (!(t instanceof ToDoTask)) {
                    LocalDate temp = t.getLocalDate();
                    if (temp != null) {
                        if (temp.equals(ref)) {
                            dueItems.add(t);
                        }
                    }
                }
            }
        }
    }

    /**
     * Finds tasks according to keywords.
     *
     * @param target the keywords as per input by user
     * @return list containing all tasks that match input keywords
     */
    public ArrayList<Task> findTask(String target) {
        ArrayList<Task> matched = new ArrayList<>();
        if (!target.equals("")) {
            for (Task t : tasks) {
                String temp = t.getTask();
                if (temp.toLowerCase().contains(target.toLowerCase())) {
                    matched.add(t);
                }
            }
        }
        return matched;
    }
}