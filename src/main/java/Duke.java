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
            }else if(input.length()>=4 && input.substring(0, 4).equals("todo")){
                ToDos todo = new ToDos(input.substring(5));
                sl.add(todo);
                linesToPrint(todo.toString(), sl.size());
            }else if(input.length()>=8 && input.substring(0, 8).equals("deadline")) {
                String by = input.substring(input.indexOf("/")+4);
                Deadlines dl = new Deadlines(input.substring(9, input.indexOf("/")), by);
                sl.add(dl);
                linesToPrint(dl.toString(), sl.size());
            }else if(input.length()>=5 && input.substring(0, 5).equals("event")) {
                String at = input.substring(input.indexOf("/")+4);
                Events event = new Events(input.substring(6,  input.indexOf("/")), at);
                sl.add(event);
                linesToPrint(event.toString(), sl.size());
            }

            else{
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
    public static void linesToPrint(String task, int size){
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
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
