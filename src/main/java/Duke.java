public class Duke {

    private static String greet() {
        return "Hello from\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?";
    }

    private static String echo(String wordToEcho) {
        return wordToEcho;
    }

    private static String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public static void main(String[] args) {
        System.out.println(greet());

        for(String str : args) {
            System.out.println(str);
            if (str.equals("bye")) {
                System.out.println(sayGoodbye());
                System.exit(0);

            } else {
                System.out.println(echo(str));
            }
        }
    }
}
