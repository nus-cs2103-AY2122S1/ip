package duke;

import java.util.ArrayList;

public class ParserUi implements Ui{
    private FormatAdapter adapter;
    public ParserUi() {
        this.adapter = new FormatAdapter();
    }


    //This method prints the saved list of events from the user.
    public void printUserInputRecord(ArrayList<Task> userInputRecord) {
        if(userInputRecord.isEmpty()) {
            System.out.println(adapter.formatMessage("Ah oh, seems like nothing is added yet :( \n" +
                    FormatAdapter.getIndentation() + "Try to input something first! \n" ));
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < userInputRecord.size(); i++) {
                System.out.println("     " + (i + 1) + "." + userInputRecord.get(i));
            }
            System.out.println("    ____________________________________________________________");
        }
    }

    public void cannotInterpretMessage() {
        System.out.println(adapter.formatMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
    }
}
