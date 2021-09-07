package duke;


public class InputChecker {
    public String input;

    /**
     * Constructor for an input checker
     * @param input, the command keyed in by the user
     * @throws DukeException
     */
    public InputChecker(String input) throws DukeException{
        this.input = input;

        if(input.equals("bye") || input.equals("list")){ //does not throw error if input is bye or list

        } else if(input.equals("done")){
            throw new DukeException("Please specify what you are done with!");
        } else if(input.equals("deadline") || input.equals("event") || input.equals("todo")){
            throw new DukeException("The description of " + input + " cannot be empty!");
        } else if(input.length() > 4 && input.substring(0,4).equals("todo")){

        } else if(input.length() > 8 && input.substring(0,8).equals("deadline")){

        } else if(input.length() > 5 && input.substring(0,5).equals("event")){

        } else if (input.contains("done") && input.length() > 4){

        } else if (input.equals("delete")) {
            throw new DukeException("Please specify what you are deleting!");
        } else if (input.contains("delete")) {

        } else if (input.contains("find") && input.length() > 4){

        } else if (input.equals("find")){
            throw new DukeException("Please specify what tasks you are searching for!");
        } else if (input.contains("sort by type")) {

        } else if (input.contains("sort by date")) {

        } else if (input.contains("sort by date reverse")) {

        } else{
            throw new DukeException("Sorry! I don't get what you're saying!");
        }
    }

    /**
     * method to check what is keyed in by the user
     * @return string of what the user has inputted to Duke
     */
    public String showInput(){
        return this.input;
    }
}
