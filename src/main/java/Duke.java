import java.util.*;

class Client {
    Scanner scan = new Scanner(System.in);
    public String input() {
        if (scan.hasNextLine()) {
            String str = scan.nextLine();
            return str;
        }
        return "";
    }
}

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! This is Duke, your very own chat bot.");
        System.out.println("What can I help you with ?");
        Client client = new Client();
        ArrayList<Task> list = new ArrayList<Task>();
        while (true) {
            String input = client.input();
            if (input.equals("")) {
                break;
            }
            Task taskInput = new Task(input);
            if (input.equals("bye")) {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            } else if (input.equals("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + "." + list.get(i).toString()); //".[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                    }
                } else if (input.startsWith("done") && input.length() < 9) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                list.get(value - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] " + list.get(value-1).description);
            } else if (input.startsWith("todo")) {
                String firstTodo = input.substring(5);
                list.add(new Todo(firstTodo));
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Todo(firstTodo));
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if (input.startsWith("deadline")) {
                String[] temp = input.split("/");
                String firstDeadline = temp[0].substring(9);
                String secondDeadline = temp[1].substring(3);
                list.add(new Deadline(firstDeadline,secondDeadline));
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Deadline(firstDeadline,secondDeadline));
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else if (input.startsWith("event")) {
                String[] tempEvent = input.split("/");
                String firstEvent = tempEvent[0].substring(6);
                String secondEvent = tempEvent[1].substring(3);
                list.add(new Event(firstEvent,secondEvent));
                System.out.println("Got it. I've added this task: ");
                System.out.println(new Event(firstEvent,secondEvent));
                System.out.println("Now you have " + list.size() + " tasks in the list");
            } else {
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                }
        }

    }
}
