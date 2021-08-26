import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ResponseMessage {
    static String exitMessage() {
        return "\t" + "Bye. Hope to see you again soon!";
    }

    static String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    static String taskDeletedMessage(Task task) {
        return "\t" + "Noted. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    static String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    static String numOfTasksInList(List<Task> tasksList) {
        return "\t" + "Now you have " + tasksList.size()
                + (tasksList.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    static String tasksInYourListMessage(List<Task> tasksList) {
        String response = "\t" + "Here are the tasks in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator();

        for (int i = 0; i < tasksList.size(); i++) {
            if (i != 0) {
                response += System.lineSeparator();
            }

            response += "\t\t" + (i + 1) + "." + "\t" + tasksList.get(i).toString();
        }

        return response;
    }

    static String tasksOnDateMessage(String specificDate, List<Task> tasksList) {
        List<Task> tasksToPrintList = new ArrayList<Task>();

        for (int i = 0; i < tasksList.size(); i++) {
            if (tasksList.get(i).isOnDate(specificDate)) {
                tasksToPrintList.add(tasksList.get(i));
            }
        }

        String response = "";

        if (tasksToPrintList.size() != 0) {
            response = "\t" + "Here are the tasks on this date ("
                    + displaySpecificDate(specificDate) + "):"
                    + System.lineSeparator()
                    + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                    + System.lineSeparator()
                    + System.lineSeparator();

            for (int i = 0; i < tasksToPrintList.size(); i++) {
                if (i != 0) {
                    response += System.lineSeparator();
                }

                response += "\t\t" + "-" + "\t" + tasksToPrintList.get(i).toString();
            }
        } else {
            response = "\t" + "There are no tasks on this date.";
        }

        return response;
    }

    static String displaySpecificDate(String specificDate) {
        LocalDate date = null;
        String displayDate = "";

        try {
            date = LocalDate.parse(specificDate);
            displayDate = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            displayDate = specificDate;
        }

        return displayDate;
    }
}
