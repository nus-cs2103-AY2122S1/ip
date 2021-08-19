import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.Optional;

public class Duke {

    // Comstant and Static Variable
    public static final String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String greeting = "Hello! I am \n"
            + logo
            + "The awesome bot helper! \n"
            + "How can I help you today?\n";

    public static final String goodbye = "Bye. Hope to see you again soon!\n";

    public static void main(String[] args) {
        
        reply(greeting);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object

        while(true) {
            String userInput = myObj.nextLine();
            String output;

            String regex = "([A-Za-z]+)\\s*([A-Za-z ]+)?\\s*(/by|/at|\\d+)?\\s*(.+)?";
            Matcher m = Pattern.compile(regex).matcher(userInput);
            m.find();

            try {
                String command = Optional.ofNullable(m.group(1)).orElseThrow(() ->
                        new DukeExceptions("Oops, you can't give me no command \n"));
                Optional<String> desc = Optional.ofNullable(m.group(2));
                Optional<String> modifier = Optional.ofNullable(m.group(3));
                Optional<String> time = Optional.ofNullable(m.group(4));

                Task task;
                if (command.equals("bye")) {
                    break;

                } else if (command.equals("list")) {
                    if (desc.isPresent() || modifier.isPresent() || time.isPresent()) {
                        throw new DukeExceptions("Oops, the list command cannot have any modifier or description \n");
                    }

                    StringBuilder result = new StringBuilder();
                    result.append("Here are the tasks in your list! \n");
                    int index = 1;
                    for (Task listedTask : taskList) {
                        result.append(String.valueOf(index) + ". " + listedTask.toString() + '\n');
                        index++;
                    }
                    output = result.toString();

                } else if (command.equals("delete")) {
                    if (desc.isPresent() || time.isPresent()) {
                        throw new DukeExceptions(
                                "Oops, the delete command can only have an integer (index) modifier \n"
                        );
                    }

                    int taskIndex = modifier.map(Integer::parseInt).orElseThrow(() -> new DukeExceptions(
                            "Oops, you forgot to tell me which item you want to mark as done! \n"));
                    task = taskList.get(taskIndex - 1);
                    output = "Alright! I deleted the following task as finished: \n"
                            + "    " + task.toString() + '\n';
                    taskList.remove(taskIndex - 1);

                } else if (command.equals("done")) {

                    if (desc.isPresent() || time.isPresent()) {
                        throw new DukeExceptions(
                                "Oops, the done command can only have an integer (index) modifier \n"
                        );
                    }

                    int taskIndex = modifier.map(Integer::parseInt).orElseThrow(() -> new DukeExceptions(
                            "Oops, you forgot to tell me which item you want to mark as done! \n"));
                    task = taskList.get(taskIndex - 1);
                    task.markFinished();
                    output = "Well done! I marked the following task as finished: \n"
                            + "    " + task.toString() + '\n';

                } else if (command.equals("event")) {
                    task = Event.create(desc, time);
                    taskList.add(task);
                    output = "Got it! I've added this task: \n"
                            + "  " + task.toString() + '\n'
                            + String.format("Now you have %d tasks in the list \n", taskList.size());

                } else if (command.equals("deadline")) {
                    task = Deadline.create(desc, time);
                    taskList.add(task);
                    output = "Got it! I've added this task: \n"
                            + "  " + task.toString() + '\n'
                            + String.format("Now you have %d tasks in the list \n", taskList.size());

                } else if (command.equals("todo")) {
                    task = ToDo.create(desc);
                    taskList.add(task);
                    output = "Got it! I've added this task: \n"
                            + "  " + task.toString() + '\n'
                            + String.format("Now you have %d tasks in the list \n", taskList.size());

                } else {
                    output = "Oops.. I don't understand your command.. \n";
                }
                reply(output);
            } catch (DukeExceptions e) {
                reply(e.getMessage());
            }

        }
        reply(goodbye);
    }

    public static void reply(String output) {
        System.out.print(HORIZONTAL_LINE + output + HORIZONTAL_LINE);
    }
}
