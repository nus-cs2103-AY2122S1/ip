import main.java.Task;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Duke {
    private final String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
    private final String borderLine = "\n____________________________________________________________\n";
    private final String goodbyeMessage = "Bye. Hope to see you again soon!";

    private List<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        new Duke().runProgram();
    }

    public void runProgram() {

        //displays the welcome message
        this.displayText(welcomeMessage);

        //initialises the scanner
        Scanner sc = new Scanner(System.in);
        String input;

        while (true) {
            input = sc.nextLine().trim();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                this.displayListItems();
            } else if (input.contains("done")) {
                
                //obtains the task number which we want to mark as done. error is number not in list or input after done is not a number.
                String stringIndex = input.replace("done ", "");
                Integer index = Integer.parseInt(stringIndex);

                //need to subtract 1 since the list starts from index 0
                this.markAsDoneText(index - 1);
                
            } else {
                //echos the text input by the user
                this.displayText(input);

                //adds item input by the user into the inputList
                this.addItem(input);
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
     *
     */
    public void displayListItems() {
        System.out.println(borderLine);

        for (int i = 0; i < this.taskList.size(); i++) {
            Task currentTask = this.taskList.get(i);

            String inputMessage = String.format("%d.[%s] %s", i+1, currentTask.getStatusIcon(), currentTask.getDescription());
            System.out.println(inputMessage);
        }

        System.out.println(borderLine);
    }

    /**
     *
     * @param input String message input by the user to be kept in the inputList
     */
    public void addItem(String input) {
        this.taskList.add(new Task(input));
    }

    public void markAsDoneText(Integer index) {
        this.taskList.get(index).markAsDone();

        String message = String.format("Nice! I've marked this task as done:\n  [X] %s", this.taskList.get(index).getDescription());
        System.out.println(borderLine + message + borderLine);
    }
}
