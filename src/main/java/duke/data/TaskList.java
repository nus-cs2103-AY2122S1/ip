package duke.data;

import java.util.ArrayList;

import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;


/**
 * Class that contains the tasklist and handles all tasklist operations.
 *
 * @author Wang Hong Yong
 */
public class TaskList {
    private ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructor for the TaskList class.
     *
     * @param list Tasklist.
     * @param storage storage for duke.
     */
    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task Task to be added.
     * @return string representation of the task added.
     */
    public String addTask(Task task) {
        list.add(task);
        this.writeFile(task);
        return Ui.getAddMsg(task.toString(), getTotalTasks());
    }

    /**
     * Deletes the task at the given task index.
     *
     * @param input the input of user for this command.
     * @param minCommandLength minimum length of command.
     * @return string representation of the task removed.
     */
    public String removeTask(String input, int minCommandLength) throws DukeException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(input.substring(minCommandLength - 1));
        } catch (Exception e) {
            throw new DukeException(Ui.getDeleteFormatMsg());
        }
        if (taskNum < 1) {
            throw new DukeException(Ui.getNegativeTaskMsg());
        } else if (list.size() == 0) {
            throw new DukeException(Ui.getListEmptyMsg());
        } else if (taskNum > list.size()) {
            throw new DukeException(Ui.getTaskNotFoundMsg());
        }
        assert taskNum > 0 : "task number is empty";
        Task item = list.remove(taskNum - 1);
        this.updateFile();
        return Ui.getRemoveMsg(item.toString(), getTotalTasks());
    }

    /**
     * Adds tag to the chosen task.
     *
     * @param infoArray array representing the information.
     * @return string representation of the task after adding tags.
     */
    public String addTag(String[] infoArray) throws DukeException {
        int taskNum;
        String tag;
        try {
            taskNum = Integer.parseInt(infoArray[1]);
            tag = infoArray[2];
        } catch (Exception e) {
            throw new DukeException(Ui.getTagFormatMsg());
        }
        if (taskNum < 1) {
            throw new DukeException(Ui.getNegativeTaskMsg());
        } else if (list.size() == 0) {
            throw new DukeException(Ui.getListEmptyMsg());
        } else if (taskNum > list.size()) {
            throw new DukeException(Ui.getTaskNotFoundMsg());
        }
        Task item = list.get(taskNum - 1);
        item.addTag(tag);
        this.updateFile();
        return Ui.getAddTagMsg(item.toString());

    }

    /**
     * Marks the task at the given task index as done.
     *
     * @param input the input of user for this command.
     * @param minCommandLength minimum length of command.
     * @return string representation of task done.
     */
    public String markTaskAsDone(String input, int minCommandLength) throws DukeException {
        int taskNum;
        try {
            taskNum = Integer.parseInt(input.substring(minCommandLength - 1));
        } catch (Exception e) {
            throw new DukeException(Ui.getDoneFormatMsg());
        }
        if (taskNum < 1) {
            throw new DukeException(Ui.getNegativeTaskMsg());
        } else if (list.size() == 0) {
            throw new DukeException(Ui.getListEmptyMsg());
        } else if (taskNum > list.size()) {
            throw new DukeException(Ui.getTaskNotFoundMsg());
        }
        assert taskNum > 0 : "task number is empty";
        Task item = list.get(taskNum - 1);
        if (item.isDone()) {
            throw new DukeException(Ui.getTaskAlreadyDoneMsg());
        }
        item.markAsDone();
        this.updateFile();
        return Ui.getTaskDone(item.toString());
    }

    /**
     * Returns number of undone task.
     *
     * @return A int representing total undone task.
     */
    private int getUndoneTasks() {
        int count = 0;
        for (Task task : list) {
            if (!task.isDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns number of total task.
     *
     * @return A int representing total task.
     */
    private int getTotalTasks() {
        return list.size();
    }

    /**
     * Prints the task searched with keyword
     *
     * @param wordToFind is a String that represents the keyword to be searched.
     * @return string representation of the task found.
     */
    public String findTask(String wordToFind) {
        ArrayList<String> tasks = new ArrayList<>();
        int index = 1;
        for (Task t : list) {
            if (t.toString().contains(wordToFind)) {
                tasks.add(String.format("    %d.%s", (index), t.toString()) + System.lineSeparator());
                index++;
            }
        }
        if (tasks.size() == 0) {
            return Ui.getNoSuchTaskMsg();
        } else {
            String msg = "     Here are the matching tasks in your list:" + System.lineSeparator();
            for (int i = 0; i < tasks.size(); i++) {
                msg += String.format("    %d.%s", (i + 1), tasks.get(i).toString()) + System.lineSeparator();
            }
            return msg;
        }
    }




    /**
     * list the total of all the tasks.
     * @return string representation of all the tasks.
     */
    public String listTasks() throws DukeException {
        if (list.size() == 0) {
            throw new DukeException(Ui.getListEmptyMsg());
        }
        String msg = "    Here are the tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < list.size(); i++) {
            msg += String.format("    %d.%s", (i + 1), list.get(i).toString()) + System.lineSeparator();
        }
        return msg;
    }

    /**
     * Updates the file into storage.
     */
    public void updateFile() throws DukeException {
        storage.updateFile(list);
    }
    /**
     * Writes the file into storage.
     */
    public void writeFile(Task t) throws DukeException {
        storage.writeFile(t);
    }
}
