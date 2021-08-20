public class Parser {
    public static Keyword parseChat(String message) throws DukeException {
        String command = message.split(" ")[0].toLowerCase();

        if (command.equals(Keyword.EXIT.getKeyword()))
            return Keyword.EXIT;
        else if (command.equals(Keyword.LIST.getKeyword()))
            return Keyword.LIST;
        else if (command.equals(Keyword.DONE.getKeyword()))
            return Keyword.DONE;
        else if (command.equals(Keyword.DEADLINE.getKeyword()))
            return Keyword.DEADLINE;
        else if (command.equals(Keyword.EVENTS.getKeyword()))
            return Keyword.EVENTS;
        else if (command.equals(Keyword.TODOS.getKeyword()))
            return Keyword.TODOS;
        else if (command.equals(Keyword.DELETE.getKeyword()))
            return Keyword.DELETE;

        throw new DukeException("Command not parserable");
    }
}
