import java.util.ArrayList;
import java.util.List;

/**
 * TaskManager keep track of the Task in the memory.
 */
public class TaskManager {
    private List<Task> taskList = new ArrayList<Task>();

    public TaskManager(){

    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * List all tasks inside the task manager.
     */
    public void listAll() {
        if (taskList.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
//                int index = i + 1;
//                String marked = taskList.get(i).getStatusIcon();
//                String taskInformation = taskList.get(i).getValue();
//                System.out.println(index + ".[" + marked + "] " + taskInformation);
                int index = i + 1;
                System.out.println(index + "" + taskList.get(i));
            }
            System.out.println();
        } else {
            System.out.println("Looks like there isn't any task!");
        }
    }

    public void markTaskDoneByIndex(int index) {
        Task taskToBeMarked = taskList.get(index - 1);
        taskToBeMarked.markDone();
        System.out.println("Nice! I've marked this task as done: ");
        String marked = taskToBeMarked.getStatusIcon();
        String taskInformation = taskToBeMarked.getValue();
        System.out.println("\t[" + marked + "] " + taskInformation);
    }

    /**
     * Get the total number of task.
     * @return the number of task in total.
     */
    public int getTotalNumberOfTask(){
        return taskList.size();
    }

    public boolean executeCommand(String input) {
        boolean run = true;
        if (input.toUpperCase().equals("BYE")) {
            System.out.println("Bye. Hope to see you again soon!");
            run = false;
        } else if (input.toUpperCase().equals("LIST")) {
            listAll();
        }else if (isDone(input)) {
            String[] stringArr = input.split(" ");
            if (stringArr.length > 1) {
                String taskNumber = stringArr[1];
                if (isNumeric(taskNumber)) {
                    int taskNum = Integer.parseInt(taskNumber);
                    if (taskNum > 0 && (taskNum - 1) < getTotalNumberOfTask()) {
                        markTaskDoneByIndex(taskNum);
                    } else {
                        System.out.println("Task number is not in the list!");
                    }

                } else {
                    System.out.println("Please enter a valid task number!");
                }

            } else {
                System.out.println("Please enter the task number after done! E.g \"done 2\"");
            }
        }else {

            Task newTask = null;
            if (input.toUpperCase().contains("TODO")) {
                newTask = new Todo(input.substring(5));
            } else if (input.toUpperCase().contains("DEADLINE")) {
                if (input.contains("/by")) {
                    String[] stringArr = input.substring(9).split("/by");
                    newTask = new Deadline(stringArr[0], stringArr[1].strip());
                }
            } else if (input.toUpperCase().contains("EVENT")) {
                if (input.contains("/at")) {
                    String[] stringArr = input.substring(6).split("/at");
                    newTask = new Event(stringArr[0], stringArr[1].strip());
                }
            }
            if (newTask != null) {
                System.out.println("Got it. I've added this task.");
                addTask(newTask);
                System.out.println(newTask);
                System.out.println("Now you have " + getTotalNumberOfTask() + " tasks in the list.");
            } else {
                System.out.println("Invalid command!");
                System.out.println("Try TODO, DEADLINE or EVENT follow by task description.");
            }

        }
        return run;
    }

    /**
     * Check if a given input has done command.
     * @param input a string that is the input of the user.
     * @return a boolean if done command is found.
     */
    public boolean isDone(String input) {
        if (input.length() >= 4) {
            return input.toUpperCase().substring(0,4).equals("DONE");
        } else {
            return false;
        }
    }

    /**
     * Check if input string is numeric or not.
     * @param input a string input from user.
     * @return a boolean if input is numeric.
     */
    public boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
