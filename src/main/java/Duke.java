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
            if(input.length()>=4 && input.substring(0, 4).equals("done")){
                marking(input);
            }else{
                Task task = new Task(input);
                String desc = task.getDescription();
                switch(desc){
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        sl.display();
                        break;
                    default:
                        sl.add(task);
                        System.out.println("added: " + desc);
                        break;
                }
            }

        }

    }

    public static void marking(String input){
        if(input.length()>=6){
            if(input.substring(5).matches("[0-9]+")){
                int taskNum = Integer.parseInt(input.substring(5)) - 1;
                if(taskNum<sl.size() && taskNum>=0){
                    sl.get(taskNum).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    sl.get(taskNum).displayTask();
                }else{
                    System.out.println("Error");
                }
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.println("Error");
        }
    }
}
