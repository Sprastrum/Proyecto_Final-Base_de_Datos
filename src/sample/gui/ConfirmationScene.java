package sample.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * window for checking other scene actions
 */
public class ConfirmationScene extends Stage {

    private Scene confirmationScene;
    private Stage stage;
    private String mode;
    private GridPane pane;
    private Button confirmation, cancel;
    private boolean result;

    private static final Text EDIT = new Text("¿Deseas editar a ésta persona?");
    private static final Text ADD = new Text("¿Deseas agregar a ésta persona?");
    private static final Text DELETE = new Text("¿Deseas eliminar a ésta persona?");

    /**
     * Constructor for creating a ConfirmationScene instance
     * @param ownerStage of the main window
     * @param mode "Agregar", "Editar" or "Eliminar"
     */
    public ConfirmationScene(Stage ownerStage, String mode) {
        stage = new Stage();
        this.mode = mode;

        setUp();
        behavior();

        stage.setTitle("Confirmación");
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(confirmationScene);
        stage.showAndWait();
    }

    /**
     * Method that calls other sub methods for setting up the report stage and scene.
     */
    public void setUp() {
        setUpButton();
        setUpPane();

        confirmationScene = new Scene(pane, 300, 100);
    }

    /**
     * Gives action to all the elements present in the scene.
     */
    public void behavior() {
        confirmation.setOnAction(e -> {
            stage.close();
            result = true;
        });

        cancel.setOnAction(e -> {
            stage.close();
            result = false;
        });
    }

    /**
     * sets up all the buttons.
     */
    public void setUpButton() {
        confirmation = new Button("Confirmar");
        confirmation.setPrefSize(100, 30);

        cancel = new Button("Cancelar");
        cancel.setPrefSize(100, 30);
    }

    /**
     * Creates and sets up the Grid pane that contains the text and the buttons. Also defines which mode is going to be used
     */
    public void setUpPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(20);
        pane.setHgap(20);

        switch (mode) {
            case "Agregar" -> pane.add(ADD, 0, 0, 2, 1);
            case "Editar" -> pane.add(EDIT, 0, 0, 2, 1);
            case "Eliminar" -> pane.add(DELETE, 0, 0, 2, 1);
        }

        pane.add(confirmation, 0, 1);
        pane.add(cancel, 1, 1);
    }

    public boolean getConfirmation() {
        return result;
    }

}
