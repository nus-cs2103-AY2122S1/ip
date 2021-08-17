import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Duke {


    static String line = "____________________________________________________________";

    static List<Task> list = new ArrayList<Task>();

    public static void HelloMessage() {
        String Hello_message = "Hello! I'm Duke\n" +
                                "What can I do for you?\n";

        System.out.println(line + "\n" + Hello_message + line + "\n");
    }

    public static void PrintList() {
        int index = 0;

        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            index++;
            System.out.println(index + "." + list.get(i).PrintTaskInfo());
        }
    }

    public static void HandleTask(String Message) throws DukeException{
        String task = "";
        String deadline = "";

        //If the task type does not belong to the three types, throw an error.
        if (!(Message.startsWith("todo") || Message.startsWith("event") || Message.startsWith("deadline"))){
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        //Get Task description and time if it has it.
        if (Message.indexOf("/") != -1) {
            task = Message.substring(Message.indexOf(" ") + 1, Message.indexOf("/") - 1);

            if (Message.indexOf("/by") != -1) {
                deadline = Message.substring(Message.indexOf("/by") + 3);
            } else if (Message.indexOf("/at") != -1){
                deadline = Message.substring(Message.indexOf("/at") + 3);
            }
        }
        else {
            if (Message.indexOf(" ") == -1) {
                throw new DukeException("☹ OOPS!!! The description of a " + Message +" cannot be empty.");
            }else {
                task = Message.substring(Message.indexOf(" ") + 1);

                deadline = "";
            }
        }

        if ((Message.startsWith("event") || Message.startsWith("deadline")) && deadline.equals("")) {
            throw new DukeException("☹ OOPS!!! The time of a " + Message.substring(0, Message.indexOf(" ")) +" cannot be empty.");
        }


        System.out.println("Got it. I've added this task: ");
        if (Message.startsWith("todo")) {
                Task newTask = new ToDos(false, task);
                System.out.println(" " + newTask.PrintTaskInfo());
                list.add(newTask);
        } else if (Message.startsWith("event")) {
                Task newTask = new Events(false, task, deadline);
                System.out.println(" " + newTask.PrintTaskInfo());
                list.add(newTask);
        } else if (Message.startsWith("deadline")){
                Task newTask = new Deadlines(false, task, deadline);
                System.out.println(" " + newTask.PrintTaskInfo());
                list.add(newTask);
        }

        System.out.println("Now you have " + list.size() + "" +
                " tasks in the list.");
    }

    public static void MarkDone(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("☹ OOPS!!! The index is invalid!!!");
        } else {
            System.out.println("Nice! I've marked this task as done:");
            list.get(index).MarkDone();
            System.out.println(" " + list.get(index).PrintTaskInfo());
        }
    }

    public static void Delete(int index) throws DukeException{
        if (index < 0) {
            throw new DukeException("☹ OOPS!!! The index is invalid!!!");
        } else {
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + list.get(index).PrintTaskInfo());
            list.remove(index);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        }
    }


    public static void PrintMessage() {
        Scanner scanner = new Scanner(System.in);
        String Message = "";
        String Goodbye_message = "Bye. Hope to see you again soon!";

        //Use loop to determine if a user enters "bye" or not.
        while (true) {
            Message = scanner.nextLine();
            System.out.println(line);
            if (Message.equals("bye")) {
                System.out.println(Goodbye_message);
                System.out.println(line + "\n");
                break;
            }
            else if (Message.equals("list")){
                PrintList();
            }
            else if (Message.startsWith("done")) {
                int index = Message.charAt(Message.length() - 1) - 49;

                try {
                    MarkDone(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }

            } else if (Message.startsWith("delete")) {
                int index =  Integer.parseInt(Message.substring(Message.indexOf(" ") + 1)) - 1;

                try {
                    Delete(index);
                } catch (DukeException e){
                    e.PrintErrorMessage();
                }
            }
            else  {
                try {
                    HandleTask(Message);
                } catch (DukeException e)
                {
                    e.PrintErrorMessage();
                }
            }

            System.out.println(line + "\n");
        }
    }




    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

       //Print Hello.
        HelloMessage();

        //Print Message();
        PrintMessage();


    }
}
