package katheryne;

import katheryne.task.Deadline;
import katheryne.task.Event;
import katheryne.task.Task;
import katheryne.task.Todo;

public class Message {
    // solution adapted from https://github.com/Yukun99/ip/blob/master/src/main/java/me/yukun99/ip/ui/Message.java

    // Field placeholders.
    private static final String NUMBER_OF_TASKS_PLACEHOLDER = "%tasks%";
    private static final String KEYWORD_PLACEHOLDER = "%keyword%";

    // Line separator.
    private static final String NEW_LINE = System.lineSeparator();

    // Command help prefix.
    private static final String CMD_PREFIX = "  > ";

    // Message sent to user to tell user how many tasks are in the TaskList.
    private static final String COUNT_TASKS = "You have " + NUMBER_OF_TASKS_PLACEHOLDER + " task(s).";
    // Message sent to user when bot starts.
    private static final String CATCHPHRASE = "Ad astra abyssosque!";
    // Saving Tasks.
    private static final String SAVE = "I'll save your tasks now.";
    
    // Error messages:
    // DateTimeParseError (Deadline)
    public static final String ERROR_DEADLINE_DATE = "The by time is in the wrong format. It must be in the format " 
            + "YYYY-MM-DD";
    // DateTimeParseError (Event)
    public static final String ERROR_EVENT_DATE = "The at time is in the wrong format. It must be in the " 
            + "format YYYY-MM-DD";
    
    // Event help text
    public static final String EVENT_HELP = "An event needs a description and a single /at time when it occurs in the format "
            + "2007-12-03";
    public static final String DEADLINE_HELP = "A deadline needs a description and a /by time in the format " 
            + "2007-12-03.";
    // No tasks found with keyword
    private static final String NO_TASKS_FOUND = "I couldn't find any tasks containing '" + KEYWORD_PLACEHOLDER 
            + "'. Try another word?";

    /**
     * Returns the text to greet the user. 
     * 
     * @param lst
     * @return
     */
    public static final String greet(TaskList lst) {
        String intro =  CATCHPHRASE + NEW_LINE + NEW_LINE + "I am Katheryne, the receptionist here at the " 
                + "Adventurers' Guild. How may I assist?" + NEW_LINE + NEW_LINE;
        String yourTasks;
        if (!lst.isEmpty()) {
            yourTasks = "I still have your list of tasks from last time. " + COUNT_TASKS.replace(NUMBER_OF_TASKS_PLACEHOLDER,
                    lst.getSize() +"");
        } else {
            // Add help here in the future.
            yourTasks = "I don't have any records on you. Is this your first time here?";
        }
        return intro + yourTasks;
    }

    /**
     * Get confirmation message for adding a deadline.
     * 
     * @param d
     * @param lst
     * @return
     */
    public static String addDeadline(Deadline d, TaskList lst) {
        return "Deadline '" + d.getDescription() + "' added to your list, scheduled for " + d.getStringDueBy() 
                + NEW_LINE + COUNT_TASKS.replace(NUMBER_OF_TASKS_PLACEHOLDER,lst.getSize() +"");
    }

    /**
     * Get confirmation message for adding an event.
     * 
     * @param e
     * @param lst
     * @return
     */
    public static String addEvent(Event e, TaskList lst) {
        return "Event '" + e.getDescription() + "' added to your list, scheduled for " + e.getStringAt()
                + NEW_LINE + COUNT_TASKS.replace(NUMBER_OF_TASKS_PLACEHOLDER,lst.getSize() +"");
    }

    /**
     * Get confirmation message for adding a todo.
     * 
     * @param t
     * @param lst
     * @return
     */
    public static String addTodo(Todo t, TaskList lst) {
        return "Todo '" + t.getDescription() + "' added to your list"
                + NEW_LINE + COUNT_TASKS.replace(NUMBER_OF_TASKS_PLACEHOLDER,lst.getSize() +"");
    }

    public static String getListTasksMessage(TaskList lst) {
        String tasks = "";
        for (int i = 1; i <= lst.getSize(); i++) {
            tasks += i + ") " + lst.getTask(i - 1) + NEW_LINE;
        }
        return COUNT_TASKS.replace(NUMBER_OF_TASKS_PLACEHOLDER,lst.getSize() +"") + NEW_LINE + tasks;
    }
    
    public static String getDeleteTasksMessage(Task t) {
        return "Task Deleted: " + t;
    }
    
    public static String getFoundTasksMessage(TaskList subList, String keyword) {
        String tasks = "";
        for (int i = 1; i <= subList.getSize(); i++) {
            tasks += i + ") " + subList.getTask(i - 1) + NEW_LINE;
        }
        return "Tasks containing '" + keyword + "' are listed below. The number of tasks is " + subList.getSize() 
                + "." + NEW_LINE + tasks + NEW_LINE;
    }
    
    public static String getTasksNotFoundMessage(String keyword) {
        return NO_TASKS_FOUND.replace(KEYWORD_PLACEHOLDER, keyword);
    }
    
    public static String getDoneMessage(Task t) {
        return "Task done: " + t;
    }

    /**
     * Get confrimation message for exiting Katheryne
     * 
     * @return
     */
    public static String goodbye() {
        return SAVE + NEW_LINE + NEW_LINE + CATCHPHRASE;
    }
    
}
