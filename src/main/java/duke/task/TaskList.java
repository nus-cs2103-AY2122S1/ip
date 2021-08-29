package duke.task;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import duke.ResponseLogic;
import duke.Storage;

public class TaskList {

    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the task list response.
     *
     * @param responseLogic The UI object of the current Duke object.
     * @return The response containing the task list.
     */
    public String getTaskList(ResponseLogic responseLogic) {
        StringBuilder sb = new StringBuilder();
        if (this.tasks.size() == 0) {
            return responseLogic.noTaskAvailableResponse();
        } else {
            sb.append(responseLogic.taskListHeaderResponse());
            for (int i = 0; i < this.tasks.size(); i++) {
                Task task = this.tasks.get(i);
                sb.append(responseLogic.taskWithIndexReponse(i + 1, task));
            }
            return sb.toString();
        }
    }

    /**
     * Returns the response containing the task which occurs on the specified date.
     *
     * @param dateString The date to filter tasks by.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response containing the task which occurs on the specified date.
     */
    public String getTasksOnDate(String dateString, ResponseLogic responseLogic) {
        StringBuilder sb = new StringBuilder(responseLogic.tasksOnDateHeaderResponse());
        LocalDate date = LocalDate.parse(dateString);
        int index = 1;

        for (Task task : this.tasks) {
            if (task instanceof Deadline) {
                LocalDate deadline = ((Deadline) task).getDeadline();
                if (deadline.equals(date)) {
                    sb.append(responseLogic.taskWithIndexReponse(index, task));
                }
            } else if (task instanceof Event) {
                LocalDate time = ((Event) task).getTime();
                if (time.equals(date)) {
                    sb.append(responseLogic.taskWithIndexReponse(index, task));
                }
            }
        }
        return sb.toString();
    }

    /** Returns the response containing tasks with the specified keyword.
     *
     * @param keyword The keyword specified by the user.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response containing tasks with the specified keyword.
     */
    public String getTasksWithKeyword(String keyword, ResponseLogic responseLogic) {
        StringBuilder sb = new StringBuilder(responseLogic.tasksWithKeywordHeaderResponse());
        int index = 1;
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                sb.append(responseLogic.taskWithIndexReponse(index, task));
            }
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param taskNumber The index number of the task to be marked as done.
     * @param storage The Storage object of the current Duke object.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response when a task is marked as done.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public String markTaskAsDone(
            int taskNumber, Storage storage, ResponseLogic responseLogic) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.update(this.tasks);
        return responseLogic.taskMarkedDoneResponse(task);
    }

    /**
     * Deletes a task.
     *
     * @param taskNumber The index number of the task to be deleted.
     * @param storage The Storage object of the current Duke object.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response when a task is deleted.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public String deleteTask(
            int taskNumber, Storage storage, ResponseLogic responseLogic) throws FileNotFoundException {
        Task task = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        storage.update(this.tasks);
        return responseLogic.deleteTaskResponse(task);
    }

    /**
     * Adds a Todo Task.
     *
     * @param description The description of the Todo task.
     * @param storage The Storage object of the current Duke object.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response when a Todo task is added.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public String addTodo(
            String description, Storage storage, ResponseLogic responseLogic) throws FileNotFoundException {
        Task task = new Todo(description);
        this.tasks.add(task);
        storage.update(this.tasks);
        return responseLogic.addTaskResponse(task);
    }

    /**
     * Adds a Deadline Task.
     *
     * @param description The description of the Deadline task.
     * @param deadline The deadline of the task.
     * @param storage The Storage object of the current Duke object.
     * @param responseLogic The UI object of the current Duke object.
     * @return The response when a Deadline task is added.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public String addDeadline(String description, String deadline, Storage storage,
                              ResponseLogic responseLogic) throws FileNotFoundException {
        Task task = new Deadline(description, deadline);
        this.tasks.add(task);
        storage.update(this.tasks);
        return responseLogic.addTaskResponse(task);
    }

    /**
     * Adds an Event task.
     *
     * @param description The description of the Event task.
     * @param time The time of the event.
     * @param storage The Storage object of the current Duke object.
     * @param responseLogic The UI object of the current Duke object.
     * @retrun The response when an Event task is added.
     * @throws FileNotFoundException If the file containing the tasks is not found.
     */
    public String addEvent(String description, String time,
                           Storage storage, ResponseLogic responseLogic) throws FileNotFoundException {
        Task task = new Event(description, time);
        this.tasks.add(task);
        storage.update(this.tasks);
        return responseLogic.addTaskResponse(task);
    }
}
