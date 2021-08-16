public class Command {
    private String name;

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
     * Returns ending message for bot.
     *
     * @return ending message for bot.
     */
    public String end() {
        return "Bye bye!";
    }
}
