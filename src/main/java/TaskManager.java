import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

import java.util.Arrays;
import java.util.List;

public class TaskManager {
    protected TaskList taskList;

    public TaskManager() {
        this.taskList = new TaskList();
    }

    public String getUserCommand(String userInput) {
        String userCommand = Arrays.asList(userInput.split(" ")).get(0);
        return userCommand;
    }

    public void processUserInput(String userInput) {
        List<String> inputList = Arrays.asList(userInput.split(" "));
        String userCommand = this.getUserCommand(userInput);

        if (userCommand.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (userCommand.equals("list")) {
            //Print the list here
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        } else if (userCommand.equals("done")) {
            if (inputList.size() > 1 && Util.isInteger(inputList.get(1))) {
                //Extract id of task
                int index = Integer.parseInt(inputList.get(1)) - 1;
                //Mark the task as done
                taskList.markAsDone(index);
                //Print out confirmation message
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.getTask(index));
            }
        } else if (userCommand.equals("todo")) {
            String taskDescription = String.join(" ",
                    inputList.subList(1, inputList.size()));

            Todo todo = new Todo(taskDescription);
            taskList.addTask(todo);
            Util.taskAddConfirmatio(todo, taskList.getNumTask());

        } else if (userCommand.equals("deadline")) {
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
        } else if (userCommand.equals("event")) {
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
}
