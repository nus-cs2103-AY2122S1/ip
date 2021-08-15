import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String logo = "                                        _       \n"
                + "  _ __  _ _   _ __  ___ ___ ___ ___ ___| |__ ___\n"
                + " | '  \\| '_| | '  \\/ -_) -_|_-</ -_) -_) / /(_-<\n"
                + " |_|_|_|_|   |_|_|_\\___\\___/__/\\___\\___|_\\_\\/__/\n"
                + "\n";
        String[] items = new String[100];
        int currentIndex = 0;

        System.out.println(logo);
        System.out.println("I'm Mr Meeseeks look at me!");
        System.out.println("Type in anything and I will keep track of it for you!");
        System.out.println("Type \"list\" to show all items inputted so far");
        System.out.println("Type \"bye\" to exit");


        while (true){
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. *Poof!*");
                break;
            }

            if (input.equals("list")){
                //list out all tracked items
                System.out.println("Items I have so far:");
                for (int i = 1; i<currentIndex+1; i++){
                    System.out.println(i + ". " + items[i-1]);
                }
            } else {
                if (currentIndex == 99) {
                    //prevent out of bounds error
                    System.out.println("Oops! My task list is full. No new items can be added");
                } else {
                    //add item to the end of the array.
                    System.out.println("Added in " + input);
                    items[currentIndex] = input;
                    currentIndex++;
                }
            }
        }
    }
}
