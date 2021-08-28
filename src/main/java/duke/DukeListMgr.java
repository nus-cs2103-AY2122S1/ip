package duke;

import duke.Tasks.DeadlineTask;
import duke.Tasks.EventTask;
import duke.Tasks.BaseTask;
import duke.Tasks.ToDosTask;

import java.util.ArrayList;

/**
 * This class encapsulates the list element that Duke uses to store tasks.
 */
public class DukeListMgr {

    private ArrayList<BaseTask> toDoList = new ArrayList<>();

    public DukeListMgr() {

    }

    /**
     * Used for adding things to the to do list.
     *
     * @param str   Thing to add to the list.
     *
     * @param currTaskType The type of the task to add.
     *
     * @throws DukeExceptionBase when an invalid input is entered.
     */
    public void tdlAdd(String str, BaseTask.TaskType currTaskType) throws DukeExceptionBase {
        BaseTask createdTask;

        int indexOfSlash = -1;

        switch (currTaskType) {
        case TODO:
            String todoContents = "";
            try {
                todoContents = str.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeExceptionBase("The description of your todo cannot be empty.");
            }

            createdTask = new ToDosTask(todoContents);

            break;
        case EVENT:
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
            break;
        case DEADLINE:
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
            break;
        default:
            throw new DukeExceptionBase("Wrong type of task input to tdlAdd.");
        }

        toDoList.add(createdTask);
        this.triggerSaveTasks();


        Duke.dukeSays("Ok, I have added this task: \n" + createdTask.getLineOfTaskInfo()
            + "\nCurrent total amount of tasks in list: " + toDoList.size());


    }



    /**
     * Used to mark the corresponding task as done.
     *
     * @param taskNo The task number in the list.
     *
     * @return Duke's output from this command
     *
     * @throws DukeExceptionBase when an invalid task is specified or if task is already done.
     */
    public String markTaskAsDone(int taskNo) throws DukeExceptionBase {
        //Task list starts from 1 instead of 0 so input in command is 1 more than
        //the task's index in the list
        int taskIndex = taskNo - 1;

        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
            BaseTask currTask = toDoList.get(taskIndex);
            currTask.setAsDone();
            this.triggerSaveTasks();

            return "Nice! I've marked this task as done:\n" + currTask.getLineOfTaskInfo();
        } else {
            throw new DukeExceptionBase("Invalid Task Specified");
        }
    }

    /**
     * Used to delete tasks from the list.
     *
     * @param taskNo The task number to delete.
     *
     * @return Duke's output from this command
     *
     * @throws DukeExceptionBase when an invalid task is specified.
     */
    public String deleteTask(int taskNo) throws DukeExceptionBase {
        int taskIndex = taskNo - 1;

        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
            BaseTask currTask = toDoList.get(taskIndex);

            toDoList.remove(taskIndex);
            this.triggerSaveTasks();
            return "Ok, this task has been removed:\n" + currTask.getLineOfTaskInfo()
                    + "\nCurrent total amount of tasks in list: " + toDoList.size();
        } else {
            throw new DukeExceptionBase("Invalid Task Specified");
        }
    }

    /**
     * Finds matching tasks in the list using a keyword.
     *
     * @param keyword the word to search for.
     * @return the output of the search.
     * @throws DukeExceptionBase when there is invalid input.
     */
    public String findMatchingTaskInList(String keyword) throws DukeExceptionBase {
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
        } else {
            // List out the matching tasks.
            String printThis = this.getListFromTaskList(foundTaskList);

            return "Here are the matching tasks found: \n" + printThis;
        }

    }


    /**
     * Used to print out contents of the list nicely.
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
