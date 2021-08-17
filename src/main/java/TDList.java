import java.util.ArrayList;

/**
 * This class encapsulates the list element that Duke uses to store tasks.
 */
public class TDList {

    private ArrayList<TDLTask> toDoList = new ArrayList<>();

    public TDList() {

    }

    /**
     * Used for adding things to the to do list.
     *
     * @param str   Thing to add to the list.
     */
    public void tdlAdd(String str) {
        toDoList.add(createdTask);


        Duke.dukeSays("Ok, I have added this task: \n" + createdTask.getLineOfTaskInfo()
            + "\nCurrent total amount of tasks in list: " + toDoList.size());
    }

    private TDLTask.TaskType checkTaskType(String command) {
        if (command.startsWith("deadline")) {
            return TDLTask.TaskType.DEADLINE;
        } else if (command.startsWith("event")) {
            return  TDLTask.TaskType.EVENT;
        } else {
            return TDLTask.TaskType.TODO;
        }
    }

    /**
     * Used to mark the corresponding task as done.
     *
     * @param taskNo The task number in the list.
     *
     * @return Duke's output from this command
     */
    public String markTaskAsDone(int taskNo) {
        //Task list starts from 1 instead of 0 so input in command is 1 more than
        //the task's index in the list
        int taskIndex = taskNo - 1;

        if (taskIndex >= 0 && taskIndex < toDoList.size()) {
            TDLTask currTask = toDoList.get(taskIndex);
            currTask.setAsDone();

            return "Nice! I've marked this task as done:\n" + currTask.getLineOfTaskInfo();
        } else {
            return "Invalid Task Specified";
        }
    }


    /**
     * Used to print out contents of the list nicely.
     */
    public void printOutTDL() {
        String printThis = "";
        String nextLine = "\n";

        int serialNo = 1;
        for (TDLTask ele : toDoList) {
            String endOfCurrLine = nextLine;
            if (serialNo == toDoList.size()) {
                endOfCurrLine = "";
            }

            String currLine = serialNo + ". " + ele.getLineOfTaskInfo() + endOfCurrLine;
            printThis = printThis + currLine;

            serialNo++;
        }

        Duke.dukeSays("Here are your current tasks: \n" + printThis);
    }


}
