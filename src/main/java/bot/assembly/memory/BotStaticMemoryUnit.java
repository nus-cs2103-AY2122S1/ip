package bot.assembly.memory;

/**
 * A class that serves as a storage unit for (final) item
 */
public class BotStaticMemoryUnit {

    public final String MESSAGE_GREETING = "Hallo! My name's Peter!\nHow may I be of service to you?\n\nBelow are all the command formats to follow:\n"
            + "\ntodo taskDesc"
            + "\ndeadline taskDesc /by 2021-09-15T18:00:59"
            + "\nevent taskDesc /at 2021-09-15T18:00:59"
            + "\ndelete Index"
            + "\ndone Index"
            + "\nfind ...keywords"
            + "\nlist"
            + "\nmassops delete"
            + "\nmassops done";
    public final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    public final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    public final String MESSAGE_TASK_COMPLETE = "Task Completed:";
    public final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    public final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    public final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in your list";
    public final String MESSAGE_REMOVE_TASK = "YEAH! You have removed one task from your list!";
    public final String MESSAGE_KEYWORD_NO_FOUND = "OPS! I just checked your task list, you dont have anything related to:\n";
    public final String MESSAGE_TASK_FOUND = "HEY! We found your task using the following keywords:\n%s\nThese are the results:\n%s";
    public final String MESSAGE_LOAD_SUCCESS_FROM_HARD_DISK = "Nice! Data successfully loaded from hard disk!";
    public final String ERROR_MESSAGE_PROMPT = "ERROR: ";
    public final String ERROR_MESSAGE_INVALID_COMMAND = "OPS! I am not even sure whether I can accept this command!";
    public final String ERROR_MESSAGE_INVALID_COMMAND_FORMAT = "OPS! Your command format is wrong! Enter in the right format please!";
    public final String MESSAGE_EMPTY_TASKLIST = "HEY! You have no task at hand! That's great!";
    public final String ERROR_MESSAGE_TASK_OUT_OF_RANGE = "HEY! You don't have that many tasks!";
    public final String ERROR_MESSAGE_INVALID_TASK_INDEX = "HOLD ON! The index you entered is not an Integer!";
    public final String ERROR_MESSAGE_INVALID_DATA_FORMAT = "HOLD ON! The data: \n%s\n is in the wrong format! Stop feeding me shit!";
    public final String ERROR_MESSAGE_INVALID_FILE = "WAIT! There are some errors with your file!";
    public final String ERROR_MESSAGE_INVALID_DATETIME_FORMAT = "I CANNOT UNDER THE TIMING FORMAT! Please input in the following format \n{yyyy-mm-ddThh:mm:ss}";

    /**
     * Constructor
     */
    public BotStaticMemoryUnit(){}

}
