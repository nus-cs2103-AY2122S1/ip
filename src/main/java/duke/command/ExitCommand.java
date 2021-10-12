package duke.command;

import duke.DukeList;
import duke.Storage;
import duke.exception.CorruptedFileException;
import javafx.application.Platform;

public class ExitCommand extends DukeCommand {

    public ExitCommand() {
        super();
    }

    @Override
    public String run(DukeList list) throws CorruptedFileException {
        Platform.exit();
        Storage.saveFile(list);
        return "Exiting";
    }

}
