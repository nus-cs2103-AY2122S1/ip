public class InputChecker {
    public String input;

    public InputChecker(String s) throws DukeException{
        this.input = s;

        if(s.equals("bye") || s.equals("list")){ //does not throw error if input is bye or list

        } else if(s.equals("done")){
            throw new DukeException("Please specify what you are done with!");
        } else if(s.equals("deadline") || s.equals("event") || s.equals("todo")){
            throw new DukeException("The description of " + s + " cannot be empty!");
        } else if(s.length() > 4 && s.substring(0,4).equals("todo")){

        } else if(s.length() > 8 && s.substring(0,8).equals("deadline")){

        } else if(s.length() > 5 && s.substring(0,5).equals("event")){

        } else if (s.contains("done") && s.length() > 4){

        } else if (s.equals("delete")) {
            throw new DukeException("Please specify what you are deleting!");
        } else if (s.contains("delete")) {

        } else {
            throw new DukeException("Sorry! I don't get what you're saying!");
        }
    }

    public String showInput(){
        return this.input;
    }
}
