import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * echoes commands entered by the user,
 * and exits when the user types `bye`.
 */
public class Duke {
    public static void main(String[] args) {
        // Greet
        DukeGreetingMessage greetingMessage = new DukeGreetingMessage("Hello! I'm Duke, what shall we do today?");
        System.out.println(greetingMessage.getFormattedMessage());

        // Add, List or Mark As Done
        String inputExitMessage = "bye";
        String inputListMessage = "list";
        String inputDoneMessage = "done";

        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();

        while (!inputMessage.equals(inputExitMessage)) {
            DukeOutputMessage outputMessage;

            if (inputMessage.equals(inputListMessage)) {
                // Output the list
                outputMessage = new DukeListMessage(DukeList.getList());
            } else if (inputMessage.contains(inputDoneMessage)) {
                try {
                    // Get the task number that should be marked as done
                    // Assume that the first 5 characters will be done and space
                    int taskNumber = Integer.parseInt(inputMessage.substring(5));

                    if (!DukeList.contains(taskNumber)) {
                        // Inform user if task number does not exist in list
                        outputMessage = new DukeOutputMessage(
                            String.format("Task %d does not exist in the list", taskNumber)
                        );
                    } else {
                        // Else mark task as done
                        DukeTask task = DukeList.getTaskByTaskNumber(taskNumber);
                        task.markAsDone();
                        outputMessage = new DukeDoneMessage(task.toString());
                    }
                } catch (NumberFormatException e) {
                    outputMessage = new DukeOutputMessage(
                        "Please enter a valid task number when marking a task as done " +
                        "in the form of 'done X', where X is the task number to be marked as done"
                    );
                }
            } else {
                try {
                    // Add input to list
                    DukeTask task = DukeTask.createTask(inputMessage);
                    DukeList.addItemToList(task);
                    outputMessage = new DukeAddedMessage(task.toString());
                } catch (InvalidTaskTypeException e) {
                    outputMessage = new DukeOutputMessage(e.getMessage());
                }
            }

            // Print output message
            System.out.println(outputMessage.getFormattedMessage());
            inputMessage = inputScanner.nextLine();
        }

        // Exit
        DukeExitMessage exitMessage = new DukeExitMessage("Bye, see you again");
        System.out.println(exitMessage.getFormattedMessage());
    }
}
