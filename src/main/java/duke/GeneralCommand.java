package duke;

import java.io.IOException;

public interface GeneralCommand {
    String execute() throws IOException, DeleteException;
}
