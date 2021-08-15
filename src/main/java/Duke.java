import java.util.Scanner;

public class Duke {

    private static StorageList sl = new StorageList();

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

        while(true){
            String input = sc.nextLine();
            switch(input){
                case "bye":
                    sl.clear();
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    sl.display();
                    break;
                default:
                    sl.add(input);
                    System.out.println("added: " + input);
                    break;
            }
        }


    }
}
