import java.util.ArrayList;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * The TaskList Class is a representation of a list of tasks that Duke is keeping track of.
 * Tasks can be added into the list via Duke and the list can be displayed as well.
 *
 * @author Benedict Chua
 */
public class TaskList {
    private static final String INDENTATION = "     ";
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a given task to the tasks list.
     *
     * @param task the task to add to the list.
     * @return String representation of messages stating that the task has been added
     */
    public String[] addToList(String task, String typeOfTask) throws MissingArgumentException {
        switch (typeOfTask) {
        case "ToDo":
            taskList.add(new ToDo(task));
            break;
        case "Deadline":
            String[] deadlineDetails = task.split(" /by ");
            if (deadlineDetails.length == 1) {
                throw new MissingArgumentException("'/by'", "Deadline");
            }
            taskList.add(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            break;
        case "Event":
            String[] eventDetails = task.split(" /at ");
            if (eventDetails.length == 1) {
                throw new MissingArgumentException("'/at'", "Event");
            }
            taskList.add(new Event(eventDetails[0], eventDetails[1]));
            break;
        default:
            // will NOT execute as Duke calls this function to add a task and it only calls them based on
            // the same switch cases.
            break;
        }

        return new String[] {
                "I've added this task but it's not like I did it for you or anything!",
                String.format("  %s", taskList.get(taskList.size() - 1)),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        taskList.size(), taskList.size() == 1 ? "task" : "tasks")
        };
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     */
    public void printList() {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println(INDENTATION + String.format("%d:%s", i + 1, currTask));
        }
    }

    /**
     * Marks tasks (based on index) as done if it exists.
     *
     * @param index index given by the User for Task in the TaskList (starts from 1).
     * @return message of the completion of the Task if it exists, else user will be informed that no such task exists.
     */
    public String[] markTaskAsDone(int index) throws InvalidIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new InvalidIndexException(taskList.size());
        }
        String[] message = {
                "You completed a task! Maybe you aren't so incompetent after all.",
                taskList.get(index - 1).markTaskAsDone()
        };
        return message;

    }

    public String[] deleteTask(int index) throws InvalidIndexException {
        if (index <= 0 || index > taskList.size()) {
            throw new InvalidIndexException(taskList.size());
        }

        Task deletedTask = taskList.remove(index - 1);
        String[] message = {
                "I've deleted this task so show me some gratitude!",
                String.format("  %s", deletedTask),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        taskList.size(), taskList.size() == 1 ? "task" : "tasks")
        };
        return message;

    }
}