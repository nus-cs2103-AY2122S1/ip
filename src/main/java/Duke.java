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
                ByeCommand bye = new ByeCommand();
                bye.execute();
                break;
            } else if (command.equals("list")) {
                ListCommand list = new ListCommand(taskList);
                list.execute();
            } else if (inputValues[0].equals("done") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "done".
                DoneCommand done = new DoneCommand(taskList, inputValues);
                done.execute();
            } else if (inputValues[0].equals("delete") && inputValues.length == 2) {
                //treat as unknown command if there is more than 1 number after "delete".
                DeleteCommand delete = new DeleteCommand(taskList, inputValues);
                delete.execute();
            } else if (inputValues[0].equals("deadline")) {
                DeadlineCommand deadline = new DeadlineCommand(taskList, inputValues, command);
                deadline.execute();
            } else if (inputValues[0].equals("event")) {
                EventCommand event = new EventCommand(taskList, inputValues, command);
                event.execute();
            } else if (inputValues[0].equals("todo")) {
                TodoCommand todo = new TodoCommand(taskList, inputValues, command);
                todo.execute();
            } else {
                System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("    -----------------------------------------");
            System.out.println();
        }
        sc.close();
    }
}
