package BobCat.executor.parser;

import BobCat.exception.CommandArityException;
import BobCat.exception.InvalidArgumentException;
import BobCat.exception.UnknownCommandException;

import java.util.Set;

/**
 * A parser object which process queries. Queries are grouped based on its behavior on <code>TaskList</code> i.e.
 * creation, mutation, or read-only. QueryParser will call the appropriate <code>CommandParser</code> based on these
 * sets of commands.
 */
public class QueryParser {
    private static final Set<String> taskCreation = Set.of("todo", "event", "deadline");
    private static final Set<String> taskMarking = Set.of("done", "delete");
    private static final Set<String> basicCommand = Set.of("list", "bye");

    BasicCommandParser basicCommandParser = new BasicCommandParser();
    CreationCommandParser taskCreationParser = new CreationCommandParser();
    MutationCommandParser taskMarkingParser = new MutationCommandParser();

    /**
     * Process user query and call the appropriate <code>CommandParser</code>.
     * @param query The given user query
     * @return Array of strings, where the 0th index is occupied by command, followed by the arguments to the command as
     * understood by the parser
     * @throws CommandArityException May be thrown if number of elements in commandArgs is not appropriate relative to
     * given command, according to the relevant <code>CommandParser</code>
     * @throws InvalidArgumentException May be thrown if elements of commandArgs are not castable to integer,
     * according to the relevant <code>CommandParser</code>
     * @throws UnknownCommandException May be thrown if given query begins with an unrecognised command
     */
    public String[] parse(String query) {
        String[] queryArr = query.split("\\s");
        String command = queryArr[0];
        if (basicCommand.contains(command)) {
            return basicCommandParser.parse(command, new String[0]);
        } else if (taskMarking.contains(command)){
            return taskMarkingParser.parse(command, queryArr);
        } else if (taskCreation.contains(command)) {
            return taskCreationParser.parse(command, queryArr);
        }
        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
    }
}
