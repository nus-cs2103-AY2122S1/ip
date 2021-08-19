import java.util.*;
class User {
    public String command() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }
}
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hope you are doing well. How can I help you?");
        User user1 = new User();
        List<Object> userList = new ArrayList<>();
        while(true) {
            String command = user1.command();
            if (command.equals("bye")) {
                System.out.println("   Bye. Have a great day!");
                break;
            } else if (command.equals("list")) {
                int count = 1;
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println("   " + count + ". " + userList.get(i));
                    count++;
                }
            } else {
                userList.add(command);
                System.out.println("   added: " + command);
            }
        }
    }
}

