package gnosis.model;

import gnosis.task.TaskCommandManager;
import gnosis.ui.GnosisUI;
import gnosis.util.GnosisException;

public interface ActionHandler {

	void setTaskActionHandler(GnosisUI view, TaskCommandManager taskCommandManager, Command command, String input)
										throws GnosisException;
}
