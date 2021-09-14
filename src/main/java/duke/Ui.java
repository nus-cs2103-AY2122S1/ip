package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.notes.Note;

/**
 * Prints out the relevant messages and responds to user's inputs on the user interface
 */
public class Ui {
    private String command;

    /**
     * Prints out the error that has occurred.
     *
     * @param error Error that has occurred.
     * @return String message when error has occurred.
     */
    public String showError(String error) {
        System.out.println(error);
        return error;
    }

    /**
     * Prints out the welcome message and asks the user to input his/her command.
     *
     * @return Welcome message to guide users what to do.
     */
    public String showWelcome() {
        String instructions = "Here's a manual: \n1. To add a task play: todo play " +
                "\n2. To add a deadline: deadline return book /by 02/12/2021 18:00, " +
                "\n3. To add an event: event meet friend /at 02/12/2021 18:00 - 19:00" +
                "\n4. To indicate task as done: done 2, where 2 refers to the task number" +
                "\n5. To delete task: delete 3, where 3 refers to the task number" +
                "\n6. To list tasks: list" + "\n7. to find a task using keyword: find play" +
                "\n8. To add a note: note my password is 123" + "\n9. To list the notes: List of Notes" +
                "\n10. To exit the application: Bye";
        String welcomeMessage =  "Hello! I'm Peppa \n" + instructions;
        System.out.println(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Prints out a horizontal dashed line on the user interface.
     *
     * @return Horizontal dotted line.
     */
    public String showLine() {
        String prettyLine = "\n______________________________________________________________\n";
        System.out.println(prettyLine);
        return prettyLine;
    }

    /**
     * Reads the command or inputs entered by the user.
     *
     * @return String message when command input by user is read.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        this.command = input;
        return this.command;
    }

    /**
     * Prints the list of tasks in the application when user asks for it.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @return String message when user wants to list all the tasks.
     */
    public String respondToList(ArrayList<Task> tasks) {
        String responseToList = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            responseToList = responseToList + "\n" + Integer.toString(i + 1) + "." + tasks.get(i).toString();
        }
        System.out.println(responseToList);
        return responseToList;
    }

    /**
     * Prints the tasks that has been completed by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param taskNumber Number of the task that has been completed in the list of tasks.
     * @return String message when user wants to indicate a task as done.
     */
    public String respondToDone(ArrayList<Task> tasks, Integer taskNumber) {
        String responseToDone = "\tNice! I've marked this task as done: \n \t \t" + " ["
                + tasks.get(taskNumber - 1).getStatusIcon() + "] " + tasks.get(taskNumber - 1).getDescription();
        System.out.println(responseToDone);
        return responseToDone;
    }

    /**
     * Prints the task that has been deleted by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param task task that has been completed in the list of tasks.
     * @return String message when user wants to delete a task.
     */
    public String respondToDelete(ArrayList<Task> tasks, Task task) {
        String responseToDelete = "\tNoted. I've removed this task: \n\t\t" + task.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToDelete);
        return responseToDelete;
    }

    /**
     * Prints the Todo task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param todo The Todo task that has been created.
     * @return String message when todo task is created.
     */
    public String respondToTodo(ArrayList<Task> tasks, ToDo todo) {
        String responseToToDo = "\t" + "Got it. I've added this task: " + "\n\t\t" + todo.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToToDo);
        return responseToToDo;
    }

    /**
     * Prints the Event task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param event The Event task that has been created.
     * @return String message when event task is created.
     */
    public String respondToEvent(ArrayList<Task> tasks, Event event) {
        String responseToEvent = "\tGot it. I've added this task: " + "\n\t\t" + event.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(responseToEvent);
        return responseToEvent;
    }

    /**
     * Prints the Deadline task that has been created by the user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param deadline The Deadline task that has been created.
     * @return String message when deadline task is created.
     */
    public String respondToDeadline(ArrayList<Task> tasks, Deadline deadline) {
        String respondToDeadline = "\t" + "Got it. I've added this task: " + "\n\t\t" + deadline.toString() +
                "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.";
        System.out.println(respondToDeadline);
        return respondToDeadline;
    }

    /**
     * Prints the response on the user interface to the blah input by user.
     *
     * @return String message when user types random command like blah.
     */
    public String respondToBlah() {
        String responseToBlah = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(responseToBlah);
        return responseToBlah;
    }

    /**
     * Prints the response on the user interface to the bye input by user.
     *
     * @return String message when user types bye and wants to exit application.
     */
    public String respondToBye() {
        String responseToBye = "\tBye. Hope to see you again soon!";
        System.out.println(responseToBye);
        return responseToBye;
    }

    /**
     * Prints the response on the user interface to the find input by user.
     *
     * @return String message when user keys in keyword to find tasks.
     */
    public String respondToFind(ArrayList<Task> tasks) {
        String responseToFind = "Here are the matching tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            responseToFind = responseToFind + "\n" + Integer.toString(i + 1) + "." + tasks.get(i).toString();
        }
        System.out.println(responseToFind);
        return responseToFind;
    }

    /**
     * @return String message in response to invalid commands.
     */
    public String respondToInvalidCommand() {
        String responseToFind = "\tInvalid command!";
        System.out.println(responseToFind);
        return responseToFind;
    }

    public String respondToNote(ArrayList<Note> notes, Note note) {
        String responseToNote = "\t" + "Got it. I've added this note: " + "\n\t\t" + note.toString() +
                "\n\tNow you have " + Integer.toString(notes.size()) + " notes in the list.";
        System.out.println(responseToNote);
        return responseToNote;
    }

    public String respondToNotesList(ArrayList<Note> notes) {
        String responseToNotesList = "Here are the notes in your list:";
        for (int i = 0; i < notes.size(); i++) {
            responseToNotesList = responseToNotesList + "\n" + Integer.toString(i + 1) + "." + notes.get(i).toString();
        }
        System.out.println(responseToNotesList);
        return responseToNotesList;
    }
}
