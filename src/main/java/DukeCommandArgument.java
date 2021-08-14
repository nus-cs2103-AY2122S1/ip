public class DukeCommandArgument {
    static final DukeCommandArgument NONE = new DukeCommandArgument("Positional argument", DukeCommandArgumentType.NOT_ALLOWED);

    public String getName() {
        return name;
    }

    private String name;
    private DukeCommandArgumentType type;

    DukeCommandArgument(String name, DukeCommandArgumentType type) {
        this.name = name;
        this.type = type;
    }

    void assertCompatibilityWith(String argValue) throws InvalidCommandException {
        if (type == DukeCommandArgumentType.REQUIRED) {
            if (argValue == null || argValue.equals("")) {
                throw new InvalidCommandException(String.format("%s is required but not present.", name));
            }
        } else if (type == DukeCommandArgumentType.NOT_ALLOWED) {
            if (argValue != null && !argValue.equals("")) {
                throw new InvalidCommandException(String.format("%s \"%s\" is not allowed but present.", name, argValue));
            }
        }
    }
}
