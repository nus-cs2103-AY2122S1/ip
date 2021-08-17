import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int index = 1;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                for(int i = 1; i < index; i++){
                    System.out.println(i + ". " + list[i]);
                }
            } else if (command.split(" ")[0].equals("done")) {
                int toComplete = Integer.parseInt(command.split(" ")[1]);
                list[toComplete].complete();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[toComplete]);
            } else {
                System.out.println("added: " + command);
                Task toAdd = new Task(command);
                list[index] = toAdd;
                index++;
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
