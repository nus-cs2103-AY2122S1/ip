import java.util.*;

public class Duke {

    public static void main(String[] args) {
        boolean running = true;
        String horizontal = "_______________________";
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
        List<Task> stored = new ArrayList<>();


        Scanner sc = new Scanner(System.in);
        while (running) {
            String input = sc.nextLine();
            System.out.println(horizontal);

            if (input.equals("bye")) {
                System.out.println("Byebye ~ nya");
                running = false;
            } else if (input.equals("list")){
                System.out.println("Here are your tasks ~ OwO");
                for (int i = 0; i < stored.size(); i++){
                    System.out.println((i+1) + ". " + stored.get(i).toString());
                }
            } else if (input.startsWith("done")) {
                Scanner s = new Scanner(input);
                s.next(); //jump 'done'
                int num = s.nextInt();
                stored.get(num-1).markAsDone();
            } else {
                stored.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println(horizontal);


        }

        sc.close();
        //System.out.println(exitText);
    }
}
