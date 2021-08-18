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
    public void tdlAdd(String str, TDLTask.TaskType currTaskType) {
        TDLTask createdTask;

        int indexOfSlash = -1;

        switch (currTaskType) {
        case TODO:
            createdTask = new ToDosTask(str);
            break;
        case EVENT:
            indexOfSlash = str.indexOf("/at");
            if (indexOfSlash == -1) {
                Duke.dukeSays("Something is wrong with your Event task.");
                return;
            }

            String eventTaskName = str.substring(6, indexOfSlash);
            String eventAtWhere = "";
            try {
                eventAtWhere = str.substring(indexOfSlash + 4);
            } catch (StringIndexOutOfBoundsException e) {
                Duke.dukeSays("Adding a event description is required.");
                return;
            }

            createdTask = new EventTask(eventTaskName, eventAtWhere);
            break;
        case DEADLINE:
            indexOfSlash = str.indexOf("/by");
            if (indexOfSlash == -1) {
                Duke.dukeSays("Something is wrong with your Deadline task.");
                return;
            }

            String deadlineTaskName = str.substring(9, indexOfSlash);
            String deadlineByWhen;

            try {
                deadlineByWhen = str.substring(indexOfSlash + 4);
            } catch (StringIndexOutOfBoundsException e) {
                Duke.dukeSays("Adding a deadline description is required");
                return;
            }

            createdTask = new DeadlineTask(deadlineTaskName, deadlineByWhen);
            break;
        default:
            createdTask = new TDLTask(str);
        }

        toDoList.add(createdTask);


        Duke.dukeSays("Ok, I have added this task: \n" + createdTask.getLineOfTaskInfo()
            + "\nCurrent total amount of tasks in list: " + toDoList.size());


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
