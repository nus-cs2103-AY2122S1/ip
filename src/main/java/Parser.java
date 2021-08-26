import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    TaskList tasks;
    String userInput;
    int index;

    public Parser(TaskList tasks, String userInput, int index) {
        this.tasks = tasks;
        this.userInput = userInput;
        this.index = index;
    }

    public void bye_execute() {
        String byeMsg = "    ----------------------------\n"
                + "    okay :<, bye!" + "\n"
                + "    ----------------------------";
        System.out.println(byeMsg);
    }

    public void list_execute() {
        String message = "    ----------------------------\n"
                + "    " + "Here are the tasks in your list:\n";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            message += "    " + (i+1) + ". " + tasks.get(i).toString() + "\n";
            i++;
        }
        message += "    ----------------------------";
        System.out.println(message);
    }

    public void done_execute() {
        String userIndex = userInput.substring(5);
        int i = Integer.valueOf(userIndex);
        if (tasks.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            tasks.get(i-1).markAsDone();
            String message = "    ----------------------------\n"
                    +"Nice! I have marked this task as done:\n"
                    +"[X] " + tasks.get(i-1).getDescription() + "\n" + "    ----------------------------";
            System.out.println(message);
        }
    }

    public void todo_execute() {
        try {
            Task A = new ToDo(userInput.substring(5));
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"+
                    "Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a todo cannot be empty!");
        }
    }

    public void event_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(6,i-1);
            //String time = userInput.substring(i+1);
            String[] split = userInput.split("at");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task A = new Event(description, formattedTime);
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    +"Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of an event cannot be empty and must contain a time!");
        }
    }

    public void deadline_execute() {
        try {
            int i = userInput.indexOf("/");
            String description = userInput.substring(9,i-1);
            //String time = userInput.substring(i+1);
            String[] split = userInput.split("by");
            String date = split[1].substring(1);
            LocalDate d = LocalDate.parse(date);
            String formattedTime = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Task A = new Deadlines(description, formattedTime);
            tasks.addTask(A);
            Duke.index++;
            String message = "    ----------------------------\n"
                    +"Got it, I've added this task: \n"
                    + A.toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! The description of a deadline cannot be empty and must contain a time!");
        }
    }

    public void delete_execute() {
        String userIndex = userInput.substring(7);
        int i = Integer.valueOf(userIndex);
        if (tasks.get(i-1) == null) {
            System.out.println("no task found!");
        } else {
            Duke.index--;
            String message = "    ----------------------------\n"
                    +"Got it, I've removed this task: \n"
                    + tasks.get(i-1).toString() + "\n"
                    + "Now you have " + Duke.index + " tasks in the list\n"
                    +"    ----------------------------";
            System.out.println(message);
            tasks.removeTask(i-1);
        }
    }

    public String generateTasks() {
        String taskString = "";
        int i = 0;
        while (i < tasks.getNumberOfTasks()) {
            taskString += tasks.get(i).toString() + "\n";
            i++;
        }
        return taskString;
    }
}
