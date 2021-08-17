import java.util.ArrayList;

public class TDList {

    private ArrayList<String> toDoList = new ArrayList<>();

    public TDList() {

    }

    /**
     * Used for adding things to the to do list.
     *
     * @param str   Thing to add to the list.
     */
    public void tdlAdd(String str) {
        toDoList.add(str);
        Duke.dukeSays("added: " + str);
    }



    /**
     * Used to print out contents of the list nicely.
     */
    public void printOutTDL() {
        String printThis = "";
        String nextLine = "\n";

        int serialNo = 1;
        for (String ele : toDoList) {
            String endOfCurrLine = nextLine;
            if (serialNo == toDoList.size()) {
                endOfCurrLine = "";
            }

            String currLine = serialNo + ". " + ele + endOfCurrLine;
            printThis = printThis + currLine;

            serialNo++;
        }

        Duke.dukeSays(printThis);
    }


}
