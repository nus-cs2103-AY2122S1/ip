public class InputHandler {
    private final String welcome = "Hello! I'm Duke\n What can I do for you?";
    private final String bye = "bye";
    private final String termination = "Bye. Hope to see you again soon!";
    private String borders;

    public InputHandler(int borderLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < borderLength; i++) {
            sb.append('_');
        }
        borders = sb.toString();
        System.out.println(formatReply(welcome));
    }

    public boolean query(String input) {
        boolean terminate = input.equals(bye);
        String output = terminate ? termination : input;
        System.out.println(formatReply(output));
        return !terminate;
    }

    private String formatReply(String input) {
        return borders + "\n " + input + '\n' + borders;
    }
}
