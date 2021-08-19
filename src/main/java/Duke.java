import java.util.*;

class Client {
    public String input() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
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
            Task taskInput = new Task(input);
            if (input.equals("bye")) {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            } else if (input.equals("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                    }
                } else if (input.startsWith("done") && input.length() < 9) {
                int value = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                list.get(value - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[X] " + list.get(value-1).description);
            } else {
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                }
        }
    }
}
