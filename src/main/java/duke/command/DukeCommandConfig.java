package duke.command;

import duke.exception.InvalidCommandException;

import java.util.Map;

class DukeCommandConfig {
    static final DukeCommandConfig NO_ARGUMENTS = new DukeCommandConfig(DukeCommandArgument.NONE, Map.of());

    final DukeCommandArgument positionalArg;
    final Map<String, DukeCommandArgument> acceptedNamedArgs;

    DukeCommandConfig(DukeCommandArgument positionalArg, Map<String, DukeCommandArgument> acceptedNamedArgs) {
        this.positionalArg = positionalArg;
        this.acceptedNamedArgs = acceptedNamedArgs;
    }

    /**
     * Asserts that the given positional and named arguments are compatible with the configuration.
     * @param arg the positional argument
     * @param namedArgs the named arguments
     * @throws InvalidCommandException if given arguments are incompatible
     */
    void assertCompatibilityWith(String arg, Map<String, String> namedArgs) throws InvalidCommandException {
        positionalArg.assertCompatibilityWith(arg);
        for (Map.Entry<String, String> entry : namedArgs.entrySet()) {
            String argName = entry.getKey();
            String argValue = entry.getValue();
            if (!acceptedNamedArgs.containsKey(argName)) {
                throw new InvalidCommandException(String.format("Unexpected named argument \"%s\" with value \"%s\".", argName, argValue));
            }
            acceptedNamedArgs.get(argName).assertCompatibilityWith(argValue);
        }
    }
}
