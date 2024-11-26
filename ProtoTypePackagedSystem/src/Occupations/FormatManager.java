package Occupations;

public interface FormatManager {
    static String stringFixFormat(String target){
        target = target.replace("€", "");
        target = target.replaceAll("\"","");
        target = target.replace(",","");
        return target;
    }
}
