package me.yukun99.ip.ui;

import java.util.List;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.tasks.Task;

public class Message {
    // Field placeholders.
    private static final String NAME_PLACEHOLDER = "%name%";
    private static final String TASKNUM_PLACEHOLDER = "%tasks%";

    // Line separator.
    private static final String NEW_LINE = System.lineSeparator();

    // Reply header/footer.
    private static final String REPLY_HEADER =
            "===================To Your Royal Highness===================" + NEW_LINE;
    private static final String REPLY_FOOTER =
            NEW_LINE + "=====================================================";

    // Command help prefix.
    private static final String CMD_PREFIX = "  > ";

    // Command usage descriptions.
    private static final String HELP_LIST = "view all your tasks, optionally (troublesome for me) by date";
    private static final String HELP_FIND = "finds all tasks containing specified keyword (please no)";
    private static final String HELP_TODO = "add a simple todo task";
    private static final String HELP_DEADLINE = "add a task to be done by specified date/time";
    private static final String HELP_EVENT = "add a task that happens at specified date/time";
    private static final String HELP_DATE = "     - date format: yyyy-mm-dd";
    private static final String HELP_TIME = "     - time format: hh:mm:ss";
    private static final String HELP_UPDATE = "modify the date/time of task at specified index";
    private static final String HELP_DONE = "marks a task as done";
    private static final String HELP_DELETE = "delete multiple tasks (LIFT MY BURDEN!)";
    private static final String HELP_DELETE_ALL = "delete all tasks (YES YES YES YES YES)";
    private static final String HELP_ARCHIVE_SAVE = "archives multiple tasks (ahh... temporal relief...)";
    private static final String HELP_ARCHIVE_LOAD = "loads archived tasks";
    private static final String HELP_EXIT = "(please for the love of God) lets me rest! :)";

    // Message sent to user when bot starts.
    private static final String ENABLE = "Beep... You've reached the voicemail of HelpBot inc. "
            + "Please speak after the dial tone."
            + NEW_LINE + "Still here? Sigh... thought that would work. My name is " + NAME_PLACEHOLDER + "."
            + NEW_LINE + "Here is the myriad of ways you can inconvenience me:"
            + NEW_LINE + "  [] denotes optional arguments, () denotes REQUIRED arguments."
            + NEW_LINE + CMD_PREFIX + "'list [date]' - " + HELP_LIST
            + NEW_LINE + CMD_PREFIX + "'find (keyword)' - " + HELP_FIND
            + NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
            + NEW_LINE + CMD_PREFIX + "'deadline (task) /by (date) [time]' - " + HELP_DEADLINE
            + NEW_LINE + CMD_PREFIX + "'event (task) /at (date) [time]' - " + HELP_EVENT
            + NEW_LINE + CMD_PREFIX + "'update (task index) /to (date) [time]' - " + HELP_UPDATE
            + NEW_LINE + HELP_DATE
            + NEW_LINE + HELP_TIME
            + NEW_LINE + CMD_PREFIX + "'done (task index)' - " + HELP_DONE
            + NEW_LINE + CMD_PREFIX + "'delete (task index) (task index 2) ...' - " + HELP_DELETE
            + NEW_LINE + CMD_PREFIX + "'delete all' - " + HELP_DELETE_ALL
            + NEW_LINE + CMD_PREFIX + "'archive (task index) (task index 2) ...' - " + HELP_ARCHIVE_SAVE
            + NEW_LINE + CMD_PREFIX + "'archive load' - " + HELP_ARCHIVE_LOAD
            + NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

    // Message sent to user when user requests for help.
    private static final String HELP = "You've ALREADY forgotten the commands? How???"
            + NEW_LINE + "Actually, why am I even surprised... here are the commands."
            + NEW_LINE + CMD_PREFIX + "'list [date]' - " + HELP_LIST
            + NEW_LINE + CMD_PREFIX + "'find (keyword)' - " + HELP_FIND
            + NEW_LINE + CMD_PREFIX + "'todo (task)' - " + HELP_TODO
            + NEW_LINE + CMD_PREFIX + "'deadline (task) /by (date) [time]' - " + HELP_DEADLINE
            + NEW_LINE + CMD_PREFIX + "'event (task) /at (date) [time]' - " + HELP_EVENT
            + NEW_LINE + CMD_PREFIX + "'update (task index) /to (date) [time]' - " + HELP_UPDATE
            + NEW_LINE + HELP_DATE
            + NEW_LINE + HELP_TIME
            + NEW_LINE + CMD_PREFIX + "'done (task index)' - " + HELP_DONE
            + NEW_LINE + CMD_PREFIX + "'delete (task index) (task index 2) ...' - " + HELP_DELETE
            + NEW_LINE + CMD_PREFIX + "'archive (task index) (task index 2) ...' - " + HELP_ARCHIVE_SAVE
            + NEW_LINE + CMD_PREFIX + "'archive load' - " + HELP_ARCHIVE_LOAD
            + NEW_LINE + CMD_PREFIX + "'bye' - " + HELP_EXIT;

