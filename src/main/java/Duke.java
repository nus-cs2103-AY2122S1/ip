import java.util.ArrayList;
import java.util.Scanner;

public class Duke{
    public static void main(String[] args) {
        String welcomeString = "____________________________________________________________\n"
                + "Yo! Duke here...on behalf of Yang Yuzhao.\n"
                + "What do ya want from me?\n"
                + "____________________________________________________________\n";
        String byeString = "____________________________________________________________\n"
                + "Duke out! Wake me up when ya need me again:)\n"
                + "____________________________________________________________\n";
        System.out.println(welcomeString);

        // ask for user input
        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        ArrayList<Task> storeRoom = new ArrayList<>();
        Task task;

        // not bye
        while (!nextLine.equals("bye")) {
            // check list
            if (nextLine.equals("list")) {
                System.out.println("____________________________________________________________\n"
                        + "Here are the tasks in your list:");
                int counter = 1;
                for (Task taskForLoop : storeRoom) {
                    System.out.println(counter
                            + "."
                            + taskForLoop);
                    counter++;
                }
                System.out.println("____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }

            // finish task by index
            if (nextLine.startsWith("done")) {
                int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                Task doneTask = storeRoom.get(intValue - 1);
                doneTask.doneTask();
                storeRoom.set(intValue - 1, doneTask);
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:\n  "
                        + doneTask
                        + "\n"
                        + "____________________________________________________________\n");
                nextLine = in.nextLine();
                continue;
            }

            // add task
            try {
                if (nextLine.startsWith("todo")) {
                    Task.isFirstWordValid(nextLine, "todo");
                    if (Task.isDescriptionEmpty(nextLine)) {
                        throw new DukeException("todo", DukeException.ErrorType.EMPTY_DESCRIPTION);
                    }
                    task = new ToDo(nextLine.substring(5));
                } else if (nextLine.startsWith("deadline")) {
                    Task.isFirstWordValid(nextLine, "deadline");
                    if (Task.isDescriptionEmpty(nextLine)) {
                        throw new DukeException("deadline", DukeException.ErrorType.EMPTY_DESCRIPTION);
                    }
                    task = Deadline.splitDeadline(nextLine);
                } else {
                    Task.isFirstWordValid(nextLine, "event");
                    if (Task.isDescriptionEmpty(nextLine)) {
                        throw new DukeException("event", DukeException.ErrorType.EMPTY_DESCRIPTION);
                    }
                    task = Event.splitEvent(nextLine);
                }
                storeRoom.add(task);
                System.out.println("____________________________________________________________\n"
                        + "Got it. I've added this task:\n  "
                        + task
                        + "\n"
                        + "Now you have "
                        + storeRoom.size()
                        + " tasks in the list."
                        + "\n"
                        + "____________________________________________________________\n");
                nextLine = in.nextLine();
            } catch (DukeException dukeException){
                if (dukeException.errorType.equals(DukeException.ErrorType.EMPTY_DESCRIPTION)){
                    System.out.println("____________________________________________________________\n"
                            + "OOPS!!! The description of a "
                            + dukeException.getMessage()
                            + " cannot be empty.\n"
                            + "____________________________________________________________\n");
                    nextLine = in.nextLine();
                    continue;
                }
                if (dukeException.errorType.equals(DukeException.ErrorType.INVALID_INPUT)){
                    System.out.println("____________________________________________________________\n"
                            + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                            + "____________________________________________________________\n");
                    nextLine = in.nextLine();
                    continue;
                }
            }
        }

        // bye
        System.out.println(byeString);
        in.close();
    }
}
