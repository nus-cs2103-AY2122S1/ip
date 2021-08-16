public class List extends Responses{

    private static String printList() {
        String response = "";
        for (int i = 0; i < Responses.listLength; i++) {
            response += String.format("\t%d. %s\n", i + 1, Responses.list[i]);
        }
        return response;
    }

    public static void chat() {
        String uResponse = interact(printList());
        if (uResponse.equals("bye")) {
            Exit.chat();
        } else if (uResponse.equals("list")) {
            List.chat();
        }
        else {
            Echo.chat(uResponse);
        }
    }

}
