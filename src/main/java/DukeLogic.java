import java.util.function.Function;

public class DukeLogic {

  public static boolean takeInput(String input) {
    if (matches("bye").apply(input)) {
      Duke.renderOutput("Goodbye!");
      return false;
    } else {
      Duke.renderOutput(input);
      return true;
    }
  }

  private static Function<String, Boolean> matches(String phrase) {
    return x -> x.trim().equalsIgnoreCase(phrase);
  }

  private static Function<String, Boolean> contains(String phrase) {
    return x -> x.trim().contains(phrase);
  }
}
