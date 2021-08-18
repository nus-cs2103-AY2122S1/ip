import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
                Task.printList(storeRoom);
                nextLine = in.nextLine();
                continue;
            }

            // finish task by index
            if (nextLine.startsWith("done")) {
                int intValue = Integer.parseInt(nextLine.replaceAll("[^0-9]", ""));
                Task doneTask = storeRoom.get(intValue - 1);
                doneTask.doneTask();
                storeRoom.set(intValue - 1, doneTask);
                doneTask.printDoneTask();
                nextLine = in.nextLine();
                continue;
            }

            // delete task
//            if (nextLine.startsWith("delete"))

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
                task.addTask(storeRoom);
                nextLine = in.nextLine();
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
                nextLine = in.nextLine();
                continue;
            }
        }

        // bye
        System.out.println(byeString);
        in.close();
    }
}

