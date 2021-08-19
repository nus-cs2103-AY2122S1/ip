package main.java;


import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class DukeEngine {
    private final String borderLine = "\n____________________________________________________________\n";
    private final DukeMessages messages = new DukeMessages();

    private List<Task> taskList = new ArrayList<Task>();

    //probably not needed for now but might be needed in the future
    private enum taskCommands {list, todo, deadline, event, delete, done, bye};

    /**
     * Main engine to run the program.
     */
    public void runProgram() {

        //initialises the scanner
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {

            //removes additional space from the input
            input = sc.nextLine().trim();

            //removes space in the command input and stores the strings in an array
            String[] inputArr = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                messages.displayListItems(this.taskList);
            } else if (inputArr[0].equals("done")) {
                
                //obtains the task number which we want to mark as done
                Integer index = Integer.parseInt(inputArr[1]);

                try {
                    this.markAsDone(index);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
                
            } else if (inputArr[0].equals("delete")) {

                //obtains the task number which we want to delete
                Integer index = Integer.parseInt(inputArr[1]);

                try {
                    this.deleteTask(index);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
            }
            else {

                //adds item input by the user into the inputList
                try {
                    this.addTask(inputArr);
                } catch (DukeException e) {
                    messages.displayText(e.toString());
                }
            }
        }
        //terminates scanner
        sc.close();
    }


    /**
     * adds a Task object into the taskList
     * @param inputArr String array containing input by the user
     */
    public void addTask(String[] inputArr) throws InvalidCommandException {
        try {
            if (inputArr[0].equals("todo")) {
                this.addTodo(inputArr);

            } else if (inputArr[0].equals("deadline")) {
                this.addDeadline(inputArr);

            } else if (inputArr[0].equals("event")) {
                this.addEvent(inputArr);

            } else {
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            messages.displayText(e.toString());
        }

    }

    /**
     * Adds a TooDo Task into the List containing Tasks
     * @param inputArr String array containing input by the user
     * @throws DescriptionException exception thrown when there are no descriptions provided
     */
    public void addTodo(String[] inputArr) throws DescriptionException {
        if (inputArr.length < 2) {
            throw new DescriptionException("todo");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, inputArr.length);

        String description = String.join(" ", descriptionArray);

        Todo todoTask = new Todo(description);
        this.taskList.add(todoTask);
        messages.taskAddMessage(todoTask.toString(), this.taskList.size());
    }

    /**
     * Adds a Deadline Task into the List containing Tasks
     * @param inputArr String array containing input by the user
     * @throws DescriptionException exception thrown when there are no descriptions provided
     * @throws CommandException exception thrown when the specific command(s) for Deadline are not present
     */
    public void addDeadline(String[] inputArr) throws DescriptionException, CommandException {
        if (inputArr.length < 2) {
            throw new DescriptionException("deadline");
        }

        boolean commandAbsent = true;
        int commandIndex = 1;
        for (int i = 0; i < inputArr.length; i++) {
            String currentStr = inputArr[i];
            if (currentStr.equals("/by")) {
                commandAbsent = false;
                commandIndex = i;
                break;
            }
        }
        if (commandAbsent) {
            throw new CommandException("deadline", "/by");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, commandIndex);
        String description = String.join(" ", descriptionArray);

        String by;
        if (commandIndex + 1 <= inputArr.length - 1) {
            String[] byArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);
            by = String.join(" ", byArray);
        } else {
            //TODO: might need to throw error here because this case is /by and nothing behind?
            by = "No data was inputted";
        }

        Deadline deadlineTask = new Deadline(description, by);
        this.taskList.add(deadlineTask);
        messages.taskAddMessage(deadlineTask.toString(), this.taskList.size());
    }

    /**
     * Adds an Event Task into the List containing Tasks
     * @param inputArr String array containing input by the user
     * @throws DescriptionException exception thrown when there are no descriptions provided
     * @throws CommandException exception thrown when the command(s) for Event are not present
     */
    public void addEvent(String[] inputArr) throws DescriptionException, CommandException {
        if (inputArr.length < 2) {
            throw new DescriptionException("event");
        }

        boolean commandAbsent = true;
        int commandIndex = 1;
        for (int i = 0; i < inputArr.length; i++) {
            String currentStr = inputArr[i];
            if (currentStr.equals("/at")) {
                commandAbsent = false;
                commandIndex = i;
                break;
            }
        }
        if (commandAbsent) {
            throw new CommandException("event", "/at");
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, commandIndex);
        String description = String.join(" ", descriptionArray);

        String at;
        if (commandIndex + 1 <= inputArr.length - 1) {
            String[] atArray = Arrays.copyOfRange(inputArr, commandIndex + 1, inputArr.length);
            at = String.join(" ", atArray);
        } else {
            //TODO: might need to throw error here because this case is /by and nothing behind?
            at = "No data was inputted";
        }

        Event eventTask = new Event(description, at);
        this.taskList.add(eventTask);

        messages.taskAddMessage(eventTask.toString(), this.taskList.size());

    }

    /**
     * Marks a task in the taskList as done
     * @param index index of the Task in the taskList to be marked as Done, but need to - 1 due to index starting at 0
     */
    public void markAsDone(Integer index) throws TaskNumberException {
        if (index > this.taskList.size()) {
            throw new TaskNumberException();

        } else {
            //because our list starts from index 0 instead of index 1
            int realIndex = index - 1;
            this.taskList.get(realIndex).markAsDone();

            messages.markAsDoneMessage(this.taskList.get(realIndex).toString());
        }
    }

    /**
     * Deletes a task from the Tasks List
     * @param index the task to be deleted using 1 as the starting index
     * @throws TaskNumberException exception thrown when the task's index is not present in the list
     */
    public void deleteTask(Integer index) throws TaskNumberException {
        if (index > this.taskList.size() || index < 0) {
            throw new TaskNumberException();

        } else {
            //because our list starts from index 0 instead of index 1
            int realIndex = index - 1;
            String removedTask = this.taskList.get(realIndex).toString();
            this.taskList.remove(realIndex);
            messages.taskDeleteMessage(removedTask, this.taskList.size());
        }
    }
}
