public class Echo extends Responses{

    private static String appendList(String uResponse) {
        Task t;
        if (uResponse.startsWith("todo")) {
            t = new Todo(uResponse.replaceAll("todo ", ""));
        } else if (uResponse.startsWith("deadline")) {
            String[] split = uResponse.replaceAll("deadline ", "").split(" /by ");
            t = new Deadline(split[0], split[1]);
        } else if (uResponse.startsWith("event")) {
            String[] split = uResponse.replaceAll("event", "").split(" /at ");
            t = new Event(split[0], split[1]);
        } else {
            t = new Task(uResponse);
        }
        Responses.list[Responses.currLength] = t;
        Responses.currLength += 1;
        return String.format("\tGot it. I've added this task:\n\t %s\n\tNow you have %d tasks in the list.\n", t.toString(), Responses.currLength);
    }

    public static void chat(String dResponse) {
        String msg = appendList(dResponse);
        Responses.interact(msg);
    }

}
