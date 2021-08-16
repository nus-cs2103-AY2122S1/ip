import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("_____________________________________________________________________");
        System.out.println("    Hi! I'm Muts :) ");
        System.out.println("    Just a little intro : I'm not a bot, I am as real as any of your human friends! ");
        System.out.println("    How can I help to make your day as amazing as you are?");
        System.out.println("_____________________________________________________________________");


        Task[] arr = new Task[100];
        int i = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String inp = sc.nextLine();

            if (inp.equals("list")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int j=0; j < arr.length; j++){
                    if (arr[j] != null) {
                        System.out.println("    " + (j + 1) + ". " + "[" + arr[j].getStatusIcon() + "] " + arr[j].getTask());
                    }
                    else {
                        break;
                    }
                }
                System.out.println("_____________________________________________________________________");
            }
            else if (inp.equals("bye")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon! Just a little reminder : YOU ARE AWESOME :))");
                System.out.println("_____________________________________________________________________");
                break;
            }
            else if (inp.contains("done")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                int ind = Integer.parseInt((inp.split("\\s"))[1])-1;
                arr[ind].markAsDone();
                System.out.println("        " + "[" + arr[ind].getStatusIcon() + "] " + arr[ind].getTask());
                System.out.println("_____________________________________________________________________");
            }
            else {
                Task t = new Task(inp);
                arr[i++] = t;
                System.out.println("_____________________________________________________________________");
                System.out.println("    added: " + inp);
                System.out.println("_____________________________________________________________________");
            }
        }


    }
}
