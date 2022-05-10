package sample.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.logic.ValidPublicEmployees;
import sample.logic.entities.Persona;
import sample.logic.entities.PublicEmployee;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;

import java.util.Map;

/**
 * Gives the space for modifying a selected person in the database.
 */
public class UpdateScene extends SetUp{

    private Button updateButton, cancelButton;
    private GridPane buttonsBox;
    private Persona persona;
    private final Stage stage;
    private Scene addScene;
    private GridPane pane;
    private String uPosition, uName, uLastName, uAge, uId,uSex,uDepartment, uCondition,uReason;
    private static final Text TITLE = new Text("Edición");
    private final IPersonaServices personaServices;
    private VBox layout;

    /**
     * Unique constructor of the class, it initialize and shows the update stage.
     * @param personaServices of the main scene
     * @param persona to update
     * @param ownerStage stage of the main scene
     */

    public UpdateScene(IPersonaServices personaServices, Persona persona, Stage ownerStage) {
        super();
        stage = new Stage();
        this.personaServices = personaServices;
        this.persona = persona;
        setUp();
        behavior();

        stage.setTitle("Modificar Persona");
        stage.setScene(addScene);
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    /**
     * Method that calls other sub methods for setting up the update stage and scene.
     */
    public void setUp() {
        setUpPersonaValues();
        setUpInputs();
        setUpButtons();
        setUpPane();
        setUpLayout();

        addScene = new Scene(layout, 400, 550);
    }

    /**
     * Gives action to all the elements present in the scene.
     */
    public void behavior() {
        updateButton.setOnAction(e -> {
            //comboBoxes
            if(!(inputPosition.getValue().equals(uPosition))){
                uPosition = inputPosition.getValue();
            }
            if(!(inputSex.getValue().equals(uSex))){
                uSex = inputSex.getValue();
            }
            if(!(inputDepartment.getValue().equals(uDepartment))){
                uDepartment = inputDepartment.getValue();
            }
            if(!(inputCondition.getValue().equals(uCondition))){
                uCondition = inputCondition.getValue();
            }
            if(!(inputName.getText().isEmpty())){
                uName = inputName.getText();
            }
            if(!(inputLastname.getText().isEmpty())){
                uLastName = inputLastname.getText();
            }
            if(!(inputAge.getText().isEmpty())){
                uAge = inputAge.getText();
            }
            if(!(inputId.getText().isEmpty())){
                uId = inputId.getText();
            }
            if(!(inputReason.getText().isEmpty())){
                uReason = inputReason.getText();
            }
            boolean isPublicEmployee = false;
            for (ValidPublicEmployees v : ValidPublicEmployees.values()) {
                if (uPosition.equals(v.toString())) {
                    isPublicEmployee = true;
                }
            }
            try {
              if(!isPublicEmployee) {
                  personaServices.modify(new Persona(uName, uLastName, uAge, uSex, uDepartment, uCondition, uReason, uId),persona);
              }
              if (isPublicEmployee){
                  personaServices.modify(new PublicEmployee(uName, uLastName, uAge, uSex, uDepartment, uCondition, uReason, uId,uPosition),persona);
              }
              stage.close();
            } catch (PersonaException personaException) {
                personaException.printStackTrace();
            }

        });

        cancelButton.setOnAction(e -> stage.close());
    }

    /**
     * Stores the persona attributes in local variables
     */
    private void setUpPersonaValues(){
        uName = persona.getName();
        uLastName = persona.getLastName();
        uAge = Integer.toString(persona.getAge());
        uId = persona.getId();
        uReason = persona.getReason();
        uSex = persona.getSex();
        uDepartment = persona.getDepartment();
        uCondition = persona.getCondition();
        uPosition = persona.getPosition();
    }

    /**
     * Gives custom titles for the text fields and an initial position for the
     * combo boxes.
     */
    public void setUpInputs () {

        //Posición
        inputPosition.getSelectionModel().select(persona.getPosition());

        //Nombre
        inputName.setPromptText(persona.getName());

        //Apellido
        inputLastname.setPromptText(persona.getLastName());

        //Edad
        inputAge.setPromptText(Integer.toString(persona.getAge()));

        //Sexo
        inputSex.getSelectionModel().select(persona.getSex());

        //Departamento
        inputDepartment.getSelectionModel().select(persona.getDepartment());

        //Condición
        inputCondition.getSelectionModel().select(persona.getCondition());

        //Razón
        inputReason.setPromptText(persona.getReason());

        //Identificación
        inputId.setPromptText(persona.getId());
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
        updateButton = new Button("Modificar");
        updateButton.setPrefSize(142,30);

        cancelButton = new Button("Cancel");
        cancelButton.setPrefSize(142,30);

        buttonsBox.add(updateButton,0,0);
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