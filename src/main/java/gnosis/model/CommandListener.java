package gnosis.model;

import gnosis.ui.GnosisUI;
import gnosis.util.GnosisException;

public interface CommandListener {
    void executeCommand(Command commandToExecute, String userInput, GnosisUI view) throws GnosisException;
}
