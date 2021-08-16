import java.util.*;

public class ToDo extends Task {
	public static ToDo init(String line) {
		if (line.length() <= 5) {
			throw new TaskException(new ToDo("?"));
		}
		return new ToDo(line.substring(5));
	}

	public ToDo(String description) {
		super(description);
		this.type = "ToDo";
	}

	public String getFormat() {
		return "todo {description}";
	}
}