import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        toDoEventDeadlines();
    }

    /**
     * Duke now recognised to dos, events and deadlines and adds them to the list accordingly
     * Inputs are taken by a scanner from the user's keyboard.
     */
    public static void toDoEventDeadlines() {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("-----------------------------------------");
        System.out.println(" Hello! I am Duke");
        System.out.println(" What can I do for you?");
        System.out.println("-----------------------------------------");
        System.out.println();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {

            String command = sc.nextLine();
            String[] inputValue = command.split(" ");
            System.out.println("    -----------------------------------------");

            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    -----------------------------------------");
                break;
            } else if (command.equals("list")) {
                System.out.println(("    Here are the tasks in your list:"));
                for (int i = 0; i < taskList.size(); i++) {
                    int currNum = i + 1;
                    Task currTask = taskList.get(i);
                    System.out.println("     " + currNum + ". " + currTask.toString());
                }
            } else if (inputValue[0].equals("done")) {
                int idx = Integer.parseInt(inputValue[1]);
                Task taskToComplete = taskList.get(idx - 1);
                taskToComplete.setIsDone(true);
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + taskToComplete.toString());
            } else if (inputValue[0].equals("deadline")) {
                int dateTimeIndex = command.indexOf("/");
                String description = command.substring(inputValue[0].length() + 1, dateTimeIndex - 1);
                String dateTime = command.substring(dateTimeIndex + 4);
                Task deadline = new Deadline(description, dateTime);
                taskList.add(deadline);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + deadline.toString());
                System.out.println("     Now you have " + taskList.size() + " tasks in the list");
            } else if (inputValue[0].equals("event")) {
                int dateTimeIndex = command.indexOf("/");
                String description = command.substring(inputValue[0].length() + 1, dateTimeIndex - 1);
                String dateTime = command.substring(dateTimeIndex + 4);
                Task event = new Event(description, dateTime);
                taskList.add(event);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + event.toString());
                System.out.println("     Now you have " + taskList.size() + " tasks in the list");
            } else if (inputValue[0].equals("todo")){
                String description = command.substring(inputValue[0].length() + 1);
                Task toDo = new ToDo(description);
                taskList.add(toDo);
                System.out.println("     Got it. I have added this task:");
                System.out.println("       " + toDo.toString());
                System.out.println("     Now you have " + taskList.size() + " tasks in the list");
            }
            System.out.println("    -----------------------------------------");
            System.out.println();
        }
        sc.close();
    }
}
