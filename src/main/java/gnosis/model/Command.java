package gnosis.model;

import gnosis.ui.GnosisUI;
import gnosis.util.GnosisConstants;
import gnosis.util.GnosisException;

/**
 * This enum specifies the different commands
 * Gnosis presents.
 *
 * @author Pawandeep Singh
 * */
public enum Command {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    FIND,
    DONE,
    DELETE,
    PLACE,
    VISITED,
    BYE;

    public static String getCommandIdentifier(Command command) {
        String identifier;
        switch (command) {
        case BYE:
            identifier = GnosisConstants.SYSTEM_EXIT_IDENTIFER;
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
        case LIST:
        case FIND:
        case DONE:
        case DELETE:
            identifier = GnosisConstants.TASK_COMMAND_IDENTIFIER;
            break;
        case PLACE:
        case VISITED:
            identifier = GnosisConstants.PLACE_COMMAND_IDENTIFIER;
            break;
        default:
            return GnosisConstants.COMMAND_NOT_FOUND_MESSAGE;

        }
        return identifier;
    }
}

