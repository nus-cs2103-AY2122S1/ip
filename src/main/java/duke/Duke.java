package duke;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Integer.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class Duke {

    enum Category {
        TODO,
        EVENT,
        DEADLINE
    }

    public Ui ob;
    public TaskList tasklist;
    public Storage storage;

    public Duke() {
        storage = new Storage();
        this.tasklist=new TaskList(storage);
        ob=new Ui(storage);
    }

    String getResponse(String input) throws DukeException, IOException {
        return ob.execInput(input, tasklist);
    }
}
