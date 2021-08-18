import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    static String myList = "";

    static List<String> toDo = new ArrayList<>();

    static void add(String newItem){
        int count = toDo.size();

        myList += "     " + count + ". " + newItem + "\n";


    }
    public static void main(String[] args) {


        String item;
        String line = "-----------------------------------------";
        Scanner myObj = new Scanner(System.in);

        System.out.println(line);
        System.out.println("Hello! Im Duke\n" + "What can I do for you?");
        System.out.println(line);

        while(myObj.hasNext()){
            System.out.println();
            item = myObj.nextLine();

            System.out.println(item);//User input typed
            if(item.equals("bye")){
                System.out.println(line);
                System.out.println("     " + "Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            if(item.equals("list")){
                System.out.println(line);
                System.out.println(myList);
                System.out.println(line);
                continue;
            }
            toDo.add(item);
            add(item);
            System.out.println(line);
            System.out.println("     added: " + item);//Added item
            System.out.println(line);
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
