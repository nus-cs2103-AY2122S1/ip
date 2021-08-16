import java.util.Arrays;
import java.util.Set;

public class ReplyParser {
    private static final Set<String> taskCreation = Set.of("todo", "event", "deadline");
    private static final Set<String> taskMarking = Set.of("done");
    private static final Set<String> basicCommand = Set.of("list", "bye"); //basicCommands don't need argument

    public static String[] parse(String query) {
        String[] queryArr = query.split("\\s");
        String command = queryArr[0];
        if (basicCommand.contains(command)) {
            return new String[]{command};
        } else if (taskMarking.contains(command)){
            return new String[]{command, String.valueOf(Integer.parseInt(queryArr[1]) - 1)};
        } else if (taskCreation.contains(command)) {
            if (command.equals("todo")) {
                String commandArg = String.join(" ",
                                                Arrays.copyOfRange(queryArr, 1, queryArr.length));
                return new String[]{command, commandArg};
            } else {
                String args = String.join(" ",
                        Arrays.copyOfRange(queryArr, 1, queryArr.length));
                String[] argArr = args.split("\\s(/by|/at)");
                return new String[]{command, argArr[0].trim(), argArr[1].trim()};
            }
        }
        throw new IllegalArgumentException("Unsupported Command!");
    }
}
