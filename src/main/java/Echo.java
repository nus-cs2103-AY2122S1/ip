public class Echo extends Responses{

    private static String appendList(String uResponse) throws DukeException {
        Task t;
        if (uResponse.startsWith("todo")) {
            String description = uResponse.replaceAll("todo", "");
            if (description.trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            t = new Todo(description);
        } else if (uResponse.startsWith("deadline")) {
            String[] split = uResponse.replaceAll("deadline", "").split("/by");
            if (split[0].trim().isEmpty() || split.length == 1 || split[1].trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            t = new Deadline(split[0], split[1]);
        } else if (uResponse.startsWith("event")) {
            String[] split = uResponse.replaceAll("event", "").split("/at");
            if (split[0].trim().isEmpty() || split.length == 1 ||split[1].trim().isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            t = new Event(split[0], split[1]);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Responses.list[Responses.currLength] = t;
        Responses.currLength += 1;
        return t.toString();
    }

    public static void chat(String dResponse) throws DukeException {
        String msg = appendList(dResponse);
        Responses.interact(String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %d tasks in the list.\n", msg, Responses.currLength));
    }

}
