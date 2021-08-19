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
            } else if (input.startsWith("todo")) {
                Scanner s = new Scanner(input);
                s.next(); //jump keyword
                String todoName = s.nextLine();
                Todo todo = new Todo(todoName);
                stored.add(todo);
                System.out.println("Ok~ I've added the task:\n" + todo.toString());
            } else if (input.startsWith("deadline")) {
                Scanner s = new Scanner(input);
                s.next(); //jump keyword
                String ddlName = "";
                String time = "";
                while (s.hasNext()) {
                    String temp = s.next();
                    if (temp.equals("/by")) {
                        time = s.nextLine();
                    } else {
                        ddlName += temp+ " ";
                    }
                }
                Deadline ddl = new Deadline(ddlName, time);
                stored.add(ddl);
                System.out.println("Ok~ I've added the task:\n" + ddl.toString());
            } else if (input.startsWith("event")) {
                Scanner s = new Scanner(input);
                s.next(); //jump keyword
                String eventName = "";
                String time = "";
                while (s.hasNext()) {
                    String temp = s.next();
                    if (temp.equals("/at")) {
                        time = s.nextLine();
                    } else {
                        eventName += temp + " ";
                    }
                }
                Event event = new Event(eventName, time);
                stored.add(event);
                System.out.println("Ok~ I've added the task:\n" + event.toString());
            } else {
                stored.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println(horizontal);


        }

        sc.close();
    }
}
