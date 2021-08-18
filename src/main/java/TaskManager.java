import exceptions.EmptyDescriptionException;
import exceptions.EmptyTimeException;
import exceptions.InvalidCommandException;
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
        return Arrays.asList(userInput.split(" ")).get(0);
    }

    public void processUserInput(String userInput) throws EmptyDescriptionException, InvalidCommandException, EmptyTimeException {
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
        } else if (userCommand.equals("delete")) {
            if (inputList.size() > 1 && Util.isInteger(inputList.get(1))) {
                //Extract id of task
                int index = Integer.parseInt(inputList.get(1)) - 1;
                //Print out confirmation message
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.getTask(index));
                //Delete the task
                taskList.deleteTask(index);
                Util.informNumTask(taskList.getNumTask());
            }
        } else if (userCommand.equals("todo")) {
            String description = String.join(" ",
                    inputList.subList(1, inputList.size()));

            if (description.equals("")) {
                throw new EmptyDescriptionException("todo");
            } else {
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                Util.taskAddConfirmation(todo, taskList.getNumTask());
            }

        } else if (userCommand.equals("deadline")) {
            //Check if there's /by keyword or not
            if (inputList.contains("/by")) {
                int byIndex = inputList.indexOf("/by");
                //Get deadline
                String time = String.join(" ", inputList.subList(byIndex+1, inputList.size()));
                //Get description
                String description = String.join(" ", inputList.subList(1, byIndex));

                if (description.equals("")) {
                    throw new EmptyDescriptionException("deadline");
                } else if (time.equals("")) {
                    throw new EmptyTimeException("deadline");
                } else {
                    Deadline deadline = new Deadline(description, time);
                    taskList.addTask(deadline);
                    Util.taskAddConfirmation(deadline, taskList.getNumTask());
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

                if (description.equals("")) {
                    throw new EmptyDescriptionException("event");
                } else if (time.equals("")) {
                    throw new EmptyTimeException("event");
                } else {
                    Event event = new Event(description, time);
                    taskList.addTask(event);
                    Util.taskAddConfirmation(event, taskList.getNumTask());
                }
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
