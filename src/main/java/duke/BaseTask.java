package duke;


/**
 *  This class encapsulates a Task element in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */
public abstract class BaseTask {
    private String taskName;
    private boolean isDone;

    /**
     * Identifies the different types of tasks available.
     */
    public enum TaskType {
        NONE,
        TODO,
        EVENT,
        DEADLINE
    }



    /**
     * Creates a new Base Task, used to directly create new tasks.
     *
     * @param taskName The name and description of the task.
     * @param isDone True if task was already completed, false if task is still incomplete.
     */
    public BaseTask(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Sets the current task as finished.
     *
     * @throws DukeExceptionBase if the task being marked as done was already done.
     */
    public void setAsDone() throws DukeExceptionBase {
        if (isTaskDone()) {
            throw new DukeExceptionBase("The specified task was already done.");
        }
        isDone = true;
    }

    /**
     * Checks whether the task is finished.
     *
     * @return true if finished, false if undone.
     */
    public boolean isTaskDone() {
        return isDone;
    }

    /**
     * Gets the name/description of the task.
     *
     * @return the name/description of the task.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Gets the name/description of the task, as well as whether it is done neatly formatted in one line.
     *
     * @return the String containing the line describing the task.
     */
    public String getLineOfTaskInfo() {
        String taskTypeCheckbox = this.getTaskTypeStringHeader();

        String doneCheckboxStr = "";
        if (isDone) {
            doneCheckboxStr = "[X]";
        } else {
            doneCheckboxStr = "[ ]";
        }

        String returnThis = taskTypeCheckbox + doneCheckboxStr + " " + this.getTaskDescription();

        return returnThis;
    }

    /**
     * Gets the type of the Task in enum form.
     *
     * @return the enum representing the type of the current task.
     */
    public TaskType getTaskType() {
        return TaskType.NONE;
    }

    /**
     * Returns a string containing the letter representing the task type in square brackets.
     *
     * @return a string containing [X] where X is the letter representing the task type.
     */
    public String getTaskTypeStringHeader() {
        return "[ ]";
    }

    /**
     * Returns a string containing the task's name and additional descriptions if applicable.
     *
     * @return the string describing the task.
     */
    public String getTaskDescription() {
        return this.getTaskName();
    }

    /**
     * Returns the extra info this task contains, if any.
     *
     * @return the String containing the extra info of this task if any. Returns null if there is none.
     */
    public String getTaskExtraInfo() {
        return null;
    }

    /**
     * Searches task to see if it matches provided keywords.
     *
     * @param keywords The keywords to use during the search.
     * @return true if the task matches, false otherwise.
     */
    public boolean searchForKeywords(String keywords) {
        return this.taskName.contains(keywords);
    }

    /**
     * Checks the type of task represented by the current command.
     *
     * @param command The command inputted by the user.
     * @return the task type. Returns NONE if it is not a task.
     */
    public static BaseTask.TaskType checkTaskType(String command) {
        if (command.startsWith("deadline")) {
            return BaseTask.TaskType.DEADLINE;
        } else if (command.startsWith("event")) {
            return BaseTask.TaskType.EVENT;
        } else if (command.startsWith("todo")) {
            return BaseTask.TaskType.TODO;
        } else {
            return BaseTask.TaskType.NONE;
        }
    }

    /**
     * Gives the short form representing a task type, this function returns the enum representing it.
     *
     * @param taskLetter the letter of the task type that is wanted.
     * @return the enum representing the wanted task type.
     */
    public static BaseTask.TaskType convertTaskLetterToEnum(String taskLetter) {
        if (taskLetter.equals("T")) {
            return TaskType.TODO;
        } else if (taskLetter.equals("D")) {
            return TaskType.DEADLINE;
        } else if (taskLetter.equals("E")) {
            return TaskType.EVENT;
        } else {
            return TaskType.NONE;
        }
    }

}
