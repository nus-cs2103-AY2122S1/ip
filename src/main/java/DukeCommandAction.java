import java.util.Map;

@FunctionalInterface
interface DukeCommandAction {
    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     *
     * @param duke      The Duke object which the command uses to execute its commands.
     * @param arg       The positional argument to the command.
     * @param namedArgs The named arguments to the command.
     * @return If Duke should continue listening to commands.
     */
    boolean apply(Duke duke, String arg, Map<String, String> namedArgs) throws InvalidCommandException;
}