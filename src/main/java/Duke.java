import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int len = 0;
    private enum Type{
        DEADLINE, EVENT, TODO
    };

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

            if(s.length() > 8 && s.substring(0,8).equals("deadline")) {
                int indexOfTime = s.indexOf("/by");
                String item = s.substring(9, indexOfTime);
                String by = s.substring(indexOfTime+4);
                addList(Type.DEADLINE, item, by);
            } else if (s.length() > 5 && s.substring(0,5).equals("event")) {
                int indexOfTime = s.indexOf("/at");
                String item = s.substring(6, indexOfTime);
                String at = s.substring(indexOfTime+4);
                addList(Type.EVENT, item, at);
            } else if (s.length() > 4 && s.substring(0,4).equals("todo")) {
                String item = s.substring(5);
                addList(Type.TODO, item, null);
            } else if(s.substring(0,4).equals("done")) {
                char temp = s.charAt(5);
                if(Character.isDigit(temp)) {
                    int item = Integer.parseInt(s.substring(5, 6));
                    done(tasks[item-1]);
                    continue;
                }
            }
        } while(scan.hasNextLine());
    }

    public static void addList(Type type, String item, String time) {
        switch (type) {
            case DEADLINE:
                tasks[len] = new Deadline(item, time);
                len++;
                break;
            case EVENT:
                tasks[len] = new Event(item, time);
                len++;
                break;
            case TODO:
                tasks[len] = new Todo(item);
                len++;
                break;
        }

        String added = "Got it. I've added this task:\n  " + tasks[len-1] + "\nNow you have " + len;
        if(len > 1) {
            added += " tasks in the list.";
        } else {
            added += " task in the list.";
        }
        pack(added);
    }

    public static void readList(){
        String out = "Here are the tasks in your list:";
        for(int i = 1; i <= len; i++) {
            out += "\n" + i + "." + tasks[i-1].toString();
        }
        pack(out);
    }

    public static void done(Task task) {
        String out = "Nice! I've marked this task as done:\n  ";
        task.markAsDone();
        out += task.toString();
        pack(out);
    }

    public static void exit(){
        String bye = "Bye. Hope to see you again soon!";
        pack(bye);
    }

    public static void level4(){
        greet();
        scan();
        exit();
    }

    public static void main(String[] args) {
        level4();
    }
}
