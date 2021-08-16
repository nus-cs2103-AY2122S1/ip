/**
 * Encapsulates the ChatBot function and supports echoing of commands.
 *
 * @author Clifford
 */

import java.util.regex.*;

public class ChatBot {
    private boolean isRunning;
    private Task[] tasks;
    private static final int tasksLimit = 100;
    private int currentIdx;

    public ChatBot() {
        this.isRunning = true;
        this.tasks = new Task[tasksLimit];
        this.currentIdx = 0;
    }

    public boolean isRunning() {
        return isRunning;
    }

    /**
     * greet is called when the user starts up the program.
     *
     * @return a welcome message when user starts interacting with ChatBot
     */
    public String greet() {
        return "Hello! I'm Chatty Clifford!\nHow may I be of service to you?";
    }

    /**
     * farewell is called when the user exits the program.
     *
     * @return a farewell message when user finishes interacting with ChatBot
     */
    public String farewell() {
        this.isRunning = false;
        return "Bye! See you next time!";
    }

    /**
     * listen decides what the ChatBot should do depending on the user input
     *
     * @param input the request by the user
     * @return the response by the ChatBot
     */
    public String listen(String input) {
        if(input.trim().equals("bye")) {
            return farewell();
        }
        if(input.trim().equals("list")) {
            return printList();
        }
        String[] temp = input.trim().split(" ", 2);
        if(temp[0].equals("done")) {
            try {
                int taskId  = Integer.parseInt(temp[1]);
                return markAsDone(taskId);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "done should be in format: done [DESCRIPTION]";
            }
        }
        if(temp[0].equals("todo")) {
            try {
                return recordTodo(temp[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "todo should be in format: todo [DESCRIPTION]";
            }
        }
        if(temp[0].equals("deadline")) {
            try {
                String[] descriptionDatePair = temp[1].split("/by", 2);
                return recordDeadline(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "deadline should be in format: [DESCRIPTION] /by [DATE]!";
            }
        }
        if(temp[0].equals("event")) {
            try {
                String[] descriptionDatePair = temp[1].split("/at", 2);
                return recordEvent(descriptionDatePair[0].trim(), descriptionDatePair[1].trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                return "event should be in format: [DESCRIPTION] /at [DATE]!";
            }
        }
        return "Sorry, I don't understand what you are saying!";
    }

    public String recordTodo(String description) {
        return record(new Todo(description));
    }

    public String recordDeadline(String description, String date) {
        return record(new Deadline(description, date));
    }

    public String recordEvent(String description, String date) {
        return record(new Event(description, date));
    }

    /**
     * Record a task to a list and informs the user if the operation is successful.
     *
     * @param task the task to be added to list
     * @return a String to tell the user that the item is recorded
     * @throws ArrayIndexOutOfBoundsException is thrown if user exceeds the limit of items.
     */
    public String record(Task task) throws ArrayIndexOutOfBoundsException {
        try {
            if(currentIdx >= tasksLimit) {
                throw new ArrayIndexOutOfBoundsException(
                    String.format("ChatBot can only record maximum of %d responses.", tasksLimit));
            }
            tasks[currentIdx] = task;
            currentIdx++;
            return "Noted task down! I've added this task:\n  " + task.toString();
        } catch(ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return "I cannot remember so many things!";
        }
    }

    /**
     * formats the items recorded in a list to be shown to user.
     *
     * @return list representation of items recorded by user
     */
    public String printList() {
        if(currentIdx == 0) {
            return "--- List is Empty ---";
        }
        StringBuilder sb = new StringBuilder("--- Start of List ---\n");
        for(int i = 0; i < currentIdx; i++) {
            sb = sb.append(Integer.toString(i + 1)).append(". ").append(tasks[i].toString()).append("\n");
        }
        sb = sb.append("--- End Of List ---");
        return sb.toString();
    }

    /**
     *
     * @param taskId the id of the task starting from 1 for the first task
     * @return a String that confirms the success or failure of the operation mark as done operation.
     * @throws IllegalArgumentException is throw when a task that does not exist is markAsDone
     */
    public String markAsDone(int taskId) throws IllegalArgumentException {
        try {
            if(taskId <= 0 || taskId > currentIdx) {
                throw new IllegalArgumentException(
                    String.format("taskId of %1$d invalid as there are %2$d recorded task(s)", taskId, currentIdx));
            }
            return tasks[taskId - 1].markAsDone();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return "No such task exists!";
        }
    }
}
