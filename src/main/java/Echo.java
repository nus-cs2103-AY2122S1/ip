public class Echo extends Responses{

    private static void appendList(String uResponse) {
        Responses.list[Responses.listLength] = uResponse;
        Responses.listLength += 1;
    }

    public static void chat(String dResponse) {
        appendList(dResponse);
        String uResponse = interact(String.format("\tadded: %s\n", dResponse));
        if (uResponse.equals("bye")) {
            Exit.chat();
        }
        else if (uResponse.equals("list")) {
            List.chat();
        }
        else {
            Echo.chat(uResponse);
        }
    }

}
