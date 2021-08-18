public class Response {
    private String horizontalLine = "    ____________________________________________________________\n";

    public void greet() {
        System.out.println(horizontalLine +
                "    Hello! I'm LebronChatBot\n" +
                "    What can I do for you?\n" +
                horizontalLine);
    }

    public void echo(String text) {
        System.out.println(horizontalLine + "    " +
                text + "\n" +
                horizontalLine);
    }

    public void exit() {
        System.out.println(horizontalLine +
                "    Bye. Hope to see you again soon!\n" +
                horizontalLine);
    }
}
