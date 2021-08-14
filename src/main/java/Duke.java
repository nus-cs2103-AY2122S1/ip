public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        run();

    }

    public static void run(){
        System.out.println(great());
    }


    public static String great(){
        String greatSent = "\n" +
        "____________________________________________________________\n" +
        "Hello! I'm Duke\n" +
        "What can I do for you? \n" +
        "____________________________________________________________";

        return greatSent;
    }    
}
