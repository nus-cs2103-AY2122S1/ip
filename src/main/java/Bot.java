import java.time.Period;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Bot {

    /**
     * initiate bot's brain to respond to inputs
     * @param args
     */
    public static void main(String[] args) {
        new BotBrain().initiate();
    }

}