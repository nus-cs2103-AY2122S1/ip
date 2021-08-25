package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * The class that contains all the operations that change the list of tasks
 * such as adding, completing, deleting.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Public constructor.
     *
     * @return An instance of TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks task as complete and shows message to user.
     *
     * @param taskID
     */
    public void completeTask(int taskID) {
        Ui.showCompletedMessage();
        this.tasks.get(taskID - 1).markAsDone();
        Ui.showLine();
    }

    /**
     * Add task to list and shows message to user
     *
     * @param newTask
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
        Ui.showAddedTask(newTask.toString(), this.tasks.size());
    }

    /**
     * Lists all the items in the list if they have not been deleted.
     */
    public void listItems() {
        Ui.showList();
        for (int i = 0; i < this.tasks.size(); i++) {
            int j = i + 1;
            System.out.println("     " + j + ". "
                    + this.tasks.get(i).toString());
        }
        Ui.showLine();
    }

    /**
     * Deletes a specific task from the list.
     *
     * @param taskID
     * @throws IndexOutOfBoundsException
     */
    public void deleteTask(int taskID) throws IndexOutOfBoundsException{
        if (taskID <=0 || taskID > this.tasks.size()) {
            Ui.noTask();
            throw new IndexOutOfBoundsException();
        } else {
            Ui.showSuccessfulDelete();
            System.out.println(this.tasks.get(taskID - 1).toString());
            this.tasks.remove(taskID - 1);
            Ui.showUpdatedNumber(this.tasks.size());
        }
    }

    /**
     * Getter to get the arraylist of tasks
     *
     * @return Arraylist of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Getter to get individual tasks from arraylist
     * @param id
     * @return Individual tasks
     */
    public Task getIndividualTask(int id) {
        return this.tasks.get(id);
    }

    /**
     * Allows users to find a task by searching for a keyword
     * and using command 'find'
     *
     * @param keyword
     */
    public void findTasks(String keyword) {
        int count = 0;
        Ui.showSuccessfulFind();
        for (int i = 0; i < this.tasks.size(); i++) {
            String taskDescription = this.getIndividualTask(i).toString();

            int index = taskDescription.indexOf(keyword);

            if (index != -1) {
                count += 1;
                Ui.showResults(count, taskDescription);
            }
        }

        if (count == 0) {
            Ui.showUnsuccessfulFind();
        }

        Ui.showLine();
    }
}
