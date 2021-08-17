import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] list = new String[100];
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
                for(int i = 1; i < index; i++){
                    System.out.println(i + ". " + list[i]);
                }
            } else {
                System.out.println("added: " + command);
                list[index] = command;
                index++;
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
