package sample.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.logic.DepartmentsEnum;
import sample.logic.ValidPublicEmployees;

import java.util.*;

/**
 * class that standardize the setups of text fields, combo boxes and labels. Also, gives
 * util lists for automation
 */
public class SetUp {
    protected TextField inputName, inputLastname, inputAge, inputReason, inputId;
    protected ComboBox<String> inputDepartment, inputSex, inputCondition, inputPosition;
    protected Label name, lastname, age, sex, department, condition, reason, id, position;
    public static final int NUMBER_OF_ITEMS = 9;
    public static final String POSITION = "Posición";
    public static final String NAME  = "Nombre";
    public static final String LAST_NAME = "Apellido";
    public static final String AGE = "Edad";
    public static final String ID = "Identificación";
    public static final String SEX = "Sexo";
    public static final String DEPARTMENT = "Departamento";
    public static final String CONDITION = "Condición";
    public static final String REASON = "Razón";

    /**
     * unique constructor of the class. It configures the elements.
     */
    public SetUp() {
        initInputs();
        setUpInputs();
    }

    /**
     * Calls for the setUp of the Combo boxes and labels.
     */
    private void setUpInputs(){
        setUpLabels(labelsList());
        
        //Posición
        setUpPositionComboBox(inputPosition);
      
        //Sexo
        setUpSexComboBox(inputSex);

        //Departamento
        setUpDepartmentsComboBox(inputDepartment);

        //Condición
        setUpConditionComboBox(inputCondition);

    }

    /**
     * sets up a given combo Box with the valid departments
     * @param comboBox for configuring
     */
    private void setUpDepartmentsComboBox (ComboBox<String> comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (DepartmentsEnum c :DepartmentsEnum.values()){
            list.add(c.toString());
        }

        comboBox.setItems(list);
        comboBox.setPromptText("-");
        comboBox.setMinWidth(200);
    }

    /**
     * sets up a given combo Box with the sexes
     * @param comboBox for configuring
     */
    private void setUpSexComboBox (ComboBox<String> comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Masculino", "Femenino");

        comboBox.setItems(list);
        comboBox.setPromptText("-");
        comboBox.setMinWidth(200);
    }

    /**
     * sets up a given combo Box with the valid conditions.
     * @param comboBox for configuring
     */
    private void setUpConditionComboBox (ComboBox<String> comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Vivo", "Herido", "Muerto", "Desconocido");

        comboBox.setItems(list);
        comboBox.setPromptText("-");
        comboBox.setMinWidth(200);
    }

    /**
     * sets up a given combo Box with the valid stoppage positions.
     * @param comboBox for configuring
     */
    private void setUpPositionComboBox (ComboBox comboBox) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("Civil");
        for (ValidPublicEmployees v : ValidPublicEmployees.values()) {
            list.add(v.toString());
        }
        comboBox.setItems(list);
        comboBox.setPromptText("-");
        comboBox.setMinWidth(200);
    }

    /**
     * standardizes the setup of the labels.
     * @param map with the labels and its texts.
     */
    protected void setUpLabels(Map<Label, String> map){
        for (Map.Entry<Label,String> e: map.entrySet()){
            e.getKey().setFont(DataScene.FONT);
            e.getKey().setText(e.getValue());
        }
    }

    /**
     * Initialize the text fields, combo boxes and labels.
     */
    private void initInputs() {
        //Text Fields
        inputName = new TextField();
        inputLastname = new TextField();
        inputAge = new TextField();
        inputReason = new TextField();
        inputId = new TextField();

        //ComboBoxes
        inputDepartment = new ComboBox<>();
        inputSex = new ComboBox<>();
        inputCondition = new ComboBox<>();
        inputPosition = new ComboBox<>();

        //Labels
        name = new Label();
        lastname = new Label();
        age = new Label();
        sex = new Label();
        department = new Label();
        condition = new Label();
        reason = new Label();
        id = new Label();
        position = new Label();
    }

    /**
     * map of the labels and inputs used.
     * @return map
     */
    protected Map<Node, Node> listOfObjects () {
        Map<Node, Node> objectList = new LinkedHashMap<>();
        objectList.put(position, inputPosition);
        objectList.put(name, inputName);
        objectList.put(lastname, inputLastname);
        objectList.put(age, inputAge);
        objectList.put(id, inputId);
        objectList.put(sex, inputSex);
        objectList.put(department, inputDepartment);
        objectList.put(condition, inputCondition);
        objectList.put(reason, inputReason);
        return objectList;
    }

    /**
     * map of the labels with their respective String name.
     * @return map
     */
    protected Map<Label, String> labelsList() {
        Map<Label, String> labels = new HashMap<>();
        labels.put(name, NAME);
        labels.put(lastname,LAST_NAME);
        labels.put(age,AGE);
        labels.put(id,ID);
        labels.put(sex,SEX);
        labels.put(department,DEPARTMENT);
        labels.put(condition,CONDITION);
        labels.put(reason,REASON);
        labels.put(position,POSITION);

        return labels;
    }

    /**
     * String list of the parameters used
     * @return string list
     */
    public List<String> stringList() {
        List<String> list = new ArrayList<>();
        list.add(POSITION);
        list.add(NAME);
        list.add(LAST_NAME);
        list.add(AGE);
        list.add(ID);
        list.add(SEX);
        list.add(DEPARTMENT);
        list.add(CONDITION);
        list.add(REASON);
        return list;
    }
}