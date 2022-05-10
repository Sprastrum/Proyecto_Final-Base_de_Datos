package sample.logic.entities;

import sample.gui.AddScene;
import sample.gui.SetUp;

import java.util.List;

/**
 * Class that standardizes with objects can be exported.
 */
public abstract class Exportable {

    public static final Character CSV = ',';
    public static final Character PCS = ';';
    public static final Character BSC = '|';

    public abstract List<String> toStringList();

    /**
     * method for getting the header of the object
     * @param separateCharacter for the string
     * @return string with the header separated with the given character
     */
    public abstract String getHeader(Character separateCharacter);

    /**
     * Given a Character, it returns the extension.
     * @param characterSeparate for checking
     * @return the extension
     */
    public static String getExtension(Character characterSeparate) {
        return switch (characterSeparate) {
            case ',' -> "csv";
            case ';' -> "pcs";
            case '|' -> "bsc";
            default -> "";
        };
    }

    /**
     * Gets a list of exportable objects and converts them into an specific extension
     * @param characterSeparate of the extension
     * @return String separated by the character given
     */
    public String toExportValue(Character characterSeparate) {
        int count = 0;
        List<String> stringList = this.toStringList();
        StringBuilder stringBuilder = new StringBuilder();

        for(String s : stringList) {
            if(count < SetUp.NUMBER_OF_ITEMS-1) {
                stringBuilder.append(s).append(characterSeparate);
                count++;
            }
            else {
                stringBuilder.append(s);
                count = 0;
            }
        }

        return stringBuilder.toString();
    }
}
