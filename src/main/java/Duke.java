import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        List list = new List();
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        start();
    }

    public static void start() {
        List l = new List();

        System.out.println(
                "Yo! Duke here \n"
                + "What did you call me for? \n"
                + "It better be something useful or else... \n"
        );

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < l.getLength(); i ++) {
                    System.out.printf("%d. %s \n", i + 1, l.getItemAtIndex(i));
                }
                input = s.nextLine();
            } else {
                l.addItem(input);
                System.out.println("added: " + input);
                input = s.nextLine();
            }
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }
}
