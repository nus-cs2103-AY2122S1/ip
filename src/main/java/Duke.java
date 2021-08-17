import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        String greeting = "Hello! I am \n"
                    + logo
                    + "The awesome bot helper! \n"
                    + "How can I help you today?\n";

        String goodbye = "Bye. Hope to see you again soon!\n";

        reply(greeting);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            String userInput = myObj.nextLine();
            String output;

            String regex = "([A-Za-z]+)\\s?([A-Za-z ]*)?\\s?(/by|/at|\\d+)?\\s?(.*)?";
            Matcher m = Pattern.compile(regex).matcher(userInput);
            m.find();

            Task task;
            if (m.group(1).equals("bye")) {
                break;
            } else if (m.group(1).equals("list")) {
                StringBuilder result = new StringBuilder();
                result.append("Here are the tasks in your list! \n");
                int index = 1;
                for (Task listedTask : taskList) {
                    result.append(String.valueOf(index) + ". " + listedTask.toString() + '\n');
                    index++;
                }
                reply(result.toString());
            } else if (m.group(1).equals("done")) {
                int taskIndex = Integer.parseInt(m.group(3)) - 1;
                task = taskList.get(taskIndex);
                task.markFinished();
                output = "Well done! I marked the following task as finished: \n"
                        + "    " + task.toString() + '\n';
                reply(output);
            } else if (m.group(1).equals("event")){
                task = new Event(m.group(2), m.group(4));
                taskList.add(task);
                output = "Got it! I've added this task: \n"
                        + "  " + task.toString() + '\n'
                        + String.format("Now you have %d tasks in the list \n", taskList.size());
                reply(output);

            } else if (m.group(1).equals("deadline")){
                task = new Deadline(m.group(2), m.group(4));
                taskList.add(task);
                output = "Got it! I've added this task: \n"
                        + "  " + task.toString() + '\n'
                        + String.format("Now you have %d tasks in the list \n", taskList.size());
                reply(output);
            } else if (m.group(1).equals("todo")) {
                task = new ToDo(m.group(2));
                taskList.add(task);
                output = "Got it! I've added this task: \n"
                        + "  " + task.toString() + '\n'
                        + String.format("Now you have %d tasks in the list \n", taskList.size());
                reply(output);
            } else {
                reply("Oops.. I dont understand what you mean.. \n");
            }
        }
        reply(goodbye);
    }

    public static void reply(String output) {
        System.out.print(HORIZONTAL_LINE + output + HORIZONTAL_LINE);
    }
}
