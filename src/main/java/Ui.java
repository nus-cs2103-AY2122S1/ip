import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private FormatAdapter adapter;

    public Ui () {
        this.adapter = new FormatAdapter();
    }

    public void welcome() {
        System.out.println(adapter.formatMessage( "Hello! I'm Peoduo\n" + FormatAdapter.getIndentation() + "Can I help you?\n"));
    }

    public void exit() {
        System.out.println(adapter.formatMessage("Bye. Hope to see you again soon!\n"));
    }

    public void readUserInput(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        parser.parse(scanner);
        scanner.close();
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

    public void nonEmptyDescriptionMessage(String taskType) {
        if(taskType.startsWith("a")|| taskType.startsWith("e") ||taskType.startsWith("i") ||
                taskType.startsWith("o") || taskType.startsWith("u")) {
            System.out.println(adapter.formatMessage("OOPS!!! The description of an " + taskType + " cannot be empty.\n"));
        } else {
            System.out.println(adapter.formatMessage("OOPS!!! The description of a " + taskType + " cannot be empty.\n"));
        }
    }

    public void invalidDateFormMessage() {
        System.out.println(adapter.formatMessage("Please enter a valid date in the format:/at yyyy-mm-dd!\n"));
    }

    public void cannotInterpretMessage() {
        System.out.println(adapter.formatMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
    }

    public void addMessage(ArrayList<Task> userInputRecord,Task task) {
        System.out.println(adapter.formatMessage( "Got it. I've added this task:\n" +
                FormatAdapter.getSubIndentation() + task + "\n" + FormatAdapter.getIndentation() +
                "Now you have " + userInputRecord.size() + " tasks in the list.\n" ));
    }

    public void markAsDoneMessage(ArrayList<Task> userInputRecord, int itemToComplete) {
        System.out.println(adapter.formatMessage("Nice! I've marked this task as done:\n" +
                FormatAdapter.getSubIndentation() + userInputRecord.get(itemToComplete) + "\n"));
    }

    public void deleteMessage(ArrayList<Task> userInputRecord, Task itemDeleted) {
        System.out.println(adapter.formatMessage("Noted. I've removed this task:\n" +
                FormatAdapter.getSubIndentation() + itemDeleted + "\n" + FormatAdapter.getIndentation() +
                "Now you have " + userInputRecord.size() + " tasks in the list.\n"));
    }

    public void absentIDMessage() {
        System.out.println(adapter.formatMessage("Oops, the ID of the task does not exist!\n"));
    }

    public void invalidIDMessage() {
        System.out.println(adapter.formatMessage("Please enter a valid ID!\n"));
    }

}
