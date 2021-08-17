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
        String[] tasksList = new String[100];
        int index = 0;
        
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            if (userInput.equals("list")) {
                for (int i = 0; i < tasksList.length; i++) {
                    if (tasksList[i] == null) break;
                    System.out.println(i + ". " +tasksList[i]);
                }
            } else {
                tasksList[index] = userInput;
                System.out.println("added: " + userInput);
                index++;
            }
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!");
        
    }
}
