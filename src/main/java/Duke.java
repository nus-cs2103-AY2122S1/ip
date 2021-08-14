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
        String inputDeleteMessage = "delete";

        DukeTaskList list = new DukeTaskList();
        Scanner inputScanner = new Scanner(System.in);
        String inputMessage = inputScanner.nextLine();

        while (!inputMessage.trim().equals(inputExitMessage)) {
            DukeOutputMessage outputMessage;

            if (inputMessage.equals(inputListMessage)) {
                // Output the list
                outputMessage = new DukeListMessage(list.getList());
            } else if (inputMessage.length() >= 4 && inputMessage.substring(0, 4).equals(inputDoneMessage)) {
                try {
                    // Get the task number that should be marked as done
                    // The task number should be after 'done' and space
                    int taskNumber = getTaskNumberFromInputMessage(inputMessage.substring(4).trim());

                    DukeTask task = list.getTaskByTaskNumber(taskNumber);
                    task.markAsDone();
                    outputMessage = new DukeDoneMessage(task.toString());
                } catch (NonExistentTaskNumberException | InvalidTaskNumberException e) {
                    outputMessage = new DukeOutputMessage(e.getMessage());
                }
            } else if (inputMessage.length() >= 6 && inputMessage.substring(0, 6).equals(inputDeleteMessage)) {
                try {
                    // Get the task number that should be deleted
                    // The task number should be after 'delete' and space
                    int taskNumber = getTaskNumberFromInputMessage(inputMessage.substring(6).trim());

                    DukeTask task = list.getTaskByTaskNumber(taskNumber);
                    list.deleteTaskFromList(taskNumber);
                    outputMessage = new DukeDeletedMessage(task.toString(), list.getNumberOfTasks());
                } catch (NonExistentTaskNumberException | InvalidTaskNumberException e) {
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
                        MissingTaskDescriptionException e
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

    public static int getTaskNumberFromInputMessage(String inputMessage) throws InvalidTaskNumberException {
        try {
            return Integer.parseInt(inputMessage);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(inputMessage);
        }
    }
}
