import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        start();
    }

    public static void start() {
        System.out.println(
                "Hello! I'm Duke \n"
                        + "What can I do for you? \n"
        );

        Scanner s = new Scanner(System.in);
        System.out.println(s.next());
    }

}
