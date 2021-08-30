package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    private Button addBth;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button listAllBtn;
    @FXML
    private Button markBtn;
    @FXML
    private TextField cmdLine;
    @FXML
    private TextArea display;

    private String command;
    private MyList list;
    private Storage storage;

    public Controller() {
        this.list = new MyList();
        this.storage = new Storage(this.list, "./Data.txt");
        storage.load();
    }

    public void enter(KeyEvent e){
        if (e.getCode().equals(KeyCode.ENTER)) {
            System.out.println("ENTER!");
            this.command = this.cmdLine.getText();
            System.out.println(this.command);
            this.cmdLine.setText("");

        }
    }

    public void listAll(ActionEvent event) {
        String s = this.list.listAll();
        System.out.println(s);
    }

}
