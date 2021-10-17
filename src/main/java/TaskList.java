/**
 * Class that handles commands
 */
public class TaskList {
    public Task[] taskList = new Task[100];
    public int i = 0;
    Storage storage;


    /**
     * Constructor for a TaskList instance
     */
    TaskList(Storage storage, Parser parser) {
        storage.checkStorage(parser, this);
    }

    /**
     * Executes the todo command
     * @param input
     * @return string feedback to user
     */
    public String todoCommand(String input) {
        taskList[i] = new ToDo(input);
        i++;
        String ads = toDoAddedMessage();
        System.out.println(ads);
        System.out.println(taskList);
        return toDoAddedMessage();
    }

    /**
     * Executes the deadline command
     * @param description
     * @param date
     * @return string feedback to user
     */
    public String deadlineCommand(String description, String date) {
        taskList[i] = new Deadline(description, date);
        i++;
        return deadlineAddedMessage();
    }

    /**
     * Executes the deadline command
     * @param description
     * @param date
     * @return string feedback to user
     */
    public String eventCommand(String description, String date) {
        taskList[i] = new Event(description, date);
        i++;
        return eventAddedMessage();
    }

    /**
     * Executes the deadline command
     * @param taskNo
     * @return string feedback to user
     */
    public String doneCommand(int taskNo) {
        taskList[taskNo - 1].markAsDone();
        return doneTaskMessage(taskNo);
    }

    /**
     * Executes the delete command
     * @param taskNo
     * @return string feedback to user
     */
    public String deleteCommand(int taskNo) {
        String type = taskList[taskNo - 1].getTask();
        String status = taskList[taskNo - 1].getStatusIcon();
        String task = taskList[taskNo - 1].getDescription();
        taskList[taskNo - 1] = null;
        if (taskNo < i + 1) {
            for (int n = taskNo - 1; n < i; n++) {
                taskList[n] = taskList[n + 1];
            }
        }
        i--;
        return deletedTaskMessage(type, status, task, taskNo);
    }

    public String findCommand(String keyword) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < i; j++) {
            Task task = taskList[j];
            if (task.getDescription().contains(keyword)) {
                String text = (j + 1) + ". " + "[" + taskList[j].getTask() + "]"
                        + "[" + taskList[j].getStatusIcon() + "] "
                        + taskList[j].getDescription() + "\n";
                stringBuilder.append(text);
            }
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * Checks the number of items in the tasklist
     * @return count of items in tasklist
     */
    public int getCount() {
        return this.i;
    }

    /**
     * Retrieves the task array tasklist
     * @return returns the tasklist array
     */
    public Task[] getTaskList() {
        return taskList;
    }

    /**
     * Executes the list command
     * @return a list of all tasks in the tasklist
     */
    public String listAll() {
        String output = "";
        for (int j = 0; j < i; j++) {
            if (taskList[j] instanceof ToDo) {
                output += j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription() + "\n";
            } else {
                output += j + 1 + ". [" + taskList[j].getTask() + "]" +
                        "[" + taskList[j].getStatusIcon() + "] " + taskList[j].getDescription()
                        + taskList[j].getDate() + "\n";
            }
        }
        return "Here are the tasks in your list: " + output;
    }

    private String toDoAddedMessage() {
        return "Got it. I've added this task: \n"
                + "[" + taskList[i - 1].getTask() + "]"
                + "[" + taskList[i - 1].getStatusIcon() + "] "
                + taskList[i - 1].getDescription()
                + "\n"
                + "Now you have " + (i) + " tasks in the list.";
    }

    private String deadlineAddedMessage() {
        return "Got it. I've added this task: \n"
                + "[" + taskList[i - 1].getTask() + "]"
                + "[" + taskList[i - 1].getStatusIcon() + "] "
                + taskList[i - 1].getDescription()
                + "\n"
                + "Now you have " + (i) + " tasks in the list.";
    }

    private String eventAddedMessage() {
        return "Got it. I've added this task: \n"
                + "[" + taskList[i - 1].getTask() + "]"
                + "[" + taskList[i - 1].getStatusIcon() + "] "
                + taskList[i - 1].getDescription()
                + "\n"
                + "Now you have " + (i) + " tasks in the list.";
    }

    private String doneTaskMessage(int taskNo) {
        return "Nice! I've marked this task as done: \n"
                + " [" + taskList[taskNo - 1].getStatusIcon() + "] "
                + taskList[taskNo - 1].getDescription();
    }

    private String deletedTaskMessage(String type, String status, String task, int taskNo) {
        return "Noted. I've removed this task: \n"
                + " [" + type + "] "
                + "[" + status + "] "
                + task
                + "\n"
                + "Now you have " + (i) + " tasks in the list.";
    }

}
