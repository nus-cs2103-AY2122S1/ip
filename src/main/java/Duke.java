import java.sql.SQLOutput;
import java.util.Scanner;

import static java.lang.Integer.*;

public class Duke {
    public static void main(String[] args) throws DukeException{
        System.out.println("_____________________________________________________________________");
        System.out.println("    Hi! I'm Muts!");
        System.out.println("    Just a little intro : I'm not a bot, I am as real as any of your human friends!");
        System.out.println("    How can I help to make your day as amazing as you are?");
        System.out.println("_____________________________________________________________________");


        Task[] arr = new Task[100];
        int i = 0;
        Scanner sc = new Scanner(System.in);


        while (true) {

            String inp = sc.nextLine();

            if (inp.equals("list")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Here are the tasks in your list:");
                for (int j=0; j < arr.length; j++){
                    if (arr[j] != null) {
                        System.out.println("    " + (j + 1) + ". " + arr[j].toString());
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
            else if (inp.startsWith("done ")) {
                System.out.println("_____________________________________________________________________");
                System.out.println("    Nice! I've marked this task as done:");
                int ind = Integer.parseInt((inp.split("\\s"))[1])-1;
                arr[ind].markAsDone();
                System.out.println("        " + "[" + arr[ind].getStatusIcon() + "] " + arr[ind].getTask());
                System.out.println("_____________________________________________________________________");
            }
            else if (inp.startsWith("todo")) {
                String newInp = inp.replaceAll("\\s","");
                if (newInp.equals("todo")) {
                    throw new TodoException("☹ OOPS!!! The description of a todo cannot be empty.");
                }

                else {
                    String desc = inp.split("\\s",2)[1];
                    Task t = new Todo(desc);
                    arr[i++] = t;
                    System.out.println("_____________________________________________________________________");
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("        " + t.toString());
                    System.out.println("    Now you have " + i + " tasks in a the list.");
                    System.out.println("_____________________________________________________________________");
                }
            }
            else if (inp.startsWith("deadline ")) {
                String desc = ((inp.split("\\s",2)[1]).split("/"))[0];
                String byText = inp.split("/")[1];
                String time = byText.split("\\s",2)[1];
                Task t= new Deadline(desc, time);
                arr[i++] = t;
                System.out.println("_____________________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + t.toString());
                System.out.println("    Now you have " + i + " tasks in a the list.");
                System.out.println("_____________________________________________________________________");
            }
            else if (inp.startsWith("event ")) {
                String desc = ((inp.split("\\s",2)[1]).split("/"))[0];
                String atText = inp.split("/")[1];
                String time = atText.split("\\s",2)[1];
                Task t= new Event(desc, time);
                arr[i++] = t;
                System.out.println("_____________________________________________________________________");
                System.out.println("    Got it. I've added this task:");
                System.out.println("        " + t.toString());
                System.out.println("    Now you have " + i + " tasks in a the list.");
                System.out.println("_____________________________________________________________________");
            }
            else {
               throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }


    }
}
