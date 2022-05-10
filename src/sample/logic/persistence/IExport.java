package sample.logic.persistence;

import sample.logic.entities.Exportable;

import java.io.FileNotFoundException;
import java.util.List;

public interface IExport {
    void export(List<Exportable> exportableList, Character characterSeparate) throws FileNotFoundException;
}
