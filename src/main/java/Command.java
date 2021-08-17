import java.util.Arrays;

public class Command {
    private String name;
    private String[] records = new String[100];
    private int count = 0;

    public Command(String name) {
        this.name = name;
    }
    /**
     * Returns greeting message for bot.
     *
     * @return greeting message for bot.
     */
    public String greeting() {
        return String.format("Hihi! This is %1$s! \n\tHow can I help?", name);
    }

    /**
     * Returns echo message for bot.
     *
     * @param input
     * @return input
     */
    public String echo(String input) {
        return input;
    }

    /**
     * Returns added message for add command.
     *
     * @param input String to be added.
     * @return successfully added message.
     */
    public String add(String input) {
        records[count] = input;
        count += 1;
        return String.format("added: %1$s", input);
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @return formatted string representing elements in records array.
     */
    public String list() {
        String result = "";
        for (int i = 0; i < count; i++) {
            result += String.format("%1$d. %2$s \n\t", i + 1, records[i]);
            if (i == count - 1) {
                result += "End of List yayyyy!!";
            }
        }
        return result;
    }

    /**
     * Returns ending message for bot.
     *
     * @return ending message for bot.
     */
    public String end() {
        return "Bye bye!";
    }
}
