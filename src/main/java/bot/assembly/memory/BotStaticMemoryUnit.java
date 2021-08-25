package bot.assembly.memory;

//  BotMemory imitates a bot's memory unit that stores the commonly used messages
//  and tracks the tasks added
public class BotStaticMemoryUnit {

    public final String LOGO =
                    "░░░░░░░░░░░░░░░░▄▄▄███████▄▄░░░░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░░▄███████████████▄░░░░░░░░░\n" +
                    "░░░░░░░░░░░░░█▀▀▀▄░░░░█████▀▀███▄░░░░░░░\n" +
                    "░░░░░░░░░░░░█░░▄░░█▄▄█░░░░▀▄▄█████░░░░░░\n" +
                    "░░░░░░░░░▄▀▀▀▄▄▀▀▀▀▀▀▄░░▀░░█▀▀▀░▀██░░░░░\n" +
                    "░░░░░░░▄▀░░░░░█░░░░░░▀▄▄▄▄█░░░░░░░▀▄░░░░\n" +
                    "░░░░░░▄▀░░░░░▄█▀▄▄▄▄░░░░░▄░░░░░░░░░█░░░░\n" +
                    "░░░░░░█░░░░░▄█▀▄▄▄▄▄▄▄▄▄▀▀░░░░░░░░░▀▄░░░\n" +
                    "░░░░░█░░░░░░▀█▄░░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▄░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▄█░░░░░░░░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▄▀░░░░░░░░▀▄░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░▀▄░░░▄░░░░░█░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░▀▀▀▀▀█▄▄▀░░░░░░░░░░░░░█░░░\n" +
                    "░░░░░█░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▄██░░\n" +
                    "░░░░░█▄░░░░░░░░░░░░░░░░░░░░░░░░░░▄▀▀▄▀▄▄\n" +
                    "░░░░▄█▀▄░░░░░░░░░░░░░░░░░░░░░░▄▄▀░░▄▀░░░\n" +
                    "░▄░▀░░█░▀▄▄░░░░░░░░░░░░░░░▄▄▄▀░░░▄▀░░░░░\n" +
                    "▀░░░░░░▀▄░░▀▄░░░░░░░░▄▄▄▀▀░░░░▄▀▀░░░░░░░";

    public final String MESSAGE_GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    public final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    public final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    public final String MESSAGE_TASK_COMPLETE = "Task Completed:";
    public final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    public final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    public final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in your list";
    public final String MESSAGE_REMOVE_TASK = "YEAH! You have removed one task from your list!";
    public final String ERROR_MESSAGE_PROMPT = "ERROR: ";
    public final String ERROR_MESSAGE_INVALID_COMMAND = "OPS! I am not even sure whether I can accept this command!";
    public final String ERROR_MESSAGE_INVALID_COMMAND_FORMAT = "OPS! Your command format is wrong! Enter in the right format please!";
    public final String ERROR_MESSAGE_EMPTY_TASKLIST = "HEY! You have no task at hand! Get your life together!";
    public final String ERROR_MESSAGE_TASK_OUT_OF_RANGE = "HEY! You don't have that many tasks!";
    public final String ERROR_MESSAGE_INVALID_TASK_INDEX = "HOLD ON! The index you entered is not an Integer!";
    public final String ERROR_MESSAGE_INVALID_DATA_FORMAT = "HOLD ON! The data: \n\t\t%s\n\t is in the wrong format! Stop feeding me shit!";
    public final String ERROR_MESSAGE_INVALID_FILE = "WAIT! There are some errors with your file!";
    public final String ERROR_MESSAGE_INVALID_DATETIME_FORMAT = "I CANNOT UNDER THE TIMING FORMAT! Please input in the following format \n\t\t{yyyy-mm-ddThh:mm:ss}";
    public final String HARD_DISK_DATA_NAME = "data.txt";

    public BotStaticMemoryUnit(){}

}
