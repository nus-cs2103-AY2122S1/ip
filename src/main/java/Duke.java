import main.java.Event;
import main.java.Task;
import main.java.Deadline;
import main.java.Todo;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    private List<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        new Duke().runProgram();
    }

    /**
     * Main engine to run the program.
     */
    public void runProgram() {

        //displays the welcome message
        this.displayText(welcomeMessage);

        //initialises the scanner
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine().trim();
            String[] inputArr = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.displayListItems();
            } else if (inputArr[0].equals("done")) {
                
                //obtains the task number which we want to mark as done
                //TODO: error is number not in list or input after done is not a number.
                String stringIndex = input.replace("done ", "");
                Integer index = Integer.parseInt(stringIndex);

                //need to subtract 1 since the list starts from index 0
                this.markAsDoneText(index - 1);
                
            } else {
                //echos the text input by the user
                //this.displayText(input);

                //adds item input by the user into the inputList
                this.addTask(inputArr);
            }
        }

        //terminates scanner
        sc.close();

        //displays goodbye message
        this.displayText(goodbyeMessage);
    }

    /**
     * Displays the message input given by the user with horizontal borderlines on both the top and bottom.
     * @param input String message to be displayed to user
     */
    public void displayText(String input) {
        System.out.println(borderLine + input + borderLine);
    }

    /**
     * Displays all the items in the taskList and their completion status.
     */
    public void displayListItems() {
        System.out.println(borderLine);

        for (int i = 0; i < this.taskList.size(); i++) {

            //displays the current task's status
            String inputMessage = String.format("%d. %s", i+1, this.taskList.get(i).toString());
            System.out.println(inputMessage);
        }

        System.out.println(borderLine);
    }

    /**
     * adds a Task object into the taskList
     * @param input String message input by the user will be used as a constructor for a new Task object to be put
     *              into the taskList
     */
    public void addTask(String[] inputArr) {
        if (inputArr[0].equals("todo")) {
            this.addTodo(inputArr);

        } else if (inputArr[0].equals("deadline")) {
            this.addDeadline(inputArr);

        } else if (inputArr[0].equals("event")) {
            this.addEvent(inputArr);

        } else {
            //this should not happen
        }

    }

    public void addTodo(String[] inputArr) {
        if (inputArr.length < 2) {
            //TODO: Throw an error here for commands without any content
            return;
        }

        String[] descriptionArray = Arrays.copyOfRange(inputArr, 1, inputArr.length);

        String description = String.join(" ", descriptionArray);

        Todo todoTask = new Todo(description);
        this.taskList.add(todoTask);
        this.taskCommonMessage(todoTask.toString());
    }

    public void addDeadline(String[] inputArr) {
        if (inputArr.length < 2) {
            //TODO: Throw an error here for commands without any content
            return;
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
            //TODO: throw some error here due to lack of command
            this.displayText("☹ OOPS!!! You didn't use the correct /command for deadline!!!!");
            return;
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
        this.taskCommonMessage(deadlineTask.toString());
    }

    public void addEvent(String[] inputArr) {
        if (inputArr.length < 2) {
            //TODO: Throw an error here for commands without any content
            return;
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
            //TODO: throw some error here due to lack of command
            this.displayText("☹ OOPS!!! You didn't use the correct /command for event!!!!");
            return;
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
        this.taskCommonMessage(eventTask.toString());

    }

    public void taskCommonMessage(String taskString) {
         String message = "Got it. I've added this task:\n" + taskString + "\n";
         message += String.format("Now you have %d tasks in the list.", this.taskList.size());
         this.displayText(message);
    }

    /**
     * Marks a task in the taskList as done
     * @param index index of the Task in the taskList to be marked as Done
     */
    public void markAsDoneText(Integer index) {
        this.taskList.get(index).markAsDone();

        String message = String.format("Nice! I've marked this task as done:\n  %s", this.taskList.get(index).toString());
        System.out.println(borderLine + message + borderLine);
    }
}
