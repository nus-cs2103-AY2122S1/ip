import java.io.IOException;

interface ParseCommands {
    String execute(TaskList tasklist) throws DukeException, IOException;
}
