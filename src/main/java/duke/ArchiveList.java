package duke;

import duke.task.Task;

import java.util.ArrayList;

public class ArchiveList extends DukeList {
    /**
     * Constructor for TaskList.
     */
    public ArchiveList() {
        super();
    }

    /**
     * Constructor for TaskList, setting tasks to a given list.
     *
     * @param list List to be assigned to tasks.
     */
    public ArchiveList(ArrayList<Task> list) {
        super(list);
    }

//    /**
//     * Adds a new Task to tasks.
//     *
//     * @param toAdd Task to add to tasks.
//     */
//    public void add(Task toAdd) {
//        super.add(toAdd);
//    }

    /**
     * Getter for archived tasks.
     *
     * @return tasks.
     */
    public ArrayList<Task> getArchivedTasks() {
        return super.getTaskArrayList();
    }

    @Override
    public String type() {
        return "archived list";
    }

    //    /**
//     * Removes Task from given index from tasks.
//     *
//     * @param index Index of Task to remove from tasks.
//     * @return The removed Task.
//     */
//    public Task remove(int index) {
//        return super.remove(index);
//    }

//    /**
//     * Gets the size of tasks.
//     *
//     * @return Size of tasks.
//     */
//    public int getSize() {
//        return super.size();
//    }

//    /**
//     * Converts Task of a given index in tasks into its String representation.
//     *
//     * @param index Index of Task to get the String representation.
//     * @return String representation of Task in tasks with given index.
//     */
//    public String taskToString(int index) {
//        return super.get(index).toString();
//    }

//    /**
//     * Converts Task of a given index in tasks to its String representation to save.
//     *
//     * @param index Index of Task to get the String representation to save.
//     * @return String representation of Task in tasks with given index.
//     */
//    public String taskSaveToString(int index) {
//        return super.get(index).convertToString();
//    }

//    /**
//     * Converts TaskList object into its String representation.
//     *
//     * @return String representation of TaskList object.
//     */
//    @Override
//    public String toString() {
//        return super.toString();
//    }
}
