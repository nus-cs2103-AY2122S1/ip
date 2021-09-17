package aoi.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates the keywords that user inputs.
 *
 * Adapted from StackOverflow.
 * @see <a href=https://stackoverflow.com/questions/41494056/add-alias-to-an-enum-in-java>Add alias to an enum</a>
 */
public enum Keyword {
    ADD("TODO", "EVENT", "DEADLINE"),
    LIST("LIST"),
    DONE("DONE"),
    DELETE("DELETE"),
    FIND("FIND");

    private final Map<String, Keyword> aliases = new HashMap<>();

    private Keyword(String... input) {
        for (final String alias: input) {
            aliases.put(alias, this);
        }
    }

    public Keyword forAlias(String alias) {
        return aliases.get(alias);
    }
}
