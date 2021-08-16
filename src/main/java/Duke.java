import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int index = 0;
        Integer count = 1;
        boolean exit = false;
        Task[] arr = new Task[100];
        String line = "____________________________________________________________\n";
        String logo = line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);

        while(sc.hasNext()){
            String temp = sc.next();
            switch(temp){
                case "bye":
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    exit = true;
                    break;
                case "list":
                    String list = "";
                    for(int i =0;i<index;i++){
                        list += arr[i];
                    }
                    System.out.println(line + list + line);
                    break;
                case "done":
                    int num = sc.nextInt() - 1;
                    arr[num].markAsDone();
                    System.out.println(line + "Nice! I've marked this task as done:\n" + arr[num]);
                    break;
                default:
                    Task t = new Task(temp + " " + sc.nextLine().trim(),count);
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