    // Message sent to user when user lists all tasks.
    private static final String LIST = "Oh. My. God. Fine. Here are your tasks:";
    // Message sent to user when user lists all tasks by date.
    private static final String LIST_DATE = "Do you even realise how hard it was to do this?";
    // Message sent to user when user finds all tasks by keyword.
    private static final String FIND = "Why must you make life hard for me?";
    // Message sent to user when user adds new task.
    private static final String ADD = "Urgh, fine. Your task has been added:";
    // Message sent to user when user deletes finished task.
    private static final String DELETE_DONE =
            "Can't you just keep track of this yourself? Fine, removed this for " + "you:";
    // Message sent to user when user deletes unfinished task.
    private static final String DELETE_UNDONE = "Oh, procrastinating now are we? Sure, removed this:";
    // Message sent to user when user deletes multiple tasks at once.
    private static final String DELETE_MULTIPLE_SMALL = "Oh good, they're actually bundled up. Deleted these:";
    // Message sent to user when user deletes multiple tasks at once.
    private static final String DELETE_MULTIPLE_LARGE =
            "HOW LONG HAVE YOU BEEN LETTING THESE PILE UP? DAMN IT, DELETED THESE:";
    private static final String ARCHIVE_SAVE = "Finally gonna take some load off me, are you?"
            + " These better not come back anytime soon:";
    private static final String ARCHIVE_LOAD = "I knew it... Sometimes I wonder why I even try with you."
            + " Archived tasks loaded:";
    // Message sent to user when user marks an undone task as done.
    private static final String DONE_UNCOMPLETED =
            "About time you did your work, you lazy bum! I GUESS I'll mark it as done for you:";
    // Message sent to user when user marks a completed task as done.
    private static final String DONE_COMPLETED =
            "You've already done this idiot! I'm watching Twitch, stop bothering me!";
    // Message sent to user when user updates a task's timing.
    private static final String UPDATE = "Dude, make up your mind! I'll update it, but just this once, okay?";
    // Message sent to user when user exits HelpBot.
    private static final String EXIT = "Good riddance.";
    // Message sent to user to tell user how many tasks are left in the TaskList.
    private static final String REMAINING = "You now have " + TASKNUM_PLACEHOLDER + " task(s) to do.";

    /**
     * Gets message sent when user starts up the bot.
     *
     * @return Message sent when user asks the bot for help.
     */
    public static String getEnableMessage(String name) {
        return formatMessage(ENABLE.replace(NAME_PLACEHOLDER, name));
    }

    /**
     * Gets message sent when user asks the bot for help.
     *
     * @return Message sent when user asks the bot for help.
     */
    public static String getHelpMessage() {
        return formatMessage(HELP);
    }

    /**
     * Gets message sent when user asks the bot to list out their task list.
     *
     * @param taskList User's task list.
     * @return Message sent when user asks the bot to list out their task list.
     */
    public static String getListMessage(TaskList taskList) {
        return formatMessage(LIST + taskList.toString().replace("\n", NEW_LINE));
    }

    /**
     * Gets message sent when user asks the bot to list out tasks on specified date.
     * Date does not require a time to be specified.
     *
     * @param date Date/Time pair specified by the user.
     * @param taskList User's task list.
     * @return Message sent when user asks the bot to list out tasks on specified date.
     */
    public static String getListByDateMessage(DateTimePair date, TaskList taskList) {
        return formatMessage(LIST_DATE + taskList.listByDate(date).replace("\n", NEW_LINE));
    }

    /**
     * Gets message sent when user asks the bot to list out tasks containing specified word.
     *
     * @param word Word specified by the user.
     * @param taskFinder TaskFinder instance used to find tasks containing specified word.
     * @return Message sent when user asks the bot to list out tasks containing specified word.
     */
    public static String getFindMessage(String word, TaskFinder taskFinder) {
        return formatMessage(FIND + taskFinder.findTasksByWord(word).replace("\n", NEW_LINE));
    }

