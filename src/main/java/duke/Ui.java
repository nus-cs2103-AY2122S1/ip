package duke;

import duke.exceptions.DukeEmptyTodoDescriptionException;
import duke.exceptions.DukeException;
import duke.exceptions.DukeTodoTimeException;
import duke.exceptions.DukeUnknownCommandException;

/**
 * duke.Ui represents the Implementation of the interface of Duke.
 * Ui handles the displaying of messages and executing commands for
 * various tasks
 */
import java.util.ArrayList;

public class Ui {

    private final String LINES = "-------------------------------------------";

    void printLines() {
        System.out.println(LINES);
    }

    /**
     * Displays the welcome message to the user.
     */
    void displayWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLines();
    }

    /**
     * Displays the farewell message to user when they enter bye.
     */
    void displayByeMessage() {
        printLines();
        System.out.println("Bye. I have saved your tasks. Hope to see you again soon!");
        printLines();
    }


    /**
     * Returns the farewell message to user when they enter bye.
     */
    String showByeMessage() {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Bye. I have saved your tasks. Hope to see you again soon!\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Returns the task at the specified index
     *
     * @param description description entered by the user which has the index
     * @return task at the given index
     */
    Task getTaskFromIndex(String description) {
        int taskIndex = Integer.parseInt(description.substring(1)) - 1;
        Task taskAtIndex = Tasklist.dukeList.get(taskIndex);
        return taskAtIndex;
    }

    /**
     * Returns the new Deadline task created.
     *
     * @param description the description for the deadline task
     */
    Deadline addDeadline(String description) {
        String[] description_and_time = Parser.splitDescriptionAndTime(description);
        String deadlineDescription = Parser.getDescription(description_and_time);
        String time = Parser.getTime(description_and_time);
        Deadline newDeadline = new Deadline(deadlineDescription, time);
        return newDeadline;
    }

    /**
     * Returns the new Event task created.
     *
     * @param description the description for the Event task
     */
    Event addEvent(String description) {
        String[] event_and_time = Parser.splitDescriptionAndTime(description);
        String eventDescription = Parser.getDescription(event_and_time);
        String time = Parser.getTime(event_and_time);
        Event newEvent = new Event(eventDescription, time);
        return newEvent;
    }

    /**
     * Returns the appropriate message upon adding a task.
     *
     * @param addedTask the task which is added to the list
     */
    String displayAddMessage(Task addedTask) {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Got it. I've added this task:\n");
        str.append(addedTask + "\n");
        return str.toString();
    }

    /**
     * Returns the appropriate message upon deleting a task.
     *
     * @param deletedTask the task which is deleted from the list
     */
    String displayDeleteMessage(Task deletedTask) {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Noted. I've removed this task:\n");
        str.append(deletedTask + "\n");
        return str.toString();
    }

    /**
     * Returns the appropriate message upon updating a task.
     *
     * @param updatedTask the task which is updated from the list
     */
    String displayUpdateMessage(Task updatedTask) {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Noted. I've updated this task:\n");
        str.append(updatedTask + "\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Returns the appropriate response as per the command given.
     *
     * @param command     the command entered
     * @param description the description of the task
     * @return response from duke
     */
    String executeCommand(String command, String description) throws DukeException {
        try {
            switch(command) {
            case "list":
                return showDukeList();
            case "done":
                return showTaskCompletion1(description);
            case "deadline":
                return showDeadlineAddition(description);
            case "event":
                return showEventAddition(description);
            case "todo":
                return showTodoAddition(description);
            case "delete":
                return showTaskDeletion(description);
            case "find":
                return showFindResult(description);
            case "update":
                return showUpdateResult(description);
            default:
                throw new DukeUnknownCommandException();

            }
        } catch (DukeEmptyTodoDescriptionException | DukeUnknownCommandException | DukeTodoTimeException e) {
            return showErrorMessage(e.getMessage());
        }
    }

    /**
     * Shows the error message during execution
     *
     * @param message the error message to be shown
     * @return
     */

    String showErrorMessage(String message) {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append(message + "\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the completion message for the Task marked done.
     *
     * @param index The index of the task to be completed
     */
    String showTaskCompletion1(String index) {
        Task toBeCompleted = getTaskFromIndex(index);
        toBeCompleted.completeTask();
        assert toBeCompleted != null : "Task to be completed should not be null";
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Nice! I've marked this task as done:\n");
        str.append(toBeCompleted + "\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the addition message for the deadline created.
     *
     * @param description the description of the deadline
     * @return
     */

    String showDeadlineAddition(String description) {
        StringBuilder str = new StringBuilder();
        Deadline newDeadline = addDeadline(description);
        Tasklist.dukeList.add(newDeadline);
        str.append(displayAddMessage(newDeadline));
        str.append("Now you have " + Tasklist.dukeList.size() + " tasks in the list.\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the addition message for the event created.
     *
     * @param description the description of the event
     * @return
     */

    String showEventAddition(String description) {
        StringBuilder str = new StringBuilder();
        Event newEvent = addEvent(description);
        Tasklist.dukeList.add(newEvent);
        str.append(displayAddMessage(newEvent));
        str.append("Now you have " + Tasklist.dukeList.size() + " tasks in the list.\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the addition message for the todo created.
     *
     * @param description the description of the todo
     * @return
     */

    String showTodoAddition(String description) throws DukeEmptyTodoDescriptionException {
        if (description.trim().equals("")) {
            throw new DukeEmptyTodoDescriptionException();
        }
        StringBuilder str = new StringBuilder();
        ToDo newTodo = new ToDo(description.substring(1));
        Tasklist.dukeList.add(newTodo);
        str.append(displayAddMessage(newTodo));
        str.append("Now you have " + Tasklist.dukeList.size() + " tasks in the list.\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the deletion message for the task.
     *
     * @param index the index of the task to be deleted
     * @return
     */
    String showTaskDeletion(String index) {
        StringBuilder str = new StringBuilder();
        int taskIndex = Integer.parseInt(index.substring(1)) - 1;
        assert taskIndex < Tasklist.dukeList.size() : "Index cannot be greater than the size of list";
        Task taskToBeDeleted = Tasklist.dukeList.get(taskIndex);
        deleteTask(taskIndex);
        str.append(displayDeleteMessage(taskToBeDeleted));
        str.append("Now you have " + Tasklist.dukeList.size() + " tasks in the list.\n");
        str.append(LINES + "\n");
        return str.toString();
    }

    /**
     * Shows the result af the find command
     *
     * @param input the input by the user for keyword
     * @return
     */

    String showFindResult(String input) {
        String keyword = input.substring(1);
        ArrayList<Task> foundTasks = Tasklist.find(keyword);
        return showFoundDukeList(foundTasks);
    }

    /**
     * Shows the result af the update command
     *
     * @param input the input by the user for keyword
     * @return
     */
    String showUpdateResult(String input) throws DukeTodoTimeException {
        StringBuilder str = new StringBuilder();
        boolean hasNewDescription = input.contains("d/");
        boolean hasNewTime = input.contains("t/");
        int taskIndex = Integer.parseInt(input.substring(1, 2)) - 1;

        if (hasNewDescription) {
            String newDescription = Parser.getNewUpdatedDescription(input);
            Tasklist.updateDescription(taskIndex, newDescription);
        }
        if (hasNewTime) {
            String newTime = Parser.getNewUpdatedTime(input);
            Tasklist.updateTime(taskIndex, newTime);
        }
        Task updatedTask = Tasklist.dukeList.get(taskIndex);
        str.append(displayUpdateMessage(updatedTask));
        return str.toString();
    }


    /**
     * Displays the tasks in the DukeList.
     */

    String showDukeList() {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        for (int i = 0; i < Tasklist.dukeList.size(); i++) {
            str.append((i + 1) + ". " + Tasklist.dukeList.get(i) + "\n");
        }
        str.append(LINES + "\n");

        return str.toString();
    }

    /**
     * Removes a Task from the list.
     *
     * @param taskIndex the index of the Task to be removed
     */
    void deleteTask(int taskIndex) {
        Tasklist.delete(taskIndex);
    }


    /**
     * Displays all the found tasks with appropriate message.
     *
     * @param foundTasks all the found tasks having the keyword
     * @return found tasks
     */
    String showFoundDukeList(ArrayList<Task> foundTasks) {
        StringBuilder str = new StringBuilder();
        str.append(LINES + "\n");
        str.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); i++) {
            str.append((i + 1) + ". " + foundTasks.get(i) + "\n");
        }
        str.append(LINES + "\n");

        return str.toString();
    }

}
