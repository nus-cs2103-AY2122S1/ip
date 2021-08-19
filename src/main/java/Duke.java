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
        List<String> stored = new ArrayList<>();


        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                running = false;
            } else if (input.equals("list")){
                for (int i = 0; i < stored.size(); i++){
                    System.out.println((i+1) + ". " + stored.get(i));
                }
            } else {
                stored.add(input);
                String wrapped = horizontal + "added: " + input + "\n" + horizontal;
                System.out.println(wrapped);
            }
        }
        sc.close();
        System.out.println(exitText);
    }
}
