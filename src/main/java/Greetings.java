public class Greetings extends Responses{

    public static void chat() {
        String uResponse = interact("\tHello! I'm Duke \n\tWhat can I do for you?\n");
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
