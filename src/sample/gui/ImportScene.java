package sample.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import sample.logic.persistence.PersistenceException;
import sample.logic.persistence.implementation.Import;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * class that allows importing a csv file for adding (if possible) its people to the database.
 */
public class ImportScene {
    private Button button;
    private Scene scene;
    private VBox vBox = new VBox();
    private Import importClass;
    private Stage stage;
    private Label label, idsLabel;

    /**
     * unique constructor of the class. It initialize and shows the import stage.
     * @param personaServices of the main scene
     * @param ownerStage of the main scene
     */
    public ImportScene(IPersonaServices personaServices, Stage ownerStage) {
        stage = new Stage();
        importClass = new Import(personaServices);
        setUpInput();
        setUpBehavior();


        stage.setTitle("Importar");
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Gives action to the element present in the scene.
     */
    private void setUpBehavior() {
        button.setOnAction(e -> {
            stage.close();
        });
    }

    /**
     * Opens, stores and verifies a selected file.
     */
    private void setUpInput() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files","*.csv"));
        File file = fileChooser.showOpenDialog(stage);
        try {
            importClass.verifyFileHeader(file);
            List<String> idsAvoided = (importClass.fileToPersonas(file));
            windowConfig(idsAvoided);
        } catch (IOException | PersistenceException | PersonaException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Method that sets up the window that will be shown depending on the content of the given list.
     * @param list of identifications
     */
    private void windowConfig(List<String> list) {
        button = new Button();
        button.setText("Aceptar");
        button.setPrefSize(70,30);

        if (list.isEmpty()) {

            label = new Label();
            label.setText("¡Importación exitosa!");
            label.setFont(new Font("Tahoma", 18));

            vBox.getChildren().addAll(label,button);
            vBox.setSpacing(10);
            vBox.setAlignment(Pos.CENTER);

            scene = new Scene(vBox,200,100);;
        }
        if (!list.isEmpty()){ ;

            label = new Label();
            label.setText("Los siguientes id's no fueron añadidos");
            label.setFont(new Font("Tahoma", 18));


            idsLabel = new Label();
            idsLabel.setText(list.toString().replace('[',' ').replace(']',' '));
            idsLabel.setFont(new Font("Tahoma", 15));


            vBox.getChildren().addAll(label,idsLabel,button);
            vBox.setSpacing(20);
            vBox.setAlignment(Pos.CENTER);

            scene = new Scene(vBox,400,200);;
        }
    }

}
