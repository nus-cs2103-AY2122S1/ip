import java.util.Scanner;

public class Duke {
    public static String[] tasksList = new String[100];

    public static void addTask(String task, int index) {
        tasksList[index] = task;
        System.out.println("added: " + task);
    }

    public static void listTask() {
        for (int i = 0; i < tasksList.length; i++) {
            if (tasksList[i] == null) break;
            System.out.println(i + ". " +tasksList[i]);
        }
    }
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from Neko!\nWhat can I do for you?\n(Nothing actually cos I will just repeat what you say!)\n");
        Scanner sc = new Scanner(System.in);  
        
        int index = 0;
        
        while (sc.hasNext()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sc.close();
                break;
            }
            if (userInput.equals("list")) {
                Duke.listTask();
            } else {
                Duke.addTask(userInput, index);
                index++;
            }
            
            
        }
        
        System.out.println("Bye! Neko wishes to see you again soon!");
        
    }
}
