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

    static String numOfTasksInList(List<Task> tasksList) {
        return "\t" + "Now you have " + tasksList.size()
                + (tasksList.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }
}
