import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int index = 0,count = 1;
        boolean exit = false;
        Task[] arr = new Task[100];
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
                    for(int i =0;i<index;i++){
                        Task t = arr[i];
                        list += t.number + "." + t +"\n";
                    }
                    System.out.println(line + list + line);
                    break;
                case "done":
                    int num = sc.nextInt() - 1;
                    arr[num].markAsDone();
                    System.out.println(line + "Nice! I've marked this task as done:\n" + arr[num]);
                    break;
                case "todo":
                    Task todo = new Todo(sc.nextLine().trim(),count);
                    arr[index] = todo;
                    System.out.println(line + "Got it. I've added this task:\n" + todo +
                            "\nNow you have " + count +" tasks in the list.\n" + line);
                    index++;
                    count++;
                    break;
                case "deadline":
                    String[] deadlineArr = sc.nextLine().split("/by");
                    Task deadline = new Deadline(deadlineArr[0].trim(),deadlineArr[1].trim(),count);
                    arr[index] = deadline;
                    System.out.println(line + "Got it. I've added this task:\n" + deadline +
                            "\nNow you have " + count +" tasks in the list.\n" + line);
                    index++;
                    count++;
                    break;
                case "event":
                    String[] eventArr = sc.nextLine().split("/at");
                    Task event = new Event(eventArr[0].trim(),eventArr[1].trim(),count);
                    arr[index] = event;
                    System.out.println(line + "Got it. I've added this task:\n" + event +
                            "\nNow you have " + count +" tasks in the list.\n" + line);
                    index++;
                    count++;
                    break;
                default:
                    Task t = new Task(command + " " + sc.nextLine().trim(),count);
                    arr[index] = t;
                    System.out.println(line + "added: " + t.description + "\n" + line);
                    index++;
                    count++;
                    break;
            }
            if(exit){
                break;
            }
        }
    }
}
