package duke;

import java.util.ArrayList;

/**
 * TaskList class that contains a list of Task and
 * methods to modify them
 */
public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Creates a TaskList object
     */
    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Creates a TaskList object
     *
     * @param list takes in a list of Task
     */
    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list
     *
     * @param t takes in a Task to be added
     */
    public void add(Task t) {
        list.add(t);
    }

    /**
     * Gets the task in the specific index
     *
     * @param getNum takes in a int representing the index
     * @return a Task at the specific index
     */
    public Task get(int getNum) {
        return list.get(getNum);
    }

    /**
     * Gets the size of the list
     *
     * @return a int representing the list size
     */
    public int size() {
        return list.size();
    }

    /**
     * Finds a list of tasks with a given keyword
     * @param keyword takes in a String representing the keyword
     * @param ui takes in a Ui for error message
     * @return a String representing the filtered list from the keyword
     * @throws DukeException throws an emptyDescriptionError
     */
    public String find(String keyword, Ui ui) throws DukeException {
        if (keyword.strip().isEmpty()) {
            throw new DukeException(ui.emptyDescriptionError());
        }
        String findList = "\n";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDescription().contains(keyword)) {
                findList += list.get(i) + "\n";
            }
        }
        return findList;
    }

    /**
     * Shows a list of tasks in the taskList
     *
     * @return a String representing the list of tasks
     */
    public String list() {
        String tempList = "";
        int listNum = 1;
        for (int i = 0; i < list.size(); i++) {
            tempList += listNum + "." + list.get(i) + "\n";
            listNum++;
        }
        assert listNum == list.size()
                : "listNum and tasks size should be the same";
        return tempList;
    }

    /**
     * Marks a task in the taskList as done with the given index
     * @param doneNum takes in an int representing the index
     * @return a Task that was marked as done
     */
    public Task doneTask(int doneNum) {
        assert doneNum < list.size() && doneNum > -1
                : "Task number should be in range";
        list.get(doneNum).markAsDone();
        return list.get(doneNum);
    }

    /**
     * Removes a task at the specific index from the list
     *
     * @param delNum takes in a int representing the index
     */
    public void remove(int delNum) {
        list.remove(delNum);
    }

}
