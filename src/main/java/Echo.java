public class Echo extends Responses{

    public static void chat(String dResponse) {
        String uResponse = interact(String.format("\t%s\n", dResponse));
        if (uResponse.equals("bye")) {
            Exit.chat();
        }
        else {
            Echo.chat(uResponse);
        }
    }

}
