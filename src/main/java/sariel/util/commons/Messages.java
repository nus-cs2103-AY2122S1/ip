package sariel.util.commons;

import javafx.scene.image.Image;
import sariel.util.windows.Main;

public class Messages {


    public static final String GREETINGS = "Greetings! I'm Sariel\nWhat can I do for you?";
    public static final String HLINE = "\t----------------------------";
    public static final String LOGO = "\n"
            + " _____            _      _ \n"
            + "/  ___|          (_)    | |\n"
            + "\\ `--.  __ _ _ __ _  ___| |\n"
            + " `--. \\/ _` | '__| |/ _ \\ |\n"
            + "/\\__/ / (_| | |  | |  __/ |\n"
            + "\\____/ \\__,_|_|  |_|\\___|_|\n"
            + "                           \n"
            + "                           \n";


    //Messages that have to be formatted to include tasks
    public static final String TASK_ADDED =
            "Roger! I have added this task.\n"
                    + "%s\n"
                    + "Now you have %d tasks in the list.\n";
    public static final String TASK_DELETED =
            "The following task has been removed\n"
                    + "%s\n"
                    + "Now you have %d tasks remaining";
    public static final String TASK_NO_DESCRIPTOR_ERROR = "OOPS!!! The description of a %s cannot be empty.";

    public static final String TASK_NOT_UNDERSTOOD_ERROR = "OOPS!!! I do no know what to do";

    public static final String TASK_ALREADY_ADDED = "Task already added: %s";
    public static final String TASK_COMPLETE =
            "Nice, I've marked this task as done\n"
                    + "%s";


    //Error messages
    public static final String EVENT_NO_INPUT_ERROR_MESSAGE =
            "☹ OOPS!!! The event deadline must be filled in prefixed by /at";
    public static final String INVALID_DONE_INPUT =
            "☹ OOPS!!! The input integer is invalid for the task list.";

    public static final String DEADLINE_NO_INPUT_ERROR_MESSAGE =
            "☹ OOPS!!! The deadline deadline must be filled in prefixed by /by";

    public static final String EXPECTED_DATE_FORMAT = "Expected date format YYYY MM DD";
    public static final String NO_DATE_SELECTED = "Please select a date.";

    //images
    public static final Image ICON = new Image(Main.class.getResourceAsStream("/images/icon.png"));
    public static final Image USER = new Image(Main.class.getResourceAsStream("/images/daUser.png"));
    public static final Image SARIEL = new Image(Main.class.getResourceAsStream("/images/daDuke.png"));

}
