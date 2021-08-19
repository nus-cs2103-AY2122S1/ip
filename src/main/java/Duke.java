import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws IllegalTaskException {
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
        String input = sc.nextLine();
        String command = input.split(" ")[0];
        while(!command.equals("bye")){
            try{ 
                if(command.equals("list")){
                    System.out.println("Here are the tasks in your list: ");
                    for(int i = 1; i < index; i++){
                        System.out.println(i + ". " + list[i]);
                    }
                } else if (command.equals("done")) {
                    int toComplete = Integer.parseInt(input.split(" ")[1]);
                    list[toComplete].complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(String.format("  %s", list[toComplete]));
                } else if(command.equals("todo")){
                    String task = input.replaceFirst("todo ","");
                    if (task.equals("todo")){
                        throw new IllegalTaskException();
                    } else { 
                        ToDo toDo = new ToDo(task);
                        list[index] = toDo;
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(String.format("  %s", toDo.toString()));
                        System.out.println(String.format("Now you have %d tasks in the list", index));
                        index++;
                    }
                } else if (command.equals("deadline")) {
                    String[] taskDate = input.replaceFirst("deadline ", "").split("/by ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    Deadline deadline = new Deadline(task, date);
                    list[index] = deadline;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", deadline.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", index));
                    index++;
                } else if (command.equals("event")) {
                    String[] taskDate = input.replaceFirst("event ", "").split("/at ");
                    String task = taskDate[0];
                    String date = taskDate[1];
                    Event event = new Event(task, date);
                    list[index] = event;
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(String.format("  %s", event.toString()));
                    System.out.println(String.format("Now you have %d tasks in the list", index));
                    index++;
                } else {
                    throw new IllegalCommandException();
                }
            } catch (IllegalCommandException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IllegalTaskException e){
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            input = sc.nextLine();
            command = input.split(" ")[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
