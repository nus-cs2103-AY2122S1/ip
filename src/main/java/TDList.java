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
        TDLTask createdTask = new TDLTask(str);
        toDoList.add(createdTask);
        Duke.dukeSays("added: " + str);
    }

    /**
     * Used to mark the corresponding task as done.
     *
     * @param taskNo The task number in the list.
     *
     * @return Duke's output from this command
     */
    public String markTaskAsDone(int taskNo) {
        if (taskNo >= 0 && taskNo < toDoList.size()) {
            TDLTask currTask = toDoList.get(taskNo);
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
