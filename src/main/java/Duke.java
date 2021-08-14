import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n"
        + "Heyllo! Jack here\n"
        + "What can I do for you?\n"
        + "____________________________________________________________\n");

        boolean bye = false;
        Scanner myObj = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();


        while (!bye) {
            String temp = myObj.nextLine();
            if (temp.equals("bye")) {
                System.out.println("____________________________________________________________\n"
                        + "Bye bye. Love you\n"
                        + "____________________________________________________________\n");
                bye = true;
            } else if (temp.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < taskList.size(); i++) {
                    String entry = String.format("%d. %s", i+1, taskList.get(i));
                    System.out.println(entry);
                }
                System.out.println("____________________________________________________________\n");
            } else {
                System.out.println("____________________________________________________________\n"
                        + "added: " + temp
                        + "\n"
                        + "____________________________________________________________\n");
                taskList.add(temp);
            }

        }
    }
}
