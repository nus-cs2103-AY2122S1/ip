package parser;

import exception.UnknownCommandException;
import java.util.Set;

public class QueryParser {
    private static final Set<String> taskCreation = Set.of("todo", "event", "deadline");
    private static final Set<String> taskMarking = Set.of("done", "delete");
    private static final Set<String> basicCommand = Set.of("list", "bye");

    BasicCommandParser basicCommandParser = new BasicCommandParser();
    CreationCommandParser taskCreationParser = new CreationCommandParser();
    MutationCommandParser taskMarkingParser = new MutationCommandParser();

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
        throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
