import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            List<String> inputList = Arrays.asList(userInput.split(" "));
            String startCommand = inputList.get(0);

            if (startCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (startCommand.equals("list")) {
                //Print the list here
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList);
            } else if (startCommand.equals("done")) {
                if (inputList.size() > 1 && Util.isInteger(inputList.get(1))) {
                    //Extract id of task
                    int index = Integer.parseInt(inputList.get(1)) - 1;
                    //Mark the task as done
                    taskList.markAsDone(index);
                    //Print out confirmation message
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.getTask(index));
                }
            } else if (startCommand.equals("todo")) {
                String taskDescription = String.join(" ",
                                        inputList.subList(1, inputList.size()));

                Todo todo = new Todo(taskDescription);
                taskList.addTask(todo);
                Util.taskAddConfirmatio(todo, taskList.getNumTask());

            } else if (startCommand.equals("deadline")) {
                //Check if there's /by keyword or not
                if (inputList.contains("/by")) {
                    int byIndex = inputList.indexOf("/by");
                    //Get deadline
                    String time = String.join(" ", inputList.subList(byIndex+1, inputList.size()));
                    //Get description
                    String description = String.join(" ", inputList.subList(1, byIndex));
                    if (!time.equals("") && !description.equals("")) {
                        Deadline deadline = new Deadline(description, time);
                        taskList.addTask(deadline);
                        Util.taskAddConfirmatio(deadline, taskList.getNumTask());
                    }
                }
            } else if (startCommand.equals("event")) {
                //Check if there's /by keyword or not
                if (inputList.contains("/at")) {
                    int byIndex = inputList.indexOf("/at");
                    //Get deadline
                    String time = String.join(" ", inputList.subList(byIndex+1, inputList.size()));
                    //Get description
                    String description = String.join(" ", inputList.subList(1, byIndex));
                    if (!time.equals("") && !description.equals("")) {
                        Event event = new Event(description, time);
                        taskList.addTask(event);
                        Util.taskAddConfirmatio(event, taskList.getNumTask());
                    }
                }
            } else {
                System.out.println("Command not found");
            }
        }
        sc.close();
    }
}