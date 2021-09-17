package gnosis.model;

import gnosis.ui.GnosisUI;
import gnosis.util.GnosisException;

/**
 * This class an interface to listen for commands.
 *
 * @author Pawandeep Singh
 *
 * */
public interface CommandListener {
    void executeCommand(Command commandToExecute, String userInput, GnosisUI view) throws GnosisException;
}
