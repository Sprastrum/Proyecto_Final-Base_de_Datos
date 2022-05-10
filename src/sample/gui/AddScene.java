package sample.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.logic.entities.Persona;
import sample.logic.entities.PublicEmployee;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;
import sample.logic.ValidPublicEmployees;
import java.io.IOException;
import java.util.Map;

/**
 * Window that allows adding a person to the database.
 */
public class AddScene extends SetUp {

    private Button addButton, cancelButton;
    private final Stage stage;
    private Scene addScene;
    private GridPane pane;
    private GridPane buttonsBox;
    private VBox layout;
    private static final Text TITLE = new Text("Añadir");

    private final IPersonaServices personaServices;
    private ConfirmationScene confirmationScene;

    /**
     * unique constructor of the class. It initialize and shows the adding stage.
     * @param personaServices of the main scene
     * @param ownerStage of the main scene
     */
    public AddScene(IPersonaServices personaServices, Stage ownerStage) {
        super();
        stage = new Stage();
        this.personaServices = personaServices;

        setUp();
        behavior();

        stage.setTitle("Añadir Persona");
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(addScene);
        stage.showAndWait();
    }

    /**
     * Gives action to all the elements present in the scene.
     */
    public void behavior() {
        addButton.setOnAction(e -> {
            confirmationScene = new ConfirmationScene(stage, "Agregar");

            if(confirmationScene.getConfirmation()) {

                try {
                    boolean isPublicEmployee = false;
                    for (ValidPublicEmployees v : ValidPublicEmployees.values()) {
                        if (inputPosition.getValue().equals(v.toString())) {
                            isPublicEmployee = true;
                        }
                    }
                    if (!isPublicEmployee) {
                        Persona persona = new Persona(inputName.getText(), inputLastname.getText(), inputAge.getText(),
                                inputSex.getValue(), inputDepartment.getValue(), inputCondition.getValue(), inputReason.getText(),
                                inputId.getText());
                        personaServices.insert(persona);
                    } else {
                        PublicEmployee publicEmployee = new PublicEmployee(inputName.getText(), inputLastname.getText(), inputAge.getText(),
                                inputSex.getValue(), inputDepartment.getValue(), inputCondition.getValue(), inputReason.getText(),
                                inputId.getText(), inputPosition.getValue());
                        personaServices.insert(publicEmployee);
                    }
                    inputId.clear();
                    inputReason.clear();
                    inputAge.clear();
                    inputLastname.clear();
                    inputName.clear();

                } catch (IOException | PersonaException exception) {
                    exception.printStackTrace();
                }
            }
        });

        cancelButton.setOnAction(e -> stage.close());
    }

    /**
     * Method that calls other sub methods for setting up the update stage and scene.
     */
    public void setUp() {
        setUpInputs();
        setUpButtons();
        setUpPane();
        setUpLayout();

        addScene = new Scene(layout, 400, 550);
    }

    /**
     *  sets up all the PrompTexts of the text fields present in the class
     */
    public void setUpInputs () {

        Map<Label, String> list = labelsList();

        //Nombre
        inputName.setPromptText(list.get(name));

        //Apellido
        inputLastname.setPromptText(list.get(lastname));

        //Edad
        inputAge.setPromptText(list.get(age));
        
        //Razón
        inputReason.setPromptText(list.get(reason));

        //Identificación
        inputId.setPromptText(list.get(id));
    }

    /**
     * Creates and sets up the Grid pane that contains the text fields and
     * combo boxes.
     */
    public void setUpPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(20);
        pane.setVgap(20);

        TITLE.setFont(DataScene.FONT_TITLE);

        pane.add(TITLE,0,0);

        int counter = 1;
        Map<Node, Node> objectList = listOfObjects();
        for (Map.Entry<Node, Node> p : objectList.entrySet()) {
            pane.add(p.getKey(), 0, counter);
            pane.add(p.getValue(), 1, counter);
            counter++;
        }
    }
    /**
     * sets up all the buttons.
     */
    public void setUpButtons () {
        buttonsBox = new GridPane();
        buttonsBox.setHgap(30);
        buttonsBox.setAlignment(Pos.CENTER);
        addButton = new Button("Añadir");
        addButton.setPrefSize(142,30);

        cancelButton = new Button("Cancelar");
        cancelButton.setPrefSize(142,30);

        buttonsBox.add(addButton,0,0);
        buttonsBox.add(cancelButton,1,0);
    }
    /**
     * sets up the layout of the scene.
     */
    private void setUpLayout(){
        layout = new VBox();
        layout.setPadding(new Insets(20));
        layout.setSpacing(20);

        layout.getChildren().addAll(pane,buttonsBox);
    }
}