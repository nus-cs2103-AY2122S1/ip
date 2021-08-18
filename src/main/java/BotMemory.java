import java.util.ArrayList;
import java.util.List;

//  BotMemory imitates a bot's memory unit that stores the commonly used messages
//  and tracks the tasks added
public class BotMemory {

    final String LOGO =
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

    final String MESSAGE_GREETING = "Hallo! My name's Peter!\n\tHow may I be of service to you?";
    final String MESSAGE_GOODBYE = "Good day! I'm gonna find Lois if you're not using me!";
    final String MESSAGE_TASK_REPORT = "Here are all your tasks! No Procrastination!";
    final String MESSAGE_TASK_COMPLETE = "Task Completed:";
    final String MESSAGE_CHEERING = "Great job! You're the best! Keep up the good work! Oho! Oho! Ohooooooo!";
    final String MESSAGE_ADD_TASK_NOTICE = "GOSH! You have one more task.";
    final String MESSAGE_ADD_TASK_SUMMARY = "Now you have %s task in your list";
    final String MESSAGE_REMOVE_TASK = "YEAH! You have removed one task from your list!";
    final String ERROR_MESSAGE_PROMPT = "ERROR: ";
    final String ERROR_MESSAGE_INVALID_COMMAND = "OPS! I am not even sure whether I can accept this command!";
    final String ERROR_MESSAGE_INVALID_COMMAND_FORMAT = "OPS! Your command format is wrong! Enter in the right format please!";
    final String ERROR_MESSAGE_EMPTY_TASKLIST = "HEY! You have no task at hand! Get your life together!";
    final String ERROR_MESSAGE_TASK_OUT_OF_RANGE = "HEY! You don't have that many tasks!";
    final String ERROR_MESSAGE_INVALID_TASK_INDEX = "HOLD ON! The index you entered is not an Integer!";

    //  A list to track the tasks added
    List<Task> taskTracker = new ArrayList<Task>();

    /**
     * Constructor
     */
    public BotMemory(){}
}
