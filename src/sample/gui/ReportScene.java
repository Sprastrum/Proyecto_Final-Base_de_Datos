package sample.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.logic.entities.Report;
import sample.logic.services.IPersonaServices;
import sample.logic.services.IReportServices;
import sample.logic.services.implementation.ReportServices;

/**
 * Scene that show all the reports created.
 */
public class ReportScene {

    private Scene reportScene;
    private Stage stage;
    private GridPane pane;
    private IReportServices reportServices;
    private static final String SPACE = ("              \n");

    private Text titleAgeMenor, titleAgeMayor, titleSexMan, titleSexWoman, titleCivil, titlePublicEmployees, titleCivilDeaths,
            titlePublicEmployeesDeaths, titleDepartment;
    private Label ageMenor, ageMayor, sexMan, sexWoman, civil, publicEmployees, civilDeaths, publicEmployeesDeaths, department;

    /**
     *
     * @param personaServices
     * @param ownerStage
     */
    public ReportScene(IPersonaServices personaServices, Stage ownerStage) {
        stage = new Stage();
        this.reportServices = new ReportServices(personaServices);

        setUp();

        stage.setTitle("Resumen");
        stage.initOwner(ownerStage);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(reportScene);
        stage.showAndWait();
    }

    /**
     * Method that calls other sub methods for setting up the report stage and scene.
     */
    public void setUp() {
        setUpText();
        setUpPane();

        reportScene = new Scene(pane, 860, 450);
    }

    /**
     * Sets up all the report information in the local variables
     */
    private void setUpText() {
        ageMenor = new Label();
        ageMenor.setText((int) reportServices.getReportByAge(true).getCount() + "%");
        ageMenor.setFont(DataScene.FONT_TITLE);
        titleAgeMenor = new Text();
        titleAgeMenor.setText(reportServices.getReportByAge(true).getDescription());
        titleAgeMenor.setFont(DataScene.FONT);

        ageMayor = new Label();
        ageMayor.setText((int) reportServices.getReportByAge(false).getCount() + "%");
        ageMayor.setFont(DataScene.FONT_TITLE);
        titleAgeMayor = new Text();
        titleAgeMayor.setText(reportServices.getReportByAge(false).getDescription());
        titleAgeMayor.setFont(DataScene.FONT);

        sexMan = new Label();
        sexMan.setText((int) reportServices.getReportBySex(true).getCount() + "%");
        sexMan.setFont(DataScene.FONT_TITLE);
        titleSexMan = new Text();
        titleSexMan.setText(reportServices.getReportBySex(true).getDescription());
        titleSexMan.setFont(DataScene.FONT);

        sexWoman = new Label();
        sexWoman.setText((int) reportServices.getReportBySex(false).getCount() + "%");
        sexWoman.setFont(DataScene.FONT_TITLE);
        titleSexWoman = new Text();
        titleSexWoman.setText(reportServices.getReportBySex(false).getDescription());
        titleSexWoman.setFont(DataScene.FONT);

        civil = new Label();
        civil.setText((int) reportServices.getReportByPosition(true).getCount() + "%");
        civil.setFont(DataScene.FONT_TITLE);
        titleCivil = new Text();
        titleCivil.setText(reportServices.getReportByPosition(true).getDescription());
        titleCivil.setFont(DataScene.FONT);

        publicEmployees = new Label();
        publicEmployees.setText((int) reportServices.getReportByPosition(false).getCount() + "%");
        publicEmployees.setFont(DataScene.FONT_TITLE);
        titlePublicEmployees = new Text();
        titlePublicEmployees.setText(reportServices.getReportByPosition(false).getDescription());
        titlePublicEmployees.setFont(DataScene.FONT);

        civilDeaths = new Label();
        civilDeaths.setText(String.valueOf((int) reportServices.getReportByDeaths(true).getCount()));
        civilDeaths.setFont(DataScene.FONT_TITLE);
        titleCivilDeaths = new Text();
        titleCivilDeaths.setText(reportServices.getReportByDeaths(true).getDescription());
        titleCivilDeaths.setFont(DataScene.FONT);

        publicEmployeesDeaths = new Label();
        publicEmployeesDeaths.setText(String.valueOf((int) reportServices.getReportByDeaths(false).getCount()));
        publicEmployeesDeaths.setFont(DataScene.FONT_TITLE);
        titlePublicEmployeesDeaths = new Text();
        titlePublicEmployeesDeaths.setText(reportServices.getReportByDeaths(false).getDescription());
        titlePublicEmployeesDeaths.setFont(DataScene.FONT);

        department = new Label();
        department.setText(reportServices.getReportByMayorDepartment().getInformation());
        department.setFont(DataScene.FONT_TITLE);
        titleDepartment = new Text();
        titleDepartment.setText(reportServices.getReportByMayorDepartment().getDescription());
        titleDepartment.setFont(DataScene.FONT);
    }

    /**
     * Creates and configures the GridPane. Adds all the reports information
     */
    private void setUpPane() {
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        pane.add(ageMenor, 0, 0);
        pane.add(titleAgeMenor, 0, 1);

        pane.add(new Text(SPACE), 1, 0);

        pane.add(ageMayor, 2, 0);
        pane.add(titleAgeMayor, 2, 1);

        pane.add(new Text(SPACE), 3, 0);

        pane.add(sexMan, 4, 0);
        pane.add(titleSexMan, 4, 1);

        pane.add(new Text(SPACE), 5, 0);

        pane.add(sexWoman, 6, 0);
        pane.add(titleSexWoman, 6, 1);

        pane.add(new Text(SPACE), 0, 2);

        pane.add(civil, 0, 3);
        pane.add(titleCivil, 0, 4);

        pane.add(publicEmployees, 2, 3);
        pane.add(titlePublicEmployees, 2, 4);

        pane.add(civilDeaths, 4, 3);
        pane.add(titleCivilDeaths, 4, 4);

        pane.add(publicEmployeesDeaths, 6, 3);
        pane.add(titlePublicEmployeesDeaths, 6, 4);

        pane.add(new Text(SPACE), 0, 5);

        pane.add(department, 2, 6);
        pane.add(titleDepartment, 2, 7);
    }
}
