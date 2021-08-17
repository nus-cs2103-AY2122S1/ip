import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static boolean terminate = false;

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

            if(userInput.matches("bye")) {
                break;
            } else if (userInput.matches("list")) {
                StringBuilder result = new StringBuilder();
                int index = 1;
                for (Task task : taskList) {
                    result.append(String.valueOf(index) + ". " + task.toString() + '\n');
                    index++;
                }
                reply(result.toString());
            } else if (userInput.matches("done [\\d]+")) {
                Matcher m = Pattern.compile("[\\d]+").matcher(userInput);
                m.find();
                int taskIndex = Integer.parseInt(m.group(0)) - 1;
                Task task = taskList.get(taskIndex);
                task.markFinished();
                output = "Well done! I marked the following task as finished: \n"
                        + "    " + task.toString() + '\n';
                reply(output);
            } else {
                    taskList.add(new Task(userInput));
                    output = "Added: " + userInput + '\n';
                    reply(output);
            }
        }
        reply(goodbye);
    }

    public static void reply(String output) {
        System.out.print(HORIZONTAL_LINE + output + HORIZONTAL_LINE);
    }
}
