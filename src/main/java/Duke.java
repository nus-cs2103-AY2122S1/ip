import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you? :)");
        Scanner sc = new Scanner(System.in);
        String inpt = sc.nextLine();
        while(!inpt.equals("bye")){
            System.out.println(inpt);
            inpt = sc.nextLine();
        }
        System.out.println("Goodbye, I will miss you!");
    }
}
