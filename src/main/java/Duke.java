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
        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String input = client.input();
            if (input.equals("bye")) {
                System.out.println("It's sad to see you go :(");
                System.out.println("Goodbye, hope to you another day!");
                break;
            } else if (input.equals("list")) {
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                } else {
                    list.add(input);
                    System.out.println("added: " + input);
                }
        }
    }
}
