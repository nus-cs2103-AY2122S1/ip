import java.util.ArrayList;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 4. ToDos, Events, Deadlines
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
     * Adds the task to the list
     *
     * @return String Returns the success message of added the task to the list
     */
    public String addTask(String description, String taskType) {

        Task newTask;
        switch(taskType) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                if (deadlineDetails.length < 2) {
                    return "Please enter date after description";
                } else {
                    newTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                }
                break;
            case "event":
                String[] eventDetails = description.split(" /at ", 2);
                if (eventDetails.length < 2) {
                    return "Please enter duration after description";
                } else {
                    newTask = new Event(eventDetails[0], eventDetails[1]);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + description);
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
     */
    public String markTask(int taskNumber) {

        if (tasks.size() == 0) {

            return "Add something to tasklist first";

        } else if ((taskNumber - 1) > tasks.size()) {

            return "No such task exists";

        } else {

            tasks.get(taskNumber - 1).completeTask();
            String markTaskMessage = "Nice! I've marked this task as done:\n"
                    + tasks.get(taskNumber - 1).toString();
            return markTaskMessage;

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
