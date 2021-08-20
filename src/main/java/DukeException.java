public class DukeException extends Exception {

    private String ErrorMessage = "";

    public DukeException(String ErrorMessage){
        this.ErrorMessage = ErrorMessage;
    }

    public void PrintErrorMessage(){
        System.out.println(this.ErrorMessage);
    }
}
