import java.util.*;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----------------------------------------");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("-----------------------------------------");
        List<String> tasks = new ArrayList<>();

        int count = 1;
        while(true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println("-----------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------");
                sc.close();
                break;
            }
            if (task.equals("list")) {
                System.out.println("-----------------------------------------");
                tasks.forEach(System.out::println);
            } else {
                tasks.add(count + ". " + task);
                count++;
                System.out.println("-----------------------------------------");
                System.out.println("added: " + task);
            }
            System.out.println("-----------------------------------------");
        }
    }


}
