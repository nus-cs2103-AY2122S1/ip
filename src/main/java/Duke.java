import java.util.Scanner;

public class Duke {
    public static void pack(String[] strings) {
        System.out.println("    ---------------------------------------------");
        for(int i = 0; i < strings.length; i++) {
            System.out.println("     " + strings[i]);
        }
        System.out.println("    ---------------------------------------------");
    }

    //    Level-1 starts
    public static void greet(){
        String[] greeting = new String[2];
        greeting[0] = "Hello! I'm Duke";
        greeting[1] = "What can I do for you?";
        pack(greeting);
    }

    public static void echo(){
        Scanner scan = new Scanner(System.in);
        String[] s = new String[1];
        do{
            s[0] = scan.next();
            if(s[0].equals("bye")) break;
            pack(s);
        } while(scan.hasNext());
    }

    public static void exit(){
        String[] bye = new String[]{"Bye. Hope to see you again soon!"};
        pack(bye);
    }

    public static void level1(){
        greet();
        echo();
        exit();
    }
    //Level-1 ends

    public static void main(String[] args) {
        level1();
    }
}
