package duke.commands;

import java.util.Arrays;
import java.util.List;

/**
 * Enumerates the command types supported by Augury.
 */
public enum CommandTypes {
    QUIT("quit", "exit", "bye", "q"),
    LIST("list", "ls", "l"),
    MARKDONE("done"),
    DELETE("delete", "del", "rm"),
    MAKE("todo", "deadline", "event"),
    FIND("find", "f"),
    SORT("sort"),
    HELP("help", "faq", "h", "?");

    private final List<String> aliases;

    CommandTypes(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public List<String> getAliases() {
        return aliases;
    }
}
