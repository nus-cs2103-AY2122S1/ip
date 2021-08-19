import java.security.PublicKey;

/**
 * This Class represents all public String constants
 * for user display.
 *
 * @author Pawandeep Singh
 * @version Level-3
 *
 * */
public class GnosisConstants {

    public static final String DISPLAY_FORMAT = "-- \t============\t --";

    //MESSAGE CONSTANTS
    public static final String GREET_MESSAGE = "Welcome, I am Gnosis.\n" +
            "The spark to meet your needs.\nHow can I assist you ?";

    public static final String BYE_MESSAGE = "Good bye.\nI hope your needs have been sparked.\n" +
            "I welcome you back soon.";

    //EXCEPTION MESSAGE CONSTANTS
    public static final String TODO_EMPTY_MESSAGE = "Todo description cannot be empty!";

    public static final String DEADLINE_EMPTY_MESSAGE = "Deadline description and specified date time cannot be empty.\n"
            + "use /by to denote date and time";

    public static final String EVENT_EMPTY_MESSAGE = "Event description and specified schedule cannot be empty.\n" +
            "use /at to denote event schedule";

    public static final String TASK_INDEX_OUT_OF_BOUNDS_MESSAGE = "Tasks can only be marked done " +
            "using index number from list command";

    public static final String DONE_COMMAND_NUM_INPUT_MESSAGE = "Please enter only numbers after done command";




}
