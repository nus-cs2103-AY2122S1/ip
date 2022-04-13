package duke;


import javafx.fxml.FXML;



/**
 * Main class for the Duke.
 */
public class Duke {

    private GreetingBot dukeBot;


    /**
     * Constructor for Duke
     */
    public Duke() {
        GreetingBot newBot = new GreetingBot();
        dukeBot = newBot;
        newBot.loadList("./data/list.txt");

    }

    /**
     * Method to get dukeBot
     *
     * @return this dukeBot
     */
    public GreetingBot getDukeBot() {
        return this.dukeBot;
    }


    /**
     * Method that creates Duke's response.
     *
     * @param input a string which is user input
     * @return the response by duke.
     */
    @FXML
    String getResponse(String input) {
        assert dukeBot.getTasks() != null;
        return dukeBot.store(input);
    }


}





