import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sample.logic.entities.Persona;
import sample.logic.services.IPersonaServices;
import sample.logic.services.PersonaException;
import sample.logic.services.implementation.PersonaServices;
import sample.logic.services.implementation.ReportServices;

import java.io.IOException;

public class ReportServicesTest {

    private IPersonaServices personaServices = new PersonaServices();
    ReportServices reportServices = new ReportServices(personaServices);
    Persona persona;

    public void setUp() throws PersonaException {
        Assertions.assertNotNull(personaServices.getAll());
        persona = new Persona("Karla" ,"Santos" ,"18" ,"Femenino" ,
                "Amazonas", "Herido", "Abuso Policial",
                ConstantsForTests.VALID_ID);
    }

    @Test
    public void ShouldReportByDepartment() {
        Assertions.assertNotNull(reportServices.getReportByDepartment());
    }

    @Test
    public void ShouldReportByAge() throws PersonaException, IOException {
        setUp();

        Assertions.assertNotNull(personaServices.getAll());
        Assertions.assertNotNull(reportServices.getReportByAge(true));
        Assertions.assertNotNull(reportServices.getReportByAge(false));
        Assertions.assertEquals("Victimas Menores de\nEdad", reportServices.getReportByAge(true).getDescription());
        Assertions.assertEquals("Victimas Mayores de\nEdad", reportServices.getReportByAge(false).getDescription());
        Assertions.assertEquals("edad", reportServices.getReportByAge(true).getInformation());
        Assertions.assertEquals(25, reportServices.getReportByAge(true).getCount());
        Assertions.assertEquals(75, reportServices.getReportByAge(false).getCount());

        personaServices.insert(persona);

        Assertions.assertEquals(20, reportServices.getReportByAge(true).getCount());
        Assertions.assertEquals(80, reportServices.getReportByAge(false).getCount());

        personaServices.delete(persona);

        Assertions.assertEquals(25, reportServices.getReportByAge(true).getCount());
        Assertions.assertEquals(75, reportServices.getReportByAge(false).getCount());
    }

    @Test
    public void ShouldReportByMayorDepartment() throws PersonaException, IOException {
        setUp();

        Assertions.assertNotNull(reportServices.getReportByMayorDepartment());
        Assertions.assertEquals("Departamento con más\nVictimas", reportServices.getReportByMayorDepartment().getDescription());
        Assertions.assertEquals("Arauca", reportServices.getReportByMayorDepartment().getInformation());
        Assertions.assertEquals(2, reportServices.getReportByMayorDepartment().getCount());

        personaServices.insert(persona);

        Assertions.assertEquals("Amazonas", reportServices.getReportByMayorDepartment().getInformation());
        Assertions.assertEquals(3, reportServices.getReportByMayorDepartment().getCount());

        personaServices.delete(persona);

        Assertions.assertEquals("Arauca", reportServices.getReportByMayorDepartment().getInformation());
        Assertions.assertEquals(2, reportServices.getReportByMayorDepartment().getCount());
    }

    @Test
    public void ShouldReportBySex() throws PersonaException, IOException {
        setUp();

        Assertions.assertNotNull(reportServices.getReportBySex(true));
        Assertions.assertEquals("Victimas de Sexo\nMasculino", reportServices.getReportBySex(true).getDescription());
        Assertions.assertEquals(100, reportServices.getReportBySex(true).getCount());

        Assertions.assertNotNull(reportServices.getReportBySex(false));
        Assertions.assertEquals("Victimas de Sexo\nFemenino", reportServices.getReportBySex(false).getDescription());
        Assertions.assertEquals(0, reportServices.getReportBySex(false).getCount());

        personaServices.insert(persona);

        Assertions.assertEquals(20, reportServices.getReportBySex(false).getCount());
        Assertions.assertEquals(80, reportServices.getReportBySex(true).getCount());

        personaServices.delete(persona);

        Assertions.assertEquals(100, reportServices.getReportBySex(true).getCount());
        Assertions.assertEquals(0, reportServices.getReportBySex(false).getCount());
    }

    @Test
    public void ShouldReportByPosition() throws PersonaException, IOException {
        setUp();

        Assertions.assertNotNull(reportServices.getReportByPosition(true));
        Assertions.assertEquals("Civiles que son\nVictimas", reportServices.getReportByPosition(true).getDescription());
        Assertions.assertEquals(50, reportServices.getReportByPosition(true).getCount());

        Assertions.assertNotNull(reportServices.getReportByPosition(false));
        Assertions.assertEquals("Empleados Públicos\nque son Victimas", reportServices.getReportByPosition(false).getDescription());
        Assertions.assertEquals(50, reportServices.getReportByPosition(false).getCount());

        personaServices.insert(persona);

        Assertions.assertEquals(60, reportServices.getReportByPosition(true).getCount());
        Assertions.assertEquals(40, reportServices.getReportByPosition(false).getCount());

        personaServices.delete(persona);

        Assertions.assertEquals(50, reportServices.getReportByPosition(false).getCount());
        Assertions.assertEquals(50, reportServices.getReportByPosition(true).getCount());
    }

    @Test
    public void ShouldReportByDeaths() throws PersonaException, IOException {
        setUp();

        Assertions.assertNotNull(reportServices.getReportByDeaths(true));
        Assertions.assertEquals("Civiles Muertos en el\nParo", reportServices.getReportByDeaths(true).getDescription());
        Assertions.assertEquals(1, reportServices.getReportByDeaths(true).getCount());

        Assertions.assertNotNull(reportServices.getReportByDeaths(false));
        Assertions.assertEquals("Empleados Públicos\nMuertos en el Paro", reportServices.getReportByDeaths(false).getDescription());
        Assertions.assertEquals(0, reportServices.getReportByDeaths(false).getCount());

        personaServices.insert(persona);

        Assertions.assertEquals(1, reportServices.getReportByDeaths(true).getCount());
        Assertions.assertEquals(0, reportServices.getReportByDeaths(false).getCount());

        personaServices.delete(persona);

        Assertions.assertEquals(1, reportServices.getReportByDeaths(true).getCount());
        Assertions.assertEquals(0, reportServices.getReportByDeaths(false).getCount());
    }

    @AfterEach
    public void eliminatePersona() throws PersonaException {
        setUp();
        try {
            personaServices.delete(persona);
        } catch (PersonaException ignored) {}
    }
}
