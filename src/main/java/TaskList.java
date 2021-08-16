import java.util.ArrayList;

/**
 * TaskList class represents a list of tasks.
 *
 * @author Chng Zi Hao
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Format the raw information by splitting it to get the description and due date of deadline.
     *
     * @param info User input containing relevant information.
     * @return String array with information of deadline split to description and date.
     * @throws DukeException For missing arguments.
     */
    public String[] extractDeadline (String info) throws DukeException{
        if (!info.contains("/by")) {
            throw new DukeException("Error: '/by' argument is missing!");
        }
        String[] description = info.split(" /by ", 2);

        if (description.length < 2 || description[1].equals("")) {
            throw new DukeException("Error: OOPS!!! Argument after '/by' is empty!");
        }
        return description;
    }

    /**
     * Format the raw information by splitting it to get the description and timeframe of event.
     *
     * @param info User input containing relevant information.
     * @return String array with information of event split to description and event timeframe.
     * @throws DukeException For missing arguments.
     */
    public String[] extractEvent (String info) throws DukeException{
        if (!info.contains("/at")) {
            throw new DukeException("Error: '/at' argument is missing!");
        }
        String[] description = info.split(" /at ", 2);
        if (description.length < 2 || description[1].equals("")) {
            throw new DukeException("Error: OOPS!!! Argument after '/at' is empty!");
        }
        return description;
    }
    /**
     * Process user input and adds task that user has given and formats the message specific to the task.
     *
     * @param info Information about the task that user has entered.
     * @param taskType The type of task user has specified
     * @return Returns formatted string to be printed out to show task has been added or not.
     * @throws DukeException For missing arguments when initialising Task.
     */
    public String addTask(String info, Command taskType) throws DukeException{
        switch (taskType) {
            case TODO:
                ToDo toDo = new ToDo(info);
                list.add(toDo);
                break;
            case DEADLINE:
                String[] deadlineInfo = extractDeadline(info);
                Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                list.add(deadline);
                break;
            case EVENT:
                String[] eventInfo = extractEvent(info);
                Event event = new Event(eventInfo[0], eventInfo[1]);
                list.add(event);
                break;
            default:
                throw new DukeException("Error: No such task type!");
        }
        String totalTask = String.format("Now you have %d task(s) in the list.", list.size());
        return String.format("Got it! I've added this task:\n  %s\n%s", list.get(list.size() - 1), totalTask);
    }

    /**
     * Prints out the task list and shows all the task user has added in order.
     */
    public void printTaskList() {
        System.out.println(Duke.DIVIDER);
        if (list.size() == 0) {
            System.out.println("List is empty. Add something to the list!");
            System.out.println(Duke.DIVIDER);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String detail = String.format("%d.%s", i + 1, list.get(i));
            System.out.println(detail);
        }
        System.out.println(Duke.DIVIDER);
    }

    /**
     * Marks task as done.
     *
     * @param index index of task to be mark as done.
     * @return a message of whether task has been successfully added or not
     * @throws DukeException For invalid indexes given by user.
     */
    public String markTaskDone(int index) throws DukeException{
        if (index >= 0 && index < list.size()) {
            if (list.get(index).getStatusIcon().equals(" ")) {
                list.get(index).markDone();
                return "Good job! I've marked this task as done:\n  " + list.get(index);
            } else {
                return "Task has already been completed!";
            }
        } else {
            throw new DukeException("No such task :< (Check the index input!!!)");
        }
    }

    /**
     * Deletes task at index specified by user.
     *
     * @param index Index of tasks to be deleted.
     * @return String message that task has been deleted.
     * @throws DukeException For invalid indexes given by user.
     */
    public String deleteTask(int index) throws DukeException{
        if (index >= 0 && index < list.size()) {
            Task removed = list.remove(index);
            String totalTask = String.format("Now you have %d task(s) in the list.", list.size());
            return String.format("Noted! I've removed this task:\n  %s\n%s", removed, totalTask);
        } else {
            throw new DukeException("No such task :< (Check the index input!!!)");
        }
    }
}
