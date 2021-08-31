package duke;


import javafx.fxml.FXML;



/**
 * Main class for the Duke.
 */
public class Duke {

    public GreetingBot dukeBot;


    public Duke() {
        GreetingBot newBot = new GreetingBot();
        dukeBot = newBot;
        newBot.loadList("./data/list.txt");

    }




    /**
     * Method that creates Duke's response.
     * @param input a string which is user input
     * @return the response by duke.
     */
    @FXML
    String getResponse(String input) {
        return dukeBot.store(input);
    }




    }




