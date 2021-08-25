public class Echo extends Responses {

    private static String appendList(String uResponse) throws DukeException {
        Task t;
        String[] uResponseList = uResponse.split(" ");
        switch (uResponseList[0].trim()) {
        case "todo":
            String todoDescription = uResponse.replaceAll("todo", "");
            if (todoDescription.trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            t = new Todo(todoDescription);
            break;
        case "deadline":
            String[] deadlineDescriptions = uResponse.replaceAll("deadline", "").split("/by");
            if (deadlineDescriptions[0].trim().isEmpty() || deadlineDescriptions.length == 1 || deadlineDescriptions[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            t = new Deadline(deadlineDescriptions[0], deadlineDescriptions[1]);
            break;
        case "event":
            String[] eventDescriptions = uResponse.replaceAll("event", "").split("/at");
            if (eventDescriptions[0].trim().isEmpty() || eventDescriptions.length == 1 || eventDescriptions[1].trim().isEmpty()) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            t = new Event(eventDescriptions[0], eventDescriptions[1]);
            break;
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Responses.list.add(t);
        return t.toString();
    }

    public static void chat(String dResponse) throws DukeException {
        String msg = appendList(dResponse);
        Responses.interact(String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %d tasks in the list.\n", msg, Responses.list.size()));
    }
}
