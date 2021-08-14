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
    private Task[] taskList;
    private int taskCounter;

    /**
     * Constructor for a TaskList.
     *
     * @param listCapacity The max number of tasks the task list can store.
     */
    public TaskList(int listCapacity) {
        taskList = new Task[listCapacity];
        taskCounter = 0;
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
            taskList[taskCounter] = new ToDo(task);
            break;
        case "Deadline":
            String[] deadlineDetails = task.split(" /by ");
            if (deadlineDetails.length == 1) {
                throw new MissingArgumentException("'/by'", "Deadline");
            }
            taskList[taskCounter] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            break;
        case "Event":
            String[] eventDetails = task.split(" /at ");
            if (eventDetails.length == 1) {
                throw new MissingArgumentException("'/at'", "Event");
            }
            taskList[taskCounter] = new Event(eventDetails[0], eventDetails[1]);
            break;
        default:
            // will NOT execute as Duke calls this function to add a task and it only calls them based on
            // the same switch cases.
            break;
        }
        taskCounter++;

        return new String[] {
                "I've added this task but it's not like I did for you or anything!",
                "  " + taskList[taskCounter - 1].toString(),
                String.format("Now you have %d %s in the list. Do your best doing them okay?",
                        taskCounter, taskCounter == 1 ? "task" : "tasks")
        };
    }

    /**
     * Prints the tasks in the list with indexing starting from 1.
     */
    public void printList() {
        for (int i = 0; i < taskCounter; i++) {
            Task currTask = taskList[i];
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
        if (index <= 0 || index > taskCounter) {
            throw new InvalidIndexException(taskCounter);
        }
        String[] message = {
                "You completed a task! Maybe you aren't so incompetent after all.",
                taskList[index - 1].markTaskAsDone()
        };
        return message;

    }
}