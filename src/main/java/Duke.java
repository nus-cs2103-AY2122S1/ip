import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> items = new ArrayList<String>(100);
    public static void main(String[] args) {
        Scanner userSc = new Scanner(System.in);
        String name = "JARVIS";


        System.out.println("Hello I am " + name +".\nIs there anything I can do for you Sir?\n");

        String userInput = userSc.nextLine();
        while(!userInput.equals("bye")){
            if(userInput.equals("list")){
                list();
            } else {
                echo(userInput);
            }
            userInput = userSc.nextLine();
        }

        System.out.println("Goodbye Sir! Will take good care of your plants in the meantime.");
    }

    public static void echo(String userInput){
        items.add(userInput);
        System.out.println("added: " + userInput + "\n");
    }

    public static void list(){
        for(int i = 1; i <= items.size(); i++){
            System.out.println(i + ". " + items.get(i - 1));
        }
        System.out.println("");
    }


}
