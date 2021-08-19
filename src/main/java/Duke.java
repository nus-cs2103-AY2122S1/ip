import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int len = 0;

    public static void pack(String string) { // format the output
        System.out.println("---------------------------------------------");
        System.out.println(string);
        System.out.println("---------------------------------------------");
    }

    public static void greet(){
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        pack(greeting);
    }

    public static void scan(){
        Scanner scan = new Scanner(System.in);
        String s;
        do{
            s = scan.nextLine();
            if(s.equals("bye")) break;
            if(s.equals("List") || s.equals("list")) {
                readList();
                continue;
            }
            if(s.length() > 4 && s.substring(0,4).equals("done")) {
                char temp = s.charAt(5);
                if(Character.isDigit(temp)) {
                    int item = Integer.parseInt(s.substring(5, 6));
                    done(tasks[item-1]);
                    continue;
                }
            }
            addList(s);
        } while(scan.hasNextLine());
    }

    public static void addList(String item) {
        tasks[len] = new Task(item);
        len++;
        pack("added: " + item);
    }

    public static void readList(){
        String out = "";
        for(int i = 1; i < len; i++) {
            out += i + ".[" + tasks[i-1].getStatusIcon() + "] " + tasks[i-1].getDescription() + "\n";
        }
        if(len > 0) {
            out += len + ".[" + tasks[len-1].getStatusIcon() + "] " + tasks[len-1].getDescription();
        }

        pack(out);
    }

    public static void done(Task task) { // mark task as done and do the output
        String out = "Nice! I've marked this task as done:\n  ";
        task.markAsDone();
        out += "[" + task.getStatusIcon() + "] " + task.getDescription();
        pack(out);
    }

    public static void exit(){
        String bye = "Bye. Hope to see you again soon!";
        pack(bye);
    }

    public static void level3(){
        greet();
        scan();
        exit();
    }

    public static void main(String[] args) {
        level3();
    }
}
