package Occupations;

/**
 * Handles formatting for Salaries when being written from A resources CSVFile to an employees data
 */
public interface FormatManager {

    static String stringFixFormat(String target)
    {
        target = target.replace("€", "");
        target = target.replaceAll("\"","");
        target = target.replace(",","");
        return target;
    }
}
