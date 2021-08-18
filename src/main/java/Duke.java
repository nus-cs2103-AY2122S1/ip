import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        int count = 0;
        boolean exit = false;
        ArrayList<Task> arrList = new ArrayList<>();
        String line = "____________________________________________________________\n";
        String logo = line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);

        while(sc.hasNext()){
            String command = sc.next();
            switch(command){
                case "bye":
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    exit = true;
                    break;
                case "list":
                    String list = "";
                    int listNum = 1;
                    for(Task x:arrList){
                        list += listNum + "." + x +"\n";
                        listNum++;
                    }
                    System.out.println(line + list + line);
                    break;
                case "done":
                    int doneNum = sc.nextInt() - 1;
                    arrList.get(doneNum).markAsDone();
                    System.out.println(line + "Nice! I've marked this task as done:\n" + arrList.get(doneNum));
                    break;
                case "delete":
                    int delNum = sc.nextInt()-1;
                    Task delete = arrList.get(delNum);
                    count--;
                    System.out.println(line + "Noted. I've removed this task:\n" + delete +
                            "\nNow you have " + count + " tasks in the list.\n" + line);
                    arrList.remove(delNum);
                    break;
                case "todo":
                    try {
                        Task todo = new Todo(sc.nextLine().trim(), count);
                        arrList.add(todo);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + todo +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a todo cannot be empty.\n" + line);
                        break;
                    }
                case "deadline":
                    try {
                        String[] deadlineArr = sc.nextLine().split("/by");
                        Task deadline = new Deadline(deadlineArr[0].trim(), deadlineArr[1].trim(), count);
                        arrList.add(deadline);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + deadline +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a deadline cannot be empty.\n" + line);
                        break;
                    }
                case "event":
                    try {
                        String[] eventArr = sc.nextLine().split("/at");
                        Task event = new Event(eventArr[0].trim(), eventArr[1].trim(), count);
                        arrList.add(event);
                        count++;
                        System.out.println(line + "Got it. I've added this task:\n" + event +
                                "\nNow you have " + count + " tasks in the list.\n" + line);
                        break;
                    } catch (Exception e){
                        System.out.println("\n" + line +
                                "\n☹ OOPS!!! The description of a event cannot be empty.\n" + line);
                        break;
                    }
                default:
                    System.out.println("\n" + line +
                            "\n☹ OOPS!!! I'm sorry, but I don't know what that means :-(.\n" + line);

            }
            if(exit){
                break;
            }
        }
    }
}
