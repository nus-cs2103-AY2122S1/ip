import java.util.Scanner;

/**
 * Encapsulates a chatbot that greets the user,
 * adds valid inputs to a task list,
 * updates the status of tasks in the list,
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

        DukeTaskList list = new DukeTaskList();
        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();

        while (!inputMessage.equals(inputExitMessage)) {
            DukeOutputMessage outputMessage;

            if (inputMessage.equals(inputListMessage)) {
                // Output the list
                outputMessage = new DukeListMessage(list.getList());
            } else if (inputMessage.length() >=4 && inputMessage.substring(0, 4).equals(inputDoneMessage)) {
                try {
                    // Get the task number that should be marked as done
                    // The task number should be after 'done' and space
                    int taskNumber = Integer.parseInt(inputMessage.substring(5));

                    DukeTask task = list.getTaskByTaskNumber(taskNumber);
                    task.markAsDone();
                    outputMessage = new DukeDoneMessage(task.toString());
                } catch (NumberFormatException e) {
                    outputMessage = new DukeOutputMessage(
                        "Please enter a valid task number when marking a task as done " +
                        "in the form of 'done X', where X is the task number to be marked as done"
                    );
                } catch (NonExistentTaskNumberException e) {
                    outputMessage = new DukeOutputMessage(e.getMessage());
                }
            } else {
                try {
                    // Add input to list
                    DukeTask task = DukeTask.createTask(inputMessage);
                    list.addTaskToList(task);
                    outputMessage = new DukeAddedMessage(task.toString(), list.getNumberOfTasks());
                } catch (
                        InvalidTaskTypeException |
                        InvalidTaskTimeFormatException |
                        MissingTaskDescriptionException |
                        FullTaskListException e
                ) {
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
