import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        String logo = line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);
        while(sc.hasNext()){
            String temp = sc.next();
            if(temp.equals("bye")){
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            }
            System.out.println(line + temp + "\n" + line);
        }
    }
}
