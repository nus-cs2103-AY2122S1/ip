import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        dukeUserInteraction();
    }

    public static void dukeUserInteraction() {

        DefaultDisplayMessage defaultDisplayMessage = new DefaultDisplayMessage();
        defaultDisplayMessage.execute();

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] inputValues = command.split(" ");
            System.out.println("    -----------------------------------------");

            if (command.equals("bye")) {
                new ByeCommand().execute();
                break;
            } else if (command.equals("list")) {
                new ListCommand(taskList).execute();
            } else if (inputValues[0].equals("done") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "done".
                new DoneCommand(taskList, inputValues).execute();
            } else if (inputValues[0].equals("delete") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "delete".
                new DeleteCommand(taskList, inputValues).execute();
            } else if (inputValues[0].equals("deadline")) {
                new DeadlineCommand(taskList, inputValues, command).execute();
            } else if (inputValues[0].equals("event")) {
                new EventCommand(taskList, inputValues, command).execute();
            } else if (inputValues[0].equals("todo")) {
                new TodoCommand(taskList, inputValues, command).execute();
            } else {
                System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("    -----------------------------------------");
            System.out.println();
        }
        sc.close();
    }
}
