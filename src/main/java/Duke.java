public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    public static final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

    private static Processor processor;

    public static void main(String[] args) {
        // Start the program and greet the user.
        System.out.println(Duke.GREETING_MESSAGE);
        // Process the commands received.
        Duke.processor = new Processor();
        while (processor.process()) {
            System.out.println(processor);
            Duke.processor = new Processor();
        }
        // End the program.
        System.out.println(processor);
    }
}
