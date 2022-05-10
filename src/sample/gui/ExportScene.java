package sample.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.logic.services.IPersonaServices;
import sample.logic.services.implementation.PersonaServices;

import java.io.FileNotFoundException;

/**
 * Window that allows exporting the database to an specific type of file.
 */
public class ExportScene extends Stage {

    private Scene exportScene;
    private final Stage STAGE;

    public static final Text TEXT = new Text("Seleccione la extensión");

    private final ComboBox<String> EXPORTABLE = new ComboBox<>();
    private Character character;
    private GridPane pane;

    private Button confirmation, cancel;

    private IPersonaServices personaServices;

    /**
     * unique constructor of the class. It initialize and shows the export stage.
     * @param personaServices of the main scene
     * @param ownerStage of the main scene
     */
    public ExportScene(IPersonaServices personaServices, Stage ownerStage) {
        STAGE = new Stage();
        this.personaServices = personaServices;

        setUp();
        behavior();

        STAGE.setTitle("Exportar");
        STAGE.initOwner(ownerStage);
        STAGE.initModality(Modality.APPLICATION_MODAL);
        STAGE.setScene(exportScene);
        STAGE.showAndWait();
    }

    /**
     * Method that calls other sub methods for setting up the export stage and scene.
     */
    public void setUp() {
        setUpButton();
        setUpComboBox(EXPORTABLE);
        setUpPane();

        VBox vBox = new VBox();
        vBox.setSpacing(15);
        vBox.getChildren().addAll(TEXT, EXPORTABLE, pane);
        TEXT.setFont(DataScene.FONT_TITLE);
        vBox.setAlignment(Pos.CENTER);

        exportScene = new Scene(vBox, 350, 170);
    }

    /**
     * Gives action to all the elements present in the scene.
     */
    public void behavior() {
        personaServices = new PersonaServices();

        confirmation.setOnAction(e -> {
            STAGE.close();
            switch (EXPORTABLE.getValue()) {
                case "CSV" -> character = ',';
                case "PCS" -> character = ';';
                case "BSC" -> character = '|';
            }
            try {
                this.personaServices.export(character);
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        });

        cancel.setOnAction(e -> STAGE.close());
    }

    /**
     * Creates and sets up the Grid pane that contains the buttons
     */
    public void setUpPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(3);

        pane.add(confirmation, 0, 2);
        pane.add(cancel, 1, 2);
    }

    /**
     * sets up all the buttons.
     */
    public void setUpButton() {
        confirmation = new Button("Exportar");
        confirmation.setPrefSize(100, 30);

        cancel = new Button("Cancelar");
        cancel.setPrefSize(100, 30);
    }

    /**
     * It sets up the comboBox of the file extensions.
     * @param comboBox to configure
     */
    public void setUpComboBox(ComboBox<String> comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("CSV", "PCS", "BSC");

        comboBox.setItems(list);
        comboBox.setPromptText("-");
        comboBox.setMinWidth(200);
    }
}
