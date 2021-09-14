package jarvis;

import java.time.LocalDateTime;

/**
 * Deals with interactions with the user
 */
public class Ui {
    public static final String NO_TASKS = "There are currently no tasks on your list :)";
    public static final String NO_NOTES = "There are currently no notes on your list!";

    public static final String BYE = "Bye! Hope to see you soon :)";

    // Error messages by Jarvis for todo task related errors
    public static final String EMPTY_TODO_DESCRIPTION = "Oops! The description of a todo cannot be empty.";

    // Error messages by Jarvis for deadline task related errors
    public static final String EMPTY_DEADLINE_DESCRIPTION = "Oops! The description of a deadline cannot be empty.";
    public static final String INCOMPLETE_DEADLINE = "I think you forgot to key in your deadline! Please key it" +
            " in as dd/mm/yyyy hh:mm (in 24 hours format)";
    public static final String WRONGLY_FORMATTED_DEADLINE_TIME = "Please include the time in the 24 hour format " +
            "(e.g. 15:00)";

    // Error messages by Jarvis for event task related errors
    public static final String EMPTY_EVENT_DESCRIPTION = "Oops! The description of an event cannot be empty.";
    public static final String INCOMPLETE_EVENT_TIMINGS = "I think you forgot to key in your event timings!";
    public static final String WRONGLY_FORMATTED_EVENT_TIMINGS = "Please include the start and end times in the 24 " +
            "hour format (e.g. 15:00-16:00)";

    // Error messages by Jarvis for other errors
    public static final String UNRECOGNISED_COMMAND = "I'm sorry, but I don't know what that means :(";
    public static final String WRONGLY_FORMATTED_DATE = "Please format the date as dd/mm/yyy";
    public static String EMPTY_TASK_DELETE = "Please state the number of the task that you wish to delete! Please key " +
            "in 'list' if you'd like to view your list of tasks again!";
    public static String INVALID_INDEX = "";

    // Error messages by Jarvis for notes related errors
    public static final String WRONGLY_FORMATTED_NOTE = "Oops, the note is formatted incorrectly! Please write it as " +
            "title /body";
    public static String EMPTY_NOTE_DELETE = "Please state the number of the note that you wish to delete! Please key " +
            "in 'notes' if you'd like to view your list of tasks again!";

    /**
     * Displays Jarvis' first greeting when user opens the application.
     *
     * @return Jarvis' greeting to the user
     */
    public static String firstGreeting() {
        return "Hi! I am Jarvis, your personal assistant :) What can I do for you?";
    }

    /**
     * Displays the error message when the task number keyed in by the user is invalid.
     *
     * @param userInput The task number keyed in by the user
     * @return The error message
     */
    public static String invalidTaskNum(int userInput) {
        Ui.INVALID_INDEX = "Hmm, I don't have task " + (userInput + 1) + " in my list. " +
                "Please key in 'list' if you'd like to view your list of tasks again!";
        return Ui.INVALID_INDEX;
    }

    /**
     * Displays the response by Jarvis when a task has been successfully added.
     *
     * @param newTask The newly added task
     * @return The confirmation message
     */
    public static String taskAdded(Task newTask) {
        String response = "";
        response += "Got it! I've added this task:\n";
        response += "\t" + newTask.toString() + "\n";
        response += "Now you have " + TaskList.getCounter() + " task(s) in the list.\n";
        return response;
    }

    /**
     * Displays the response by Jarvis when the task is done.
     *
     * @param taskNum The number of the task that was completed
     * @return The message to be displayed when a task is done
     */
    public static String taskDone(int taskNum) {
        String response = "";
        response += "Good job! I've marked this task as done:\n";
        response += "\t" + TaskList.getTaskList().get(taskNum).toString() + "\n";
        return response;
    }

    /**
     * Displays the response by Jarvis when the task is deleted.
     *
     * @param task The task that was deleted
     * @return The message to be displayed when a task is deleted
     */
    public static String taskDeleted(Task task) {
        String response = "";
        response += "Noted. I've removed this task from your main list:\n";
        response += "\t" + task.toString() + "\n";
        response += "Now you have " + TaskList.getCounter() + " task(s) in the list.\n";
        return response;
    }

    /**
     * Returns a list of tasks saved by Jarvis
     *
     * @return a list of tasks save by Jarvis
     */
    public static String listTasks() {
        int num = 1;
        String result = Ui.NO_TASKS; // If there are no tasks in the list

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (i == 0) {
                result = "Your tasks are: \n";
            }
            result += num + "." + TaskList.getTaskList().get(i).toString() + "\n"; //Print the task
            num++;
        }

