package util;

import javax.swing.plaf.PanelUI;
import java.security.PublicKey;

/**
 * This Class represents all public String constants
 * for user display.
 *
 * @author Pawandeep Singh
 * @version Level-6
 *
 * */
public class GnosisConstants {

    public static final String DISPLAY_FORMAT = "-- \t============\t --";

    //MESSAGE CONSTANTS
    public static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n" +
            "The spark to meet your needs.\nHow can I assist you ?";

    public static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n" +
            "I welcome you back soon.";

    public static final String COMMAND_NOT_FOUND_MESSAGE = "COMMAND NOT FOUND.";

    //STORAGE MESSAGE CONSTANTS
    public static final String DATA_TASK_FILE_FOUND_MESSAGE = "SAVED DATA FOUND -- YOU CAN VIEW YOUR SAVED TASKS -- ";

    public static final String DATA_TASK_FILE_NOT_FOUND_MESSAGE = "NO SAVED DATA FOUND -- NEW TASKS CAN NOW BE SAVED IN THE DATA FOLDER -- ";

    //EXCEPTION MESSAGE CONSTANTS
    public static final String TODO_EMPTY_EXCEPT_MESSAGE = "Todo description cannot be empty!";

    public static final String DEADLINE_EMPTY_EXCEPT_MESSAGE = "Deadline description and specified date time cannot be empty.\n"
            + "use /by to denote date and time";

    public static final String EVENT_EMPTY_EXCEPT_MESSAGE = "Event description and specified schedule cannot be empty.\n" +
            "use /at to denote event schedule";

    public static final String TASK_INDEX_OUT_OF_BOUNDS_EXCEPT_MESSAGE = "You can only use Task index" +
            " from the task number from the list command";

    public static final String DONE_COMMAND_NUM_INPUT_EXCEPT_MESSAGE = "Please enter only numbers after done command";

    public static final String DATETIME_FORMAT_EXCEPT_MESSAGE = "ERROR, ENTER DATE FORMAT AS SUCH:\n" +
            "<day>/<month>/<year> <24HR format time>\ne.g: 14/08/2021 1600";






}
