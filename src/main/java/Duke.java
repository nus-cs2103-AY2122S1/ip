import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Task [] t = new Task[100];
        int ctr = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("_______________________________________________");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("_______________________________________________");

        while (scanner.hasNext()) {
           String response = scanner.nextLine();
            if (response.equals("list")) {
                System.out.println("_______________________________________________");
                System.out.println("Here are the tasks on your list: ");
                for (int i = 0; i < ctr; i++) {
                    System.out.println( (i + 1) + ". " + t[i] );
                }
                System.out.println("_______________________________________________");
            } else if (response.equals("bye")) {
                System.out.println("_______________________________________________");
                System.out.println("Bye! Hope to see you again soon!");
                System.out.println("_______________________________________________");
                System.exit(0);
            } else if(response.contains("done")){
                String[]str = response.split(" ");
                String task = str[0];
                int num = Integer.parseInt(str[1]);
                t[num - 1].markAsDone();
                System.out.println("Nice! I've marked this task as done: \n" + t[num - 1].toString());
                System.out.println("_______________________________________________");
            }
            else if(response.contains("todo")){
                String[]str = response.split(" ");
                String task = str[0];
                String command = str[1];
                command = response.substring(response.indexOf(" "));
                Task td = new Todo(command);
                t[ctr] = td;
                System.out.println("Got it! I've added this task: \n" + td.toString());
                ctr++;
                System.out.println("Now you have " + ctr + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else if(response.contains("deadline")){
                int tLabelFirst = response.indexOf(" ") + 1;
                int tTimeFirst = response.indexOf("/");
                String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
                String tTime = response.substring(tTimeFirst + 4);
                Task td = new Deadline(tLabel, tTime);
                t[ctr] = td;
                System.out.println("Got it! I've added this task: \n" + td.toString());
                ctr++;
                System.out.println("Now you have " + ctr + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else if(response.contains("event")){
                int tLabelFirst = response.indexOf(" ") + 1;
                int tTimeFirst = response.indexOf("/");
                String tLabel = response.substring(tLabelFirst, tTimeFirst - 1);
                String tTime = response.substring(tTimeFirst + 4);
                Task td = new Event(tLabel, tTime);
                t[ctr] = td;
                System.out.println("Got it! I've added this task: \n" + td.toString());
                ctr++;
                System.out.println("Now you have " + ctr + " tasks in the list.");
                System.out.println("_______________________________________________");
            }
            else {
                System.out.println("_______________________________________________");
                System.out.println("added: " + response);
                System.out.println("_______________________________________________");
                t[ctr]  = new Task(response);
                ctr++;
            }
        }
    }
}
