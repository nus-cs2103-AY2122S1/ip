import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Dino dino = new Dino();
        dino.greeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("echo")) dino.setEcho();
            else if (input.equals("bye")) {
                dino.farewell();
                break;
            } else {
                if (dino.getMode().equals("echo")) dino.echo(input);
                else dino.readCommand(input);
            }
        }
        sc.close();
    }

}
