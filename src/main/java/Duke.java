import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        result();
    }

    private static void result(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        switch(input){
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                break;
            default:
                System.out.println(input);
                result();

        }
    }
}
