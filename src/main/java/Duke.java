import java.util.*;

public class Duke {


    public static void main(String[] args) {
        boolean running = true;
        String horizontal = "____________________\n";
        String exitText = horizontal + "Bye!\n" + horizontal;

        String logo =
              "                     _               _         \n" +
                      "                    | |             | |        \n" +
                      "  _ __ ___     ___  | |   ___     __| |  _   _ \n" +
                      " | '_ ` _ \\   / _ \\ | |  / _ \\   / _` | | | | |\n" +
                      " | | | | | | |  __/ | | | (_) | | (_| | | |_| |\n" +
                      " |_| |_| |_|  \\___| |_|  \\___/   \\__,_|  \\__, |\n" +
                      "                                          __/ |\n" +
                      "                                         |___/ ";

        System.out.println("Hello from\n" + logo);


        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                running = false;
                System.out.println(exitText);
            } else {
                String wrapped = horizontal + input + "\n" + horizontal;
                System.out.println(wrapped);
            }
        }
        sc.close();
    }
}
