import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int len = 0;
    public static void pack(String string) {
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
            if(s.equals("List")) {
                readList();
                continue;
            }
            addList(s);
        } while(scan.hasNextLine());
    }

    public static void addList(String item) {
        list[len] = item;
        len++;
        pack("added: " + item);
    }

    public static void readList(){
        String items = "";
        for(int i = 1; i < len; i++) {
            items += i + ". " + list[i-1] + "\n";
        }
        if(len > 0) {
            items += len + ". " + list[len-1];
        }
        pack(items);
    }

    public static void exit(){
        String bye = "Bye. Hope to see you again soon!";
        pack(bye);
    }

    public static void level2(){
        greet();
        scan();
        exit();
    }

    public static void main(String[] args) {
        level2();
    }
}
