import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToJava {
    public JsonToJava() {}

    public Object dumpJsonToObject(File jsonFile) throws IOException {
        String str = fileToString(jsonFile);
        String[] array = stringToArray(str);
        if(str.equals(""))
            return null;
        else if(array.length==1) { //String or Int
            String[] tmpArray = array[0].split(":"); //on récupere le content
            tmpArray[1] = tmpArray[1].substring(1); //on enleve les ""
            tmpArray[1] = tmpArray[1].substring(0,tmpArray[1].length()-1); //on enleve les ""

            if (tmpArray[1].chars().allMatch(Character::isDigit))  //Case 1 : Int
                 return Integer.parseInt(tmpArray[1]);
            else  //Case 2 : String
                return tmpArray[1];
        } else{ //Case 3 : Human
            String tmpString1 = array[0];
            String tmpString2 = array[1];
            String firstname, lastname;
            tmpString1 = tmpString1.replace("\"", "");
            tmpString2 = tmpString2.replace("\"", "");
            firstname = tmpString1.split(":")[1];
            lastname = tmpString2.split(":")[1];
            return new Human(firstname, lastname);
        }
    }

    public String fileToString(File jsonFile) throws IOException {
        return new String(Files.readAllBytes(Paths.get(jsonFile.getPath())));
    }

    public String[] stringToArray(String values) throws IOException {
        String val = values;
        String[] array = new String[1];
        if (val.length()==0) {
            array[0] = val;
            return array;
        } else {
            val = val.substring(1); // premiere accolade
            val = val.substring(0, val.length() - 1); //deuxieme accolade
            return val.split(",", -1);
        }
    }
}