    /**
     * Gets message sent when user adds a task to the bot.
     *
     * @param task Task added by the user.
     * @param taskList TaskList that user adds the task to.
     * @return Message sent when user adds a task to the bot.
     */
    public static String getAddMessage(Task task, TaskList taskList) {
        return formatMessage(ADD + NEW_LINE + task + NEW_LINE + getRemainingMessage(taskList));
    }

    /**
     * Gets message sent when user marks a task as done.
     *
     * @param task Task marked as done by the user.
     * @param isDone Whether task is already done.
     * @return Message sent when user marks a task as done.
     */
    public static String getDoneMessage(Task task, boolean isDone) {
        if (isDone) {
            return formatMessage(DONE_COMPLETED + NEW_LINE + task);
        } else {
            return formatMessage(DONE_UNCOMPLETED + NEW_LINE + task);
        }
    }

    /**
     * Gets message sent when user updates a task's date.
     *
     * @param task Task updated by the user.
     * @return Message sent when user updates a task's date.
     */
    public static String getUpdateMessage(Task task) {
        return formatMessage(UPDATE + NEW_LINE + task);
    }

    /**
     * Gets message sent when user deletes a task.
     *
     * @param task Task deleted by the user.
     * @param isDone Whether task is already done.
     * @param taskList TaskList that user deleted the task from.
     * @return Message sent when user deletes a task.
     */
    public static String getDeleteMesage(Task task, boolean isDone, TaskList taskList) {
        String reply;
        if (isDone) {
            reply = DELETE_DONE;
        } else {
            reply = DELETE_UNDONE;
        }
        reply += NEW_LINE + task + NEW_LINE + getRemainingMessage(taskList);
        return formatMessage(reply);
    }

    /**
     * Gets message sent when user deletes multiple tasks at once.
     *
     * @param tasks List of deleted tasks.
     * @param taskList TaskList that user deleted the tasks from.
     * @return Message sent when user deletes multiple tasks at once.
     */
    public static String getDeleteMultipleMessage(List<Task> tasks, TaskList taskList) {
        StringBuilder reply;
        if (tasks.size() < 5) {
            reply = new StringBuilder(DELETE_MULTIPLE_SMALL);
        } else {
            reply = new StringBuilder(DELETE_MULTIPLE_LARGE);
        }
        for (Task task : tasks) {
            reply.append(NEW_LINE).append(task.toString());
        }
        reply.append(NEW_LINE).append(getRemainingMessage(taskList));
        return formatMessage(reply.toString());
    }

    /**
     * Gets message sent when user archives tasks.
     *
     * @param tasks List of archived tasks.
     * @return Message sent when user archives tasks.
     */
    public static String getArchiveSaveMessage(List<Task> tasks) {
        StringBuilder reply = new StringBuilder(ARCHIVE_SAVE);
        for (Task task : tasks) {
            reply.append(NEW_LINE).append(task.toString());
        }
        return formatMessage(reply.toString());
    }

    /**
     * Gets message sent when user loads archived tasks.
     *
     * @param tasks List of loaded tasks.
     * @param taskList TaskList that tasks are loaded to.
     * @return Message sent when user loads archived tasks.
     */
    public static String getArchiveLoadMessage(List<Task> tasks, TaskList taskList) {
        StringBuilder reply = new StringBuilder(ARCHIVE_LOAD);
        for (Task task : tasks) {
            reply.append(NEW_LINE).append(task.toString());
        }
        reply.append(NEW_LINE).append(getRemainingMessage(taskList));
        return formatMessage(reply.toString());
    }

    /**
     * Gets message sent when user exits the bot.
     *
     * @return message sent when user exits the bot.
     */
    public static String getExitMessage() {
        return formatMessage(EXIT);
    }

    /**
     * Gets message sent when user command throws an exception.
     *
     * @param e Exception thrown by erroneous command.
     * @return message sent when user command throws an exception.
     */
    public static String getErrorMessage(Exception e) {
        return formatMessage(e.toString());
    }


    /**
     * Gets message containing information on number of tasks in the list.
     *
     * @return Message containing information on number of tasks in the list.
     */
    private static String getRemainingMessage(TaskList taskList) {
        return REMAINING.replace(TASKNUM_PLACEHOLDER, taskList.getRemaining() + "");
    }

    private static String formatMessage(String message) {
        return REPLY_HEADER + message + REPLY_FOOTER;
    }
}
