package spbstuWorks.work4;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONUtils {
    public static ArrayList<String> parseJson(final String fileName) {
        ArrayList<String> res = new ArrayList<>();

        JSONParser jsonParser = new JSONParser();
        String filePath = "//home//yaroslav//UNIVERSITY//COMPUTER-SCIENCE//" +
                "Java//IdeaProjects//Java-labs//src//com//labs//l4//" + fileName;

        try(FileReader reader = new FileReader(filePath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray commandsList = (JSONArray) obj;
            //System.out.println(commandsList);
            //Iterate over commands array
            for(Object command : commandsList) {
                res.add(parseCommandObject((JSONObject) command));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    private static String parseCommandObject(JSONObject command) {
        String sCommand = (String) command.get("commandName");
        sCommand += " " + (String) command.get("commandInfo");

        return sCommand;
    }
}
