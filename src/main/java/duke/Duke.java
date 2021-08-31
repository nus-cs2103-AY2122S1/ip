package duke;


import javafx.fxml.FXML;



/**
 * Main class for the Duke.
 */
public class Duke {

    /**
     * Main method for Duke which runs the program.
     * @param args main method args.
     */
    public static void main(String[] args) {
        GreetingBot newBot = new GreetingBot();
        newBot.startBot("./data/list.txt");

    }



    /**
     * Method that creates Duke's response.
     * @param input a string which is user input
     * @return the response by duke.
     */
    @FXML
    String getResponse(String input) {
        return "Let me repeat what you said:  " + input;
    }




    }




