public class Duke {
    private static final String GREETING_MESSAGE = "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    public static final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println(Duke.GREETING_MESSAGE);
        Processor processor = new Processor();
        processor.process();
        System.out.println(processor);
    }
}
