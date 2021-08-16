import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        int index = 0;
        Integer count = 1;
        String[] arr = new String[100];
        String line = "____________________________________________________________\n";
        String logo = line + " Hello! I'm Duke\n" + " What can I do for you?\n" + line;
        Scanner sc = new Scanner(System.in);
        System.out.println(logo);

        while(sc.hasNextLine()){
            String temp = sc.nextLine();
            if(temp.equals("bye")){
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            }
            else if(temp.equals("list")){
                String list = "";
                for(int i =0;i<index;i++){
                    list += arr[i];
                }
                System.out.println(line + list + line);
            }
            else {
                arr[index] = count.toString() + ". " + temp + "\n";
                System.out.println(line + "added: " + temp + "\n" + line);
                index++;
                count++;
            }
        }
    }
}
