public class DukeCommandArgument {
    static final DukeCommandArgument NONE = new DukeCommandArgument("Positional argument", "", DukeCommandArgumentType.NOT_ALLOWED);

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

    public String toDetailedString() {
        return String.format("%s - %s (%s)", toString(), description, type.toString());
    }

    void assertCompatibilityWith(String argValue) throws InvalidCommandException {
        if (type == DukeCommandArgumentType.REQUIRED) {
            if (argValue == null || argValue.equals("")) {
                throw new InvalidCommandException(String.format("%s is required but not present.", this == NONE ? name : toString()));
            }
        } else if (type == DukeCommandArgumentType.NOT_ALLOWED) {
            if (argValue != null && !argValue.equals("")) {
                throw new InvalidCommandException(String.format("%s \"%s\" is not allowed but present.", name, argValue));
            }
        }
    }
}
