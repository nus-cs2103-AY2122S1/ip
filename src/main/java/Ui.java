import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("ENTER COMMAND:");
        System.out.print("\t");
        return sc.nextLine().trim();
    }

    public void greet() {
        displayLine();
        displayWelcomeMessage();
        displayMenuOptions();
        displayLine();
    }

    public void exit() {
        String message = "\t" + "Bye. Hope to see you again soon!";
        displayResponse(message);
    }

    public void displayResponse(String message) {
        displayLine();
        System.out.println(message);
        displayLine();
    }

    public void displayError(String message) {
        displayLine();
        System.out.println("ERROR MESSAGE:");
        System.out.println("\t" + "☹ " + message);
        displayLine();
    }

    private void displayLine() {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
    }

    private void displayWelcomeMessage() {
        String message = "\t" + "Hello! I'm Duke, your Personal Assistant ChatBot."
                + System.lineSeparator()
                + "\t" + "What can I do for you?"
                + System.lineSeparator();
        System.out.println(message);
    }

    private void displayMenuOptions() {
        String format = "%-25s%s%n";

        String menuOption1 = "\t" + "list:";
        String menuOption1Info = "List the tasks in your list";

        String menuOption2 = "\t" + "todo ABC:";
        String menuOption2Info = "Add a todo [T] task, ABC, into your list";

        String menuOption3 = "\t" + "deadline ABC /by XYZ:";
        String menuOption3Info = "Add a deadline [D] task, ABC, into your list "
                + "and specify the date/time, XYZ, it needs to be completed by";

        String menuOption4 = "\t" + "event ABC /at XYZ:";
        String menuOption4Info = "Add an event [E] task, ABC, into your list "
                + "and specify the start and end date/time, XYZ";

        String menuOption5 = "\t" + "done N:";
        String menuOption5Info = "Mark a task number, N, as done";

        String menuOption6 = "\t" + "delete N:";
        String menuOption6Info = "Delete a task number, N, from your list";

        String menuOption7 = "\t" + "print /on yyyy-mm-dd:";
        String menuOption7Info = "Print deadlines/events on a specific date";

        String menuOption8 = "\t" + "bye:";
        String menuOption8Info = "Exit the ChatBot";

        System.out.printf(format, menuOption1, menuOption1Info);
        System.out.printf(format, menuOption2, menuOption2Info);
        System.out.printf(format, menuOption3, menuOption3Info);
        System.out.printf(format, menuOption4, menuOption4Info);
        System.out.printf(format, menuOption5, menuOption5Info);
        System.out.printf(format, menuOption6, menuOption6Info);
        System.out.printf(format, menuOption7, menuOption7Info);
        System.out.printf(format, menuOption8, menuOption8Info);
    }

    public String taskAddedMessage(Task task) {
        return "\t" + "Got it. I've added this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskDeletedMessage(Task task) {
        return "\t" + "Noted. I've removed this task:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this task as done:"
                + System.lineSeparator() + "\t\t" + task;
    }

    public String numOfTasksInList(TaskList tasks) {
        return "\t" + "Now you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks" : " task")
                + " in the list.";
    }

    public String tasksInYourList(TaskList tasks) {
        StringBuilder response = new StringBuilder("\t" + "Here are the tasks in your list:"
                + System.lineSeparator()
                + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                + System.lineSeparator()
                + System.lineSeparator());

        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                response.append(System.lineSeparator());
            }

            response.append("\t\t").append(i + 1).append(".")
                    .append("\t").append(tasks.get(i).toString());
        }

        return response.toString();
    }

    public String tasksOnDate(String dateStr, TaskList tasks) {
        TaskList tasksToPrint = new TaskList();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isOnDate(dateStr)) {
                tasksToPrint.add(tasks.get(i));
            }
        }

        StringBuilder response;

        if (tasksToPrint.size() != 0) {
            response = new StringBuilder("\t" + "Here are the tasks on this date ("
                    + processDateStr(dateStr) + "):"
                    + System.lineSeparator()
                    + "\t" + "[Legend: T = todo, D = deadline, E = event]"
                    + System.lineSeparator()
                    + System.lineSeparator());

            for (int i = 0; i < tasksToPrint.size(); i++) {
                if (i != 0) {
                    response.append(System.lineSeparator());
                }

                response.append("\t\t").append(i + 1).append(".")
                        .append("\t").append(tasksToPrint.get(i).toString());
            }
        } else {
            response = new StringBuilder("\t" + "There are no tasks on this date.");
        }

        return response.toString();
    }

    private String processDateStr(String dateStr) {
        LocalDate date;
        String processedDateStr;

        try {
            date = LocalDate.parse(dateStr);
            processedDateStr = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (Exception e) {
            processedDateStr = dateStr;
        }

        return processedDateStr;
    }
}
