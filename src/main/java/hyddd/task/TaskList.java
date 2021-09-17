package hyddd.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import hyddd.exceptions.ExceptionType;
import hyddd.exceptions.HydddException;
import hyddd.executions.LastExecution;
import hyddd.logics.Parser;

/**
 * @@author Hang Zelin
 *
 * Stores all the tasks for the hyddd. hyddd can refer to this tasklist to see a specific task
 * or make use of the methods in it to execute an operation.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private LastExecution lastExecution;

    /**
     * Constructor to store all the tasks in a Generic ArrayList.
     *
     * @param tasks A list of Task type variables.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.lastExecution = new LastExecution("Null", null, 0, null);
    }

    /**
     * Another Constructor to initialize an empty TaskList if there is no save data.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns all the tasks in a given list.
     *
     * @return A list of tasks info.
     */
    public String printListUi() {
        StringBuilder text = new StringBuilder();
        text.append("Here are the tasks in your list:\n");
        //@@author Hang Zelin-reused
        //Reused from:
        //https://github.com/nus-cs2103-AY2122S1/ip/pull/60/commits/7f61b2b2976ae38932af343a7c8c3b71405cc6aa
        //Inspired by his stream method.
        int[] index = {0};
        tasks.forEach(task -> text.append(++index[0]).append(".").append(task.getTaskStatus()).append("\n"));
        return text.toString();
    }

    private boolean returnIsFound(String time, String unparsedInfo, String task) {
        Parser parser = new Parser("");

        String timeInFormat = (parser.parseTime(time) != null)
                ? parser.parseTime(time).format(DateTimeFormatter
                .ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH))
                : "Null time Info";
        boolean isMessageContains = task.contains(time)
                || task.contains(timeInFormat);
        boolean isUnparsedInfoContains = unparsedInfo != null && (unparsedInfo.contains(time)
                || unparsedInfo.contains(timeInFormat));

        return isMessageContains || isUnparsedInfoContains;
    }

    private void checkIsDuplicate(String task) throws HydddException {
        for (int index = 0; index < tasks.size(); index++) {
            if (tasks.get(index).returnIsSameTask(task)) {
                throw new HydddException(ExceptionType.DUPLICATE_TASK_ERROR);
            }
        }
    }

    /**
     * Returns all the tasks that match the time users take in.
     *
     * @param time String message that indicates time users take in to find specific event.
     * @return All the tasks that match the time users take in.
     */
    public String getSpecificDateEvent(String time) {
        StringBuilder text = new StringBuilder();

        //@@author Hang Zelin-reused
        //Reused from:
        //https://github.com/nus-cs2103-AY2122S1/ip/pull/60/commits/7f61b2b2976ae38932af343a7c8c3b71405cc6aa
        //Inspired by his stream method.
        final int[] count = {0}; //count the number of the events happen on the time.
        tasks.stream()
                .filter(task -> returnIsFound(time,
                        task.getTimeForSaveData(), task.getTaskStatus()))
                .forEach(task -> text.append(++count[0])
                        .append(".").append(task.getTaskStatus()).append("\n"));

        if (count[0] == 0) {
            return "Sorry. There is no tasks occurred on the time you give me!! :(\n";
        }
        return text.toString();
    }

    /**
     * Undoes last hyddd operation.
     *
     * @return hyddd's response for undo message.
     */
    public String undo() {
        String text = lastExecution.undo();
        this.lastExecution = new LastExecution("Null", null, 0, null);
        return text;
    }

    /**
     * Returns all the tasks that match the key word users take in.
     *
     * @param keyword String message that indicates the keyword users want to search.
     * @return All the tasks that match the key word users take in.
     */
    public String findTasks(String keyword) {
        StringBuilder text = new StringBuilder();
        final int[] count = {0};

        //@@author Hang Zelin-reused
        //Reused from:
        //https://github.com/nus-cs2103-AY2122S1/ip/pull/60/commits/7f61b2b2976ae38932af343a7c8c3b71405cc6aa
        //Inspired by his stream method.
        tasks.stream()
                .filter(task -> task.getTaskStatus().contains(keyword))
                .forEach(task -> text.append(++count[0]).append(".")
                        .append(task.getTaskStatus()).append("\n"));

        if (count[0] == 0) {
            return "Sorry. There is no tasks matching the keyword you give me!! :(\n";
        }

        return text.toString();
    }

    /**
     * Marks a specific task as done.
     *
     * @param index Integer indicates the index for the task.
     */
    public void markDone(int index) {
        Task task = this.tasks.get(index);
        task.markDone();
        lastExecution = new LastExecution("done", task, index, tasks);
    }

    /**
     * Deletes a specific task.
     *
     * @param index Integer indicates the index for the task.
     */
    public void delete(int index) {
        Task task = this.tasks.get(index);
        this.tasks.remove(index);
        lastExecution = new LastExecution("delete", task, index, tasks);
    }

    private void createNewTask(String taskType, String task, LocalDateTime parsedTime) {
        OperationType[] taskTypes = OperationType.values();
        for (OperationType t : taskTypes) {
            boolean isMatch = t.toString().equals(taskType.toUpperCase());
            if (isMatch) {
                Task newTask = t.assignTaskType(t, task, parsedTime);
                tasks.add(newTask);
                lastExecution = new LastExecution("add", newTask, tasks.size() - 1, tasks);
                break;
            }
        }
    }

    /**
     * Adds a task to the TaskLists. This method will automatically decide which type of the
     * task is added to the list.
     *
     * @param taskType String message indicates the task type.
     * @param task String message indicates the task info.
     * @param time String message indicates the time info.
     * @throws HydddException Throws when a task cannot be created or added to the TaskList.
     */
    public void add(String taskType, String task, String time) throws HydddException {
        Parser parser = new Parser("");
        LocalDateTime parsedTime = parser.parseTime(time);
        checkIsDuplicate(task);
        createNewTask(taskType, task, parsedTime);
    }

    /**
     * Returns a specific task users refer to.
     *
     * @param index An integer indicates the index of the task.
     * @return Task users refer to.
     * */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Integer indicates the size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Detects if the index taking in is invalid or not.
     *
     * @param index Integer indicates the index of the task.
     * @throws HydddException Throws when the index is invalid.
     */
    public void detectIndex(int index) throws HydddException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new HydddException(ExceptionType.WRONG_INDEX_ERROR);
        }
    }

    /**
     * Enum of all types of operations that is able to execute.
     * It also contains a method AssignTask Type to find the specific type of task to create.
     */
    public enum OperationType {
        BYE, DONE, DELETE, TELL, FIND, UNDO, HELP, LIST, TODO, DEADLINE, EVENT;

        /**
         * Returns a task in a specific operationType. It can be either todo, deadline or event.
         *
         * @param type Task type given to hyddd.
         * @param task Specific task info.
         * @param time Specific time info.
         * @return Task in a specific operationType. It can be either todo, deadline or event.
         */
        public Task assignTaskType(OperationType type, String task, LocalDateTime time) {
            Task newTask;
            switch (type) {
            case TODO: newTask = new ToDo(false, task);
            break;
            case DEADLINE: newTask = new Deadline(false, task, time);
            break;
            case EVENT: newTask = new Event(false, task, time);
            break;
            default: newTask = null;
            }

            return newTask;
        }
    }
}
