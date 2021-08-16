public class Duke {
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println("Hi, I'm Duke, your Personal Assistant Chatbot\n" +
                "What can I do for you today?");
        System.out.println(LINE);
    }
}
