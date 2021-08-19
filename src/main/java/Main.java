import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Dino dino = new Dino();
        dino.greeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                dino.farewell();
                break;
            } else {
                dino.readCommand(input);
            }
        }
        sc.close();
    }

}
