import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Neko!\nWhat can I do for you?\n(Nothing actually cos I will just repeat what you say!)\n");
        Scanner sc = new Scanner(System.in);  
        
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            System.out.println(userInput);
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!");
        
    }
}
