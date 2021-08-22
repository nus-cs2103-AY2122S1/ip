import java.io.*;
import java.util.*;

abstract class Command {
	abstract void execute(TaskList tasks, UI ui, Storage storage) throws IOException;

	public boolean isExit() {
		return false;
	}
}
