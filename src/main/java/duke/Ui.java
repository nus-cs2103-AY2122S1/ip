package duke;

import java.util.ArrayList;;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.notes.Note;

/**
 * Prints out the relevant messages and responds to user's inputs on the user interface.
 */
public class Ui {
    private String command;
    private String manual = "Here's a manual: \n1. To add a task play: todo play "
            + "\n2. To add a deadline: deadline return book /by DD/MM/YYYY HH:MM, "
            + "\n3. To add an event: event meet friend /at DD/MM/YYYY HH:MM-HH:MM"
            + "\n4. To indicate task as done: done 2, where 2 refers to the task number"
            + "\n5. To delete task: delete 3, where 3 refers to the task number"
            + "\n6. To list tasks: list" + "\n7. to find a task using keyword: find play"
            + "\n8. To add a note: note my password is 123" + "\n9. To list the notes: n/list"
            + "\n10. To delete a note: n/delete 2, where 2 refers to to note number"
            + "\n11. To exit the application: Bye";

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
        String welcomeMessage =  "Hello! I'm Peppa \n" + this.manual;
        return welcomeMessage;
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
            String index = Integer.toString(i + 1);
            Task currentTask = tasks.get(i);
            responseToList = responseToList + "\n" + index + "." + currentTask.toString();
        }
        return responseToList;
    }

    /**
     * Prints the tasks that has been completed by user.
     *
     * @param tasks Collection of tasks in the task manager application.
     * @param taskNumber Number of the task that has been completed in the list of tasks.
     * @return String message when user wants to indicate a task as done.
     */
    public String respondToDone(ArrayList<Task> tasks, Integer taskNumber) {
        Task currentTask = tasks.get(taskNumber - 1);
        String taskStatusIcon = currentTask.getStatusIcon();
        String taskDescription = currentTask.getDescription();
        String responseToDone = "\tNice! I've marked this task as done: \n \t \t" + " ["
                + taskStatusIcon + "] " + taskDescription;
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
        String numberOfTasks = Integer.toString(tasks.size());
        String taskString = ""; //This variable either stores the plural or singular form of task based on the number of notes.
        if(tasks.size() == 1) {
            taskString = " task";
        } else {
            taskString = " tasks";
        }
        String responseToDelete = "\tNoted. I've removed this task: \n\t\t" + task.toString() +
                "\n\tNow you have " + numberOfTasks + taskString + " in the list.";
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
        String numberOfTasks = Integer.toString(tasks.size());
        String taskString = ""; //This variable either stores the plural or singular form of task based on the number of notes.
        if(tasks.size() == 1) {
            taskString = " task";
        } else {
            taskString = " tasks";
        }
        String responseToToDo = "\t" + "Got it. I've added this task: " + "\n\t" + todo.toString() +
                "\n\tNow you have " + numberOfTasks + taskString + " in the list.";
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
        String numberOfTasks = Integer.toString(tasks.size());
        String taskString = ""; //This variable either stores the plural or singular form of task based on the number of notes.
        if(tasks.size() == 1) {
            taskString = " task";
        } else {
            taskString = " tasks";
        }
        String responseToEvent = "\tGot it. I've added this task: " + "\n\t" + event.toString() +
                "\n\tNow you have " + numberOfTasks + taskString + " in the list.";
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
        String numberOfTasks = Integer.toString(tasks.size());
        String taskString = ""; //This variable either stores the plural or singular form of task based on the number of notes.
        if(tasks.size() == 1) {
            taskString = " task";
        } else {
            taskString = " tasks";
        }
        String responseToDeadline = "\t" + "Got it. I've added this task: " + "\n\t" + deadline.toString() +
                "\n\tNow you have " + numberOfTasks + taskString + " in the list.";
        return responseToDeadline;
    }

    /**
     * Prints the response on the user interface to a random or invalid input by user.
     *
     * @return String message when user types random command like blah.
     */
    public String respondToBlah() {
        String responseToBlah = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
        return responseToBlah;
    }

    /**
     * Prints the response on the user interface to the bye input by user.
     *
     * @return String message when user types bye and wants to exit application.
     */
    public String respondToBye() {
        String responseToBye = "\tBye. Hope to see you again soon!";
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
            String index = Integer.toString(i + 1);
            Task currentTask = tasks.get(i);
            responseToFind = responseToFind + "\n" + index + "." + currentTask.toString();
        }
        return responseToFind;
    }

    /**
     * Prints the note that has been created by the user on the user interface.
     *
     * @param notes Current list of notes in the task manager.
     * @param note Note that has been created and added to the task manager.
     * @return String message indicating that the note has been added and the total number of notes in the task manager.
     */
    public String respondToNote(ArrayList<Note> notes, Note note) {
        String numberOfNotes = Integer.toString(notes.size());
        String noteString = ""; //This variable stores the plural or singular form of note based on the number of notes.
        if(notes.size() == 1) {
            noteString = " note";
        } else {
            noteString = " notes";
        }
        String responseToNote = "\t" + "Got it. I've added this note: " + "\n\t\t" + note.toString() +
                "\n\tNow you have " + numberOfNotes + noteString + " in the list.";
        return responseToNote;
    }

    /**
     * Prints the list of notes that are stored in the task manager on the user interface.
     *
     * @param notes Current list of notes in the task manager.
     * @return String of list of notes in the task manager.
     */
    public String respondToNotesList(ArrayList<Note> notes) {
        String responseToNotesList = "Here are the notes in your list:";
        for (int i = 0; i < notes.size(); i++) {
            String currentIndex = Integer.toString(i + 1);
            Note currentNote = notes.get(i);
            responseToNotesList = responseToNotesList + "\n" + currentIndex + "." + currentNote.toString();
        }
        return responseToNotesList;
    }

    /**
     * Prints a message on the user interface to notify users that the note has been deleted successfully.
     *
     * @param notes Current list of notes in the task manager.
     * @param note Note that has been created and added to the task manager.
     * @return String message indicating the note that has been deleted.
     */
    public String respondToDeleteNote(ArrayList<Note> notes, Note note) {
        String numberOfNotes = Integer.toString(notes.size());
        String noteString = ""; //This variable stores the plural or singular form of note based on the number of notes.
        if(notes.size() == 1) {
            noteString = " note";
        } else {
            noteString = " notes";
        }
        String responseToDeleteNote = "\tNoted. I've removed this note: \n\t\t" + note.toString() +
                "\n\tNow you have " + numberOfNotes + noteString + " in the list.";
        return responseToDeleteNote;
    }

    /**
     * Prints the manual on the user interface for users to refer to for the commands.
     *
     * @return String message of the manual of the task manager Peppa.
     */
    public String respondToHelp() {
        return this.manual;
    }
}
