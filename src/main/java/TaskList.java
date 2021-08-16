/**
 * TaskList class represents a list of tasks.
 *
 * @author Chng Zi Hao
 */
public class TaskList {
    private Task[] list;
    private int currentIndex;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new Task[100];
        this.currentIndex = 0;
    }

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
     */
    public String addTask(String info, Command taskType) throws DukeException{
        if (currentIndex >= 100) {
            return "List is full.";
        }
        switch (taskType) {
            case TODO:
                ToDo toDo = new ToDo(info);
                list[currentIndex] = toDo;
                break;
            case DEADLINE:
                String[] deadlineInfo = extractDeadline(info);
                Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                list[currentIndex] = deadline;
                break;
            case EVENT:
                String[] eventInfo = extractEvent(info);
                Event event = new Event(eventInfo[0], eventInfo[1]);
                list[currentIndex] = event;
                break;
            default:
                throw new DukeException("Error: No such task type!");
        }
        currentIndex++;
        String totalTask = String.format("Now you have %d task(s) in the list.", currentIndex);
        return String.format("Got it! I've added this task:\n  %s\n%s", list[currentIndex - 1], totalTask);
    }

    /**
     * Prints out the task list and shows all the task user has added in order.
     */
    public void printTaskList() {
        System.out.println(Duke.DIVIDER);
        if (currentIndex == 0) {
            System.out.println("List is empty. Add something to the list!");
            System.out.println(Duke.DIVIDER);
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currentIndex; i++) {
            String detail = String.format("%d.%s", i + 1, list[i]);
            System.out.println(detail);
        }
        System.out.println(Duke.DIVIDER);
    }

    /**
     * Marks task as done.
     *
     * @param index index of task to be mark as done.
     * @return a message of whether task has been successfully added or not
     */
    public String markTaskDone(int index) throws DukeException{
        if (index >= 0 && index < currentIndex) {
            if (list[index].getStatusIcon().equals(" ")) {
                list[index].markDone();
                return "Good job! I've marked this task as done:\n  " + list[index];
            } else {
                return "Task has already been completed!";
            }
        } else {
            throw new DukeException("No such task :< (Check the index input!!!)");
        }
    }
}
