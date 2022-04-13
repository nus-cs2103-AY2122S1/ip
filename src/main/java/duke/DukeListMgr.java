package duke;

import java.util.ArrayList;

/**
 * This class encapsulates the list element that Duke uses to store tasks.
 */
public class DukeListMgr {

    private ArrayList<BaseTask> toDoList = new ArrayList<>();

    /**
     * Creates a new list manager.
     */
    public DukeListMgr() {

    }

    /**
     * Adds things to the to do list.
     *
     * @param str   Thing to add to the list.
     * @param currTaskType The type of the task to add.
     * @throws DukeExceptionBase when an invalid input is entered.
     */
    public void tdlAdd(String str, BaseTask.TaskType currTaskType) throws DukeExceptionBase {
        assert (this.toDoList != null) : "Duke internal List is missing!";

        BaseTask createdTask;

        switch (currTaskType) {
        case TODO:
            createdTask = processTodoTask(str);
            break;
        case EVENT:
            createdTask = processEventTask(str);
            break;
        case DEADLINE:
            createdTask = processDeadlineTask(str);
            break;
        default:
            throw new DukeExceptionBase("Wrong type of task input to tdlAdd.");
        }

        toDoList.add(createdTask);
        this.triggerSaveTasks();

        Duke.dukeSays("Ok, I have added this task: \n" + createdTask.getLineOfTaskInfo()
            + "\nCurrent total amount of tasks in list: " + toDoList.size());

    }

    private static BaseTask processDeadlineTask(String str) throws DukeExceptionBase {
        int indexOfSlash;
        BaseTask createdTask;
        indexOfSlash = str.indexOf("/by");
        if (indexOfSlash == -1) {
            throw new DukeExceptionBase("Your Deadline task needs a '/by' description.");
        }

        String deadlineTaskName = str.substring(9, indexOfSlash);
        String deadlineByWhen;

        try {
            deadlineByWhen = str.substring(indexOfSlash + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeExceptionBase("Adding a deadline description is required.");
        }

        createdTask = new DeadlineTask(deadlineTaskName, deadlineByWhen);
        return createdTask;
    }

    private static BaseTask processEventTask(String str) throws DukeExceptionBase {
        int indexOfSlash;
        BaseTask createdTask;
        indexOfSlash = str.indexOf("/at");
        if (indexOfSlash == -1) {
            throw new DukeExceptionBase("Your Event task needs a '/at' description.");
        }

        String eventTaskName = str.substring(6, indexOfSlash);
        String eventAtWhere = "";
        try {
            eventAtWhere = str.substring(indexOfSlash + 4);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeExceptionBase("Adding a event description is required.");
        }

        createdTask = new EventTask(eventTaskName, eventAtWhere);
        return createdTask;
    }

    private static BaseTask processTodoTask(String str) throws DukeExceptionBase {
        BaseTask createdTask;
        String todoContents = "";
        try {
            todoContents = str.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeExceptionBase("The description of your todo cannot be empty.");
        }

        createdTask = new ToDosTask(todoContents);
        return createdTask;
    }


    /**
     * Marks the corresponding task as done.
     *
     * @param taskNo The task number in the list.
     * @return Duke's output from this command.
     * @throws DukeExceptionBase when an invalid task is specified or if task is already done.
     */
    public String markTaskAsDone(int taskNo) throws DukeExceptionBase {
        assert (this.toDoList != null) : "Duke internal List is missing!";
        // Task list starts from 1 instead of 0 so input in command is 1 more than
        // the task's index in the list
        int taskIndex = taskNo - 1;

        if (!(taskIndex >= 0 && taskIndex < toDoList.size())) {
            throw new DukeExceptionBase("Invalid Task Specified");
        }

        BaseTask currTask = toDoList.get(taskIndex);
        currTask.setAsDone();
        this.triggerSaveTasks();

        return "Nice! I've marked this task as done:\n" + currTask.getLineOfTaskInfo();
    }

    /**
     * Deletes tasks from the list.
     *
     * @param taskNo The task number to delete.
     * @return Duke's output from this command.
     * @throws DukeExceptionBase when an invalid task is specified.
     */
    public String deleteTask(int taskNo) throws DukeExceptionBase {
        assert (this.toDoList != null) : "Duke internal List is missing!";
        int taskIndex = taskNo - 1;

        if (!(taskIndex >= 0 && taskIndex < toDoList.size())) {
            throw new DukeExceptionBase("Invalid Task Specified");
        }

        BaseTask currTask = toDoList.get(taskIndex);

        toDoList.remove(taskIndex);
        this.triggerSaveTasks();
        return "Ok, this task has been removed:\n" + currTask.getLineOfTaskInfo()
                + "\nCurrent total amount of tasks in list: " + toDoList.size();
    }

    /**
     * Finds matching tasks in the list using a keyword.
     *
     * @param keyword the word to search for.
     * @return the output of the search.
     * @throws DukeExceptionBase when there is invalid input.
     */
    public String findMatchingTaskInList(String keyword) throws DukeExceptionBase {
        assert (this.toDoList != null) : "Duke internal List is missing!";
        if (keyword.contains(" ")) {
            throw new DukeExceptionBase("find only works with keywords. (A keyword cannot contain spaces)");
        }

        ArrayList<BaseTask> foundTaskList = new ArrayList<>();

        for (BaseTask currTask : this.toDoList) {
            boolean isMatching = currTask.searchForKeywords(keyword);

            if (isMatching) {
                foundTaskList.add(currTask);
            }
        }

        if (foundTaskList.size() == 0) {
            return "There were no matching tasks.";
        }

        // List out the matching tasks.
        String printThis = this.getListFromTaskList(foundTaskList);

        return "Here are the matching tasks found: \n" + printThis;
    }

    /**
     * Prints out contents of the list nicely.
     */
    public void printOutWholeList() {
        String printThis = this.getListFromTaskList(this.toDoList);

        Duke.dukeSays("Here are your current tasks: \n" + printThis);
    }

    /**
     * Converts the input list of tasks into a String listing out all the tasks.
     *
     * @param taskList the task list to get the list from.
     * @return the String containing the converted task list.
     */
    private String getListFromTaskList(ArrayList<BaseTask> taskList) {
        String printThis = "";
        String nextLine = "\n";

        int serialNo = 1;
        for (BaseTask ele : taskList) {
            String endOfCurrLine = nextLine;
            if (serialNo == taskList.size()) {
                endOfCurrLine = "";
            }

            String currLine = serialNo + ". " + ele.getLineOfTaskInfo() + endOfCurrLine;
            printThis = printThis + currLine;

            serialNo++;
        }

        return printThis;
    }

    /**
     * Triggers the saving of the current state of the task list.
     */
    public void triggerSaveTasks() {
        Duke currDuke = Duke.getCurrDuke();
        currDuke.getCurrStorageMgr().saveCurrentTasks(this.toDoList);
    }

    /**
     * Loads a new ArrayList of tasks for use with Duke.
     *
     * @param loadedTasks the ArrayList of BaseTasks to load.
     */
    public void loadTaskList(ArrayList<BaseTask> loadedTasks) {
        this.toDoList = loadedTasks;
    }

}
