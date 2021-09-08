package duke;

import java.io.IOException;

public interface GeneralCommand {
    void execute() throws IOException, DeleteException;
}
