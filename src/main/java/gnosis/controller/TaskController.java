package gnosis.controller;

import static gnosis.util.DateTimeHelper.stringToDate;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.database.TaskDbManager;
import gnosis.model.Command;
import gnosis.model.CommandListener;
import gnosis.model.Deadline;
import gnosis.model.Event;
import gnosis.model.Task;
import gnosis.model.Todo;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;


public class TaskController implements CommandListener {

    private List<Task> tasks;

    // TODO: remove use of hardcoded value
    private TaskDbManager taskDbManager = new TaskDbManager("tasks");

    public TaskController() {
        this.tasks = taskDbManager.loadTasks();
    }

    @Override
    public void executeCommand(Command commandToExecute, String userInput, GnosisUI view) throws GnosisException {
        switch (commandToExecute) {
        case TODO:
            Todo td = this.addTodo(userInput);
            view.updateTaskManagementViewMessage(commandToExecute.name(), td, this.getNumOfTasks());
            break;
        case DEADLINE:
            Deadline dl = this.addDeadline(userInput);
            view.updateTaskManagementViewMessage(commandToExecute.name(), dl, this.getNumOfTasks());
            break;
        case EVENT:
            Event evt = this.addEvent(userInput);
            view.updateTaskManagementViewMessage(commandToExecute.name(), evt, this.getNumOfTasks());
            break;
        case LIST:
            view.displayAllTasksMessage(this.getTasks());
            break;
        case FIND:
            List<Task> filteredTasks = this.findMatchingTasks(userInput);
            view.displayFoundTasksMessage(filteredTasks, userInput);
            break;
        case DONE:
            // only if "done" command is call, we retrieve task index from user
            int taskIndex = Integer.parseInt(userInput.trim()) - 1;
            view.displayMarkedTaskMessage(this.markTaskAsDone(taskIndex), taskIndex + 1);
            break;
        case DELETE:
            taskIndex = Integer.parseInt(userInput.trim()) - 1;
            Task task = this.deleteTask(taskIndex);
            view.updateTaskManagementViewMessage(commandToExecute.name(), task, this.getNumOfTasks());
            break;
        default:
            throw new GnosisException(GnosisConstants.COMMAND_NOT_FOUND_MESSAGE);
        }

        // update storage for every execution
        taskDbManager.writeTasksToFile(this.tasks);
    }

    public boolean isTaskLoaded() {
        return this.taskDbManager.isDataFileAvail();
    }

    public boolean exportFile(File pathToExport) {
        return this.taskDbManager.copyFileToNewPath(pathToExport);
    }


    /**
     * Adds a t0do tasks and returns the t0do.
     *
     * @param tdo The t0do input to save.
     * @return t0do The t0do that is saved.
     * @throws GnosisException If input is empty.
     */
    public Todo addTodo(String tdo) throws GnosisException {
        if (tdo.trim().equalsIgnoreCase("")) {
            throw new GnosisException(GnosisConstants.TODO_EMPTY_EXCEPT_MESSAGE);
        }

        Todo td = new Todo(tdo);
        tasks.add(td);

        return td;
    }

    /**
     * Adds an event tasks to save.
     *
     * @param event event to save.
     * @return event The event that is saved.
     * @throws GnosisException If input is empty.
     */
    public Event addEvent(String event) throws GnosisException {
        String[] splitTaskInput = event.split("/at");

        if (splitTaskInput.length != 2) {
            //event empty exception
            throw new GnosisException(GnosisConstants.EVENT_EMPTY_EXCEPT_MESSAGE);
        }

        String taskName = splitTaskInput[0];
        String taskSchedule = splitTaskInput[1];

        Event et = new Event(taskName, stringToDate(taskSchedule));
        tasks.add(et);

        return et;
    }

    /**
     * Adds an deadline task to save.
     *
     * @param deadline deadline to save.
     * @return deadline the deadline task that is saved.
     * @throws GnosisException If input is empty.
     */
    public Deadline addDeadline(String deadline) throws GnosisException {
        String[] splitTaskInput = deadline.split("/by");

        if (splitTaskInput.length != 2) {
            throw new GnosisException(GnosisConstants.DEADLINE_EMPTY_EXCEPT_MESSAGE);
        }

        String taskName = splitTaskInput[0];
        String taskDeadline = splitTaskInput[1];
        Deadline dl = new Deadline(taskName, stringToDate(taskDeadline));
        tasks.add(dl);

        return dl;
    }

    /**
     * Marks selected task as done.
     *
     * @param taskIndex taskIndex indicates task to mark done.
     * @return task Task that is marked done.
     * @throws GnosisException If taskIndex is out of bounds
     */
    public Task markTaskAsDone(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }

        tasks.get(taskIndex).setDone(true);
        return tasks.get(taskIndex);
    }

    /**
     * Deletes selected task.
     *
     * @param taskIndex taskIndex indicates which task to delete.
     * @return deadline the deadline task that is saved.
     * @throws GnosisException If taskIndex is out of bounds
     */
    public Task deleteTask(int taskIndex) throws GnosisException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new GnosisException(GnosisConstants.TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE);
        }

        Task t = tasks.get(taskIndex);
        tasks.remove(taskIndex);

        return t;
    }

    /**
     * Finds matching tasks with specified keyword
     *
     * @param taskKeyword Matching Keyword to filter tasks.
     * @return List of filtered task matching keyword.
     */
    public List<Task> findMatchingTasks(String taskKeyword) {
        // using stream to filter out only matching task in task list
        return this.tasks.stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(taskKeyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.getTasks().size();
    }
}
