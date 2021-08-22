import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Processor.greet();
        Processor.load(); 
        Scanner input = new Scanner(System.in);
        Processor.process(input); 
        input.close();
        Processor.exit();
    }
}