package gnosis.util;

/**
 * This Class represents all public String constants
 * for user display.
 *
 * @author Pawandeep Singh
 *
 * */
public class GnosisConstants {

    // COMMAND IDENTIFIERS
    public static final String SYSTEM_EXIT_IDENTIFER = "EXIT";
    public static final String TASK_COMMAND_IDENTIFIER = "TASK";
    public static final String PLACE_COMMAND_IDENTIFIER = "PLACE";

    //MESSAGE CONSTANTS
    public static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n"
            + "The spark to meet your needs.\nHow can I assist you ?";

    public static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n"
            + "I welcome you back soon.";

    public static final String COMMAND_NOT_FOUND_MESSAGE = "COMMAND NOT FOUND.";

    public static final String LISTING_MATCH_KEYWORD_MESSAGE = "Listing all tasks with matching keyword: ";

    //STORAGE MESSAGE CONSTANTS
    public static final String DATA_TASK_FILE_FOUND_MESSAGE = "SAVED TASKS DATA FOUND -- "
            + "YOU CAN VIEW YOUR SAVED TASKS -- ";

    public static final String DATA_TASK_FILE_NOT_FOUND_MESSAGE = "NO SAVED TASKS DATA FOUND -- "
            + "NEW TASKS CAN NOW BE SAVED IN THE DATA FOLDER -- ";

    public static final String DATA_PLACE_FILE_FOUND_MESSAGE = "SAVED PLACE DATA FOUND -- "
            + "YOU CAN VIEW YOUR SAVED PLACE -- ";

    public static final String DATA_PLACE_FILE_NOT_FOUND_MESSAGE = "NO SAVED PLACE DATA FOUND -- "
            + "NEW PLACE CAN NOW BE ADDED IN THE DATA FOLDER -- ";

    //TASK MESSAGE CONSTANTS
    public static final String TODO_EMPTY_EXCEPT_MESSAGE = "Todo description cannot be empty!";

    public static final String DEADLINE_EMPTY_EXCEPT_MESSAGE = "Deadline description and "
            + "specified date time cannot be empty.\n"
            + "use /by to denote date and time";

    public static final String EVENT_EMPTY_EXCEPT_MESSAGE = "Event description and "
            + "specified schedule cannot be empty.\n"
            + "use /at to denote event schedule";

    public static final String TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE = "You can only use Task index"
            + " from the task number from the list command";

    public static final String DONE_COMMAND_NUM_INPUT_EXCEPT_MESSAGE = "Please enter only numbers "
            + "after done command";

    public static final String PLACE_EMPTY_EXCEPT_MESSAGE = "Place cannot be empty";

    public static final String PLACE_FORMAT_EXCEPT_MESSAGE = "Place not in right format, SHOULD BE: "
            + "<name> /at <address> /on <datetime>";

    //UTIL EXCEPT MESSAGES

    public static final String DATETIME_FORMAT_EXCEPT_MESSAGE = "ERROR, ENTER DATE FORMAT AS SUCH:\n"
            + "<day>/<month>/<year> <24HR format time>\ne.g: 14/08/2021 1600";






}
