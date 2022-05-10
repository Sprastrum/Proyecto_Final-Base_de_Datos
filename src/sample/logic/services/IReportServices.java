package sample.logic.services;

import sample.logic.entities.Report;

import java.util.List;
import java.util.Map;

public interface IReportServices {
    Map<String, Report> getReportByDepartment();
    Report getReportByAge(boolean isMenor);
    Report getReportByMayorDepartment();
    Report getReportBySex(boolean isMan);
    Report getReportByPosition(boolean isCivil);
    Report getReportByDeaths(boolean isCivil);
}
