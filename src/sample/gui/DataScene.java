package sample.gui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;
import sample.logic.services.implementation.PersonaServices;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Main scene of where all the information is reported.
 */
public class DataScene extends Application {

    private TableView<Persona> table;
    private Scene dataScene;
    private Stage stage;

    private IPersonaServices personaServices;
    private ConfirmationScene confirmationScene;

    private GridPane pane;
    private Text name;
    private MenuBar bar;
    private Map<String, MenuItem> menuItems;

    public static final Font FONT = new Font("DIALOG", 15);
    public static final Font FONT_TITLE = new Font("Tahoma", 30);

    /**
     * Initializes and configures the primary stage.
     */
    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        setUp();
        behavior();

        primaryStage.setTitle("Directorio del Paro de Colombia");
        primaryStage.setScene(dataScene);
        primaryStage.show();
    }

    /**
     * Gives action to all the elements present in the scene.
     */
    public void behavior() {
        personaServices = new PersonaServices();

        table.setItems((ObservableList<Persona>) this.personaServices.getAll());

        menuItems.get("Add").setOnAction(e -> new AddScene(this.personaServices, stage));

        //Update
        table.setRowFactory(tw -> {
            TableRow row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())){
                    new UpdateScene(this.personaServices,(Persona) row.getItem(),stage);
                }
            });
            return row;
        });

        menuItems.get("Update").setOnAction(e -> {
            if(table.getSelectionModel().getSelectedItem() != null) {
                new UpdateScene(personaServices, table.getSelectionModel().getSelectedItem(), stage);
                pane.getChildren().clear();
                pane.add(table, 0, 0);
            }
        });

        //Delete
        dataScene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case DELETE:
                case BACK_SPACE:
                    try {
                        confirmationScene = new ConfirmationScene(stage, "Eliminar");
                        if(confirmationScene.getConfirmation()) {
                            personaServices.delete(table.getSelectionModel().getSelectedItem());
                            pane.getChildren().clear();
                            pane.add(table, 0, 0);
                        }
                    } catch (PersonaException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        });

        menuItems.get("Delete").setOnAction(e -> {
            new DeleteScene(this.personaServices, stage);

            pane.getChildren().clear();
            pane.add(table, 0, 0);
        });

        table.setOnMouseClicked(e -> {
            pane.getChildren().clear();
            pane.add(table, 0, 0);
            if(table.getSelectionModel().getSelectedItem() != null) {
                for(Persona p : personaServices.getAll()) {
                    if(table.getSelectionModel().getSelectedItem().getId().equals(p.getId())) {
                        name = new Text(table.getSelectionModel().getSelectedItem().toString());
                        name.setFont(FONT_TITLE);
                        pane.add(name, 1, 0);
                    }
                }
            }
        });

        menuItems.get("Export").setOnAction(e -> new ExportScene(personaServices, stage));

        menuItems.get("Import").setOnAction(e -> new ImportScene(personaServices, stage));

        menuItems.get("Report").setOnAction(e -> new ReportScene(personaServices, stage));
    }

    /**
     * Method that calls other sub methods for setting up the data stage and scene.
     */
    public void setUp() {
        setUpTable();
        setUpMenu();
        setUpPane();

        BorderPane menuBar = new BorderPane();
        menuBar.setPadding(new Insets(-10));
        menuBar.setTop(bar);

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(menuBar, pane);

        dataScene = new Scene(layout, 950, 450);
    }

    /**
     * Creates and configures de GridPane layout
     */
    public void setUpPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setVgap(20);
        pane.setHgap(20);

        pane.add(table, 0, 0);
    }

    /**
     * Creates and configures the upper bar menu
     */
    public void setUpMenu() {
        Menu fileMenu = new Menu("Archivos");
        Menu editMenu = new Menu("Editar");
        Menu resumeMenu = new Menu("Resumen");
        Menu aboutMenu = new Menu("Acerca de");

        menuItems = new HashMap<>();
        menuItems.put("Import", new MenuItem("Importar"));
        menuItems.put("Export", new MenuItem("Exportar"));
        menuItems.put("Add", new MenuItem("Agregar"));
        menuItems.put("Update", new MenuItem("Actualizar"));
        menuItems.put("Delete", new MenuItem("Eliminar"));
        menuItems.put("Report", new MenuItem("Reporte"));

        fileMenu.getItems().addAll(menuItems.get("Import"), menuItems.get("Export"));
        editMenu.getItems().addAll(menuItems.get("Add"), menuItems.get("Update"), menuItems.get("Delete"));
        resumeMenu.getItems().add(menuItems.get("Report"));

        bar = new MenuBar();
        bar.getMenus().addAll(fileMenu, editMenu, resumeMenu, aboutMenu);
    }

    /**
     *  Creates and configures the Table view
     */
    public void setUpTable() {
        TableColumn<Persona, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setMinWidth(20);
        nameColumn.setMaxWidth(200);
        nameColumn.setPrefWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Persona, String> lastNameColumn = new TableColumn<>("Apellido");
        lastNameColumn.setMinWidth(20);
        lastNameColumn.setMaxWidth(200);
        lastNameColumn.setPrefWidth(150);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Persona, String> idColumn = new TableColumn<>("Identificación");
        idColumn.setMinWidth(20);
        idColumn.setMaxWidth(200);
        idColumn.setPrefWidth(150);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        table = new TableView<>();
        table.getColumns().addAll(nameColumn, lastNameColumn, idColumn);
        table.setMaxWidth(450);
    }

    public static void main(String[] args) {
        launch(args);
    }
}