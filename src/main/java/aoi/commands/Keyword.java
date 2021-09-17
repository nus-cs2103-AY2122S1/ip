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
    LIST("LIST", "LS"),
    DONE("DONE", "D"),
    DELETE("DELETE", "DEL", "RM"),
    FIND("FIND"),
    EXIT("BYE", "EXIT", "QUIT");

    private final Map<String, Keyword> aliases = new HashMap<>();

    Keyword(String... input) {
        for (final String alias: input) {
            aliases.put(alias, this);
        }
    }

    public static Keyword getKeyword(String alias) throws IllegalArgumentException {
        for (Keyword keyword : Keyword.values()) {
            Keyword kw = keyword.aliases.get(alias);
            if (kw != null) {
                return kw;
            }
        }
        throw new IllegalArgumentException();
    }
}