        return result;
    }

    /**
     * Returns the list of tasks set for/due today and all todo tasks
     *
     * @return the list of tasks set for/due today and all todo tasks
     */
    public static String listTodayTasks() {
        String result = "";
        result += "Tasks scheduled for today are: \n";
        int num = 1;

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            //If the task is a todo, include it in today's list of tasks
            if (TaskList.getTaskList().get(i) instanceof Todo) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            } else {
                // Retrieve the current year, month and date
                int currYear = LocalDateTime.now().getYear();
                int currMonth = LocalDateTime.now().getMonthValue();
                int currDate = LocalDateTime.now().getDayOfMonth();

                // Create a LocalDateTime object to represent start and end times of the day
                LocalDateTime start = LocalDateTime.of(currYear, currMonth, currDate, 0, 0);
                LocalDateTime end = LocalDateTime.of(currYear, currMonth, currDate, 23, 59);

                // If it is a deadline task
                if (TaskList.getTaskList().get(i) instanceof Deadline) {
                    //Check if the deadline is after the day starts
                    boolean isDeadlineAfterDayStarts =
                            (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isAfter(start);
                    // Check if the deadline is before the day end
                    boolean isDeadlineBeforeDayEnds =
                            (((Deadline) TaskList.getTaskList().get(i)).getDeadline()).isBefore(end);

                    if (isDeadlineAfterDayStarts && isDeadlineBeforeDayEnds) {
                        result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                        num++;
                    }

                // If the event start and end times fall in between the start and end times of the day, add it to the
                // list of tasks for the day
                } else if (TaskList.getTaskList().get(i) instanceof Event) {
                    //Check if the event starts after the day starts
                    boolean isEventAfterDayStarts =
                            (((Event) TaskList.getTaskList().get(i)).getEventStart()).isAfter(start);
                    //Check if the event ends before the day end
                    boolean isEventBeforeDayEnds =
                            (((Event) TaskList.getTaskList().get(i)).getEventEnd()).isBefore(end);

                    if (isEventAfterDayStarts && isEventBeforeDayEnds) {
                        result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                        num++;
                    }
                }
            }
        }

        // If there are no tasks for today
        if (num == 1) {
            result += "\tLooks like there is nothing due today!\n";
        }
        return result;
    }

    /**
     * Returns the list of tasks that matches the searchPhrase.
     *
     * @param searchPhrase The key word/phrase that the user inputs for the search
     * @return list of tasks that matches the searchPhrase
     */
    public static String findTasks(String searchPhrase) {
        String result = "";
        result += "Here are the matching tasks in your list:\n";
        int num = 1;

        // Extract the search word/phrase
        searchPhrase = searchPhrase.substring(5);

        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            // Check if the search word/phrase is contained in the task description
            if (TaskList.getTaskList().get(i).getDescription().contains(searchPhrase)) {
                result += "\t" + num + "." + TaskList.getTaskList().get(i).toString() + "\n";
                num++;
            }
        }
        // If there are no matching results
        if (num == 1) {
            result += "\tNo matching results found!\n";
        }
        return result;
    }

    /**
     * Displays the error message when the note number keyed in by the user is invalid.
     *
     * @param userInput The note number keyed in by the user
     * @return The error message
     */
    public static String invalidNoteNum(int userInput) {
        Ui.INVALID_INDEX = "Hmm, I don't have note " + (userInput + 1) + " in my list. Please key in 'notes' if you'd like to " +
                "view your list of notes again!";
        return Ui.INVALID_INDEX;
    }

    /**
     * Prints out the list of notes saved by Jarvis.
     */
    public static String listNotes() {
        int num = 1;
        String result = Ui.NO_NOTES;

        for (int i = 0; i < NoteList.getNoteList().size(); i++) {
            if (i == 0) {
                result = "Your notes are: \n";
            }
            result += num + ". " + NoteList.getNoteList().get(i).toString() + "\n";
            num++;
        }

        return result;
    }

    /**
     * Displays the response by Jarvis when the note is added.
     *
     * @param newNote The note that was added
     * @return The message to be displayed when a note is added
     */
    public static String noteAdded(Note newNote) {
        String response = "";
        response += "Got it! I've added this note:\n";
        response += "\t" + newNote.toString() + "\n";
        response += "Now you have " + NoteList.getCounter() + " note(s) in the list.\n";
        return response;
    }

    /**
     * Displays the response by Jarvis when the note is deleted.
     *
     * @param note The note that was deleted
     * @return The message to be displayed when a note is deleted
     */
    public static String noteDeleted(Note note) {
        String response = "";
        response += "Noted. I've removed this note from your notes list:\n";
        response += "\t" + note.toString() + "\n";
        response += "Now you have " + NoteList.getCounter() + " notes(s) in the notes list.\n";
        return response;
    }
}
