import java.util.ArrayList;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 6. Delete
 *
 * Description:
 * Encapsulates the TaskList which contains a list of tasks
 *
 * @author Keith Tan
 */
public class Tasklist {

    private ArrayList<Task> tasks;

    public Tasklist() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Checks whether user inputted the time for the deadline/event task
     *
     * @param strArr String array containing the inputted description and time
     * @param event String stating the type of task to be added
     * @return String Returns the time of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no time found
     */
    private static String checkTime(String[] strArr, String event) throws MissingArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("time", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("time", event);
        } else {
            return strArr[1];
        }

    }

    /**
     * Adds the task to the list
     *
     * @param description String containing the description and time of the task
     * @param taskType String stating the type of task to be added
     * @return String Returns the success message of added the task to the list
     * @throws DukeException throws a duke exception depending on the error found
     */
    public String addTask(String description, String taskType) throws DukeException {

        Task newTask;
        switch(taskType) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                newTask = new Deadline(deadlineDetails[0], checkTime(deadlineDetails, "deadline"));
                break;
            case "event":
                String[] eventDetails = description.split(" /at ", 2);
                newTask = new Event(eventDetails[0], checkTime(eventDetails, "event"));
                break;
            default:
                //unexpected error occurs
                throw new InvalidCommandException();
        }

        tasks.add(newTask);
        String successMessage = "Got it. I've added this task:\n"
                + "  " + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        return successMessage;
    }

    /**
     * Marks the designated tasks as complete
     *
     * @param taskNumber task to be marked as complete
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String markTask(int taskNumber) throws IndexOutOfRangeException {

        if (taskNumber > tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size() );

        } else {

            if (tasks.get(taskNumber - 1).completeTask()) {

                String markTaskMessage = "Nice! I've marked this task as done:\n"
                        + tasks.get(taskNumber - 1).toString();
                return markTaskMessage;

            } else {

                String markTaskMessage = tasks.get(taskNumber - 1).toString() + " already marked!";
                return markTaskMessage;

            }

        }

    }

    /**
     * Delete the designated tasks from the task list
     *
     * @param taskNumber task to be deleted
     * @return String message to be printed depending on if tasks is marked
     * @throws IndexOutOfRangeException throws the exception if index given is out of
     *              range of the current list
     */
    public String deleteTask(int taskNumber) throws IndexOutOfRangeException {

        if (taskNumber> tasks.size()) {

            throw new IndexOutOfRangeException(taskNumber, tasks.size());

        } else {

            Task taskToRemove = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            String deletedTaskMessage = "Noted. I've removed this task:\n"
                    + "  " + taskToRemove.toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.";;
            return deletedTaskMessage;

        }

    }

    @Override
    public String toString() {

        String listString = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            String listItem = String.format("%d.%s", (i + 1), tasks.get(i).toString());
            if (i == (tasks.size() - 1)) {
                listString += listItem;
            } else {
                listString += listItem + "\n";
            }

        }
        return listString;

    }

}
