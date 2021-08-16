public class Response {
    private final String input;

    Response(String input) {
        this.input = input;
    }

    String Echo() {
        if (input.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }
        return input + "\n";
    }
}
