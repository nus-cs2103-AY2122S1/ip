import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        List<String> toDo = new ArrayList<>();
        String item;
        String line = "-----------------------------------------";
        Scanner myObj = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! Im Duke\n" + "What can I do for you?");
        System.out.println(line);

        while(myObj.hasNext()){
            System.out.println();
            item = myObj.next();

            System.out.println(item);//User input typed
            if(item.equals("bye")){
                System.out.println(line);
                System.out.println("     " + "Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(line);
            System.out.println("     " + item);
            System.out.println(line);

            toDo.add(item);
        }
//myObj.close();

//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        for(String s: toDo){
//
//            System.out.println(s);
//        }

    }
}
