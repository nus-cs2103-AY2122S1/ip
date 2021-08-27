package duke.command;

import duke.exception.InvalidCommandException;

public class DukeCommandArgument {
    public static final DukeCommandArgument NONE = new DukeCommandArgument("Positional argument", "",
            DukeCommandArgumentType.NOT_ALLOWED);

    private final String name;
    private final String description;
    private final DukeCommandArgumentType type;

    DukeCommandArgument(String name, String description, DukeCommandArgumentType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("<%s>", name);
    }

    /**
     * Returns the argument formatted for use in the {@link DukeCommand#HELP} command.
     *
     * @return the formatted string
     */
    public String toDetailedString() {
        return String.format("%s - %s (%s)", toString(), description, type.toString());
    }

    /**
     * Asserts that the argument type can be fulfilled by the given argument value. Otherwise, throws an
     * {@link InvalidCommandException}
     *
     * @param argValue the argument value
     * @throws InvalidCommandException if the argument value is incompatible with the argument type
     */
    void assertCompatibilityWith(String argValue) throws InvalidCommandException {
        if (type == DukeCommandArgumentType.REQUIRED) {
            if (argValue == null || argValue.equals("")) {
                throw new InvalidCommandException(
                        String.format("%s is required but not present.", this == NONE ? name : toString()));
            }
        } else if (type == DukeCommandArgumentType.NOT_ALLOWED) {
            if (argValue != null && !argValue.equals("")) {
                throw new InvalidCommandException(
                        String.format("%s \"%s\" is not allowed but present.", name, argValue));
            }
        }
    }
}
