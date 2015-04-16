/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.Hitter;
import wofieballdraftkit.data.Pitcher;
import wofieballdraftkit.data.Player;

/**
 *
 * @author MiChAeL
 */
public class JsonDraftFileManager implements DraftFileManager {
    // JSON FILE READING AND WRITING CONSTANTS
    String JSON_PITCHERS = "PITCHERS";
    String JSON_HITTERS = "HITTERS ";
    String JSON_TEAM = "TEAM";
    String JSON_LASTNAME = "LAST_NAME";
    String JSON_FIRSTNAME = "FIRST_NAME";
    String JSON_QP = "QP";
    String JSON_AB = "AB";
    String JSON_R = "R";
    String JSON_H ="H";
    String JSON_HR = "HR";
    String JSON_RBI = "RBI";
    String JSON_SB = "SB";
    String JSON_NOTES = "NOTES";
    String JSON_BIRTH = "YEAR_OF_BIRTH";
    String JSON_NATION = "NATION_OF_BIRTH";
    String JSON_IP = "IP";
    String JSON_ER = "ER";
    String JSON_W = "W";
    String JSON_SV = "SV";
    //String JSON_H   = "H";
    String JSON_BB = "BB";
    String JSON_K = "K";
    String JSON_EXT = ".json";
    String SLASH = "/";

    /**
     * This method saves all the data associated with a course to
     * a JSON file.
     * 
     * @param courseToSave The course whose data we are saving.
     * 
     * @throws IOException Thrown when there are issues writing
     * to the JSON file.
//     */
    @Override
    public void saveDraft(Draft draftToSave) throws IOException {
//        // BUILD THE FILE PATH
//        String courseListing = "" + courseToSave.getSubject() + courseToSave.getNumber();
//        String jsonFilePath = PATH_COURSES + SLASH + courseListing + JSON_EXT;
//        
//        // INIT THE WRITER
//        OutputStream os = new FileOutputStream(jsonFilePath);
//        JsonWriter jsonWriter = Json.createWriter(os);  
//        
//        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
//        JsonArray pagesJsonArray = makePagesJsonArray(courseToSave.getPages());
//        
//        // AND AN OBJECT FOR THE INSTRUCTOR
//        JsonObject instructorJsonObject = makeInstructorJsonObject(courseToSave.getInstructor());
//        
//        // ONE FOR EACH OF OUR DATES
//        JsonObject startingMondayJsonObject = makeLocalDateJsonObject(courseToSave.getStartingMonday());
//        JsonObject endingFridayJsonObject = makeLocalDateJsonObject(courseToSave.getEndingFriday());
//        
//        // THE LECTURE DAYS ARRAY
//        JsonArray lectureDaysJsonArray = makeLectureDaysJsonArray(courseToSave.getLectureDays());
//        
//        // THE SCHEDULE ITEMS ARRAY
//        JsonArray scheduleItemsJsonArray = makeScheduleItemsJsonArray(courseToSave.getScheduleItems());
//        
//        // THE LECTURES ARRAY
//        JsonArray lecturesJsonArray = makeLecturesJsonArray(courseToSave.getLectures());
//        
//        // THE HWS ARRAY
//        JsonArray hwsJsonArray = makeHWsJsonArray(courseToSave.getAssignments());
//        
//        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
//        JsonObject courseJsonObject = Json.createObjectBuilder()
//                                    .add(JSON_SUBJECT, courseToSave.getSubject().toString())
//                                    .add(JSON_NUMBER, courseToSave.getNumber())
//                                    .add(JSON_TITLE, courseToSave.getTitle())
//                                    .add(JSON_SEMESTER, courseToSave.getSemester().toString())
//                                    .add(JSON_YEAR, courseToSave.getYear())
//                                    .add(JSON_PAGES, pagesJsonArray)
//                                    .add(JSON_INSTRUCTOR, instructorJsonObject)
//                                    .add(JSON_STARTING_MONDAY, startingMondayJsonObject)
//                                    .add(JSON_ENDING_FRIDAY, endingFridayJsonObject)
//                                    .add(JSON_LECTURE_DAYS, lectureDaysJsonArray)
//                                    .add(JSON_SCHEDULE_ITEMS, scheduleItemsJsonArray)
//                                    .add(JSON_LECTURES, lecturesJsonArray)
//                                    .add(JSON_HWS, hwsJsonArray)
//                .build();
//        
//        // AND SAVE EVERYTHING AT ONCE
//        jsonWriter.writeObject(courseJsonObject);
    }
    
    /**
     * Loads the courseToLoad argument using the data found in the json file.
     * 
     * @param courseToLoad Course to load.
     * @param jsonFilePath File containing the data to load.
     * 
     * @throws IOException Thrown when IO fails.
     */
//    @Override
//    public void loadPitcher(Pitcher playerToLoad, String jsonFilePath) throws IOException {
//        // LOAD THE JSON FILE WITH ALL THE DATA
//        JsonObject json = loadJSONFile(jsonFilePath);
//        
//        // NOW LOAD THE COURSE
//        
//        double ERA = 9*(json.getJsonNumber(JSON_ER).doubleValue() / 
//                            json.getJsonNumber(JSON_IP).doubleValue());
//        double WHIP = (json.getJsonNumber(JSON_BB).doubleValue()+ json.getJsonNumber(JSON_H).doubleValue())/
//                        json.getJsonNumber(JSON_IP).doubleValue();
//        
//        
//        playerToLoad.setTeam(json.getString(JSON_TEAM));
//        playerToLoad.setLastName(json.getString(JSON_LASTNAME));
//        playerToLoad.setFirstName(json.getString(JSON_FIRSTNAME));
//        playerToLoad.setERA(ERA);
//        playerToLoad.setWHIP(WHIP);
//        playerToLoad.setK(json.getInt(JSON_K));
//        playerToLoad.setW(json.getInt(JSON_W));
//        playerToLoad.setSv(json.getInt(JSON_SV));
//        playerToLoad.setBirth(json.getInt(JSON_BIRTH));
//        playerToLoad.setNation(json.getString(JSON_NATION));
//        playerToLoad.setNotes(json.getString(JSON_NOTES));
//        
//
//    }
//    
//        @Override
//    public void loadHitter(Hitter playerToLoad, String jsonFilePath) throws IOException {
//        // LOAD THE JSON FILE WITH ALL THE DATA
//        JsonObject json = loadJSONFile(jsonFilePath);
//        
//        // NOW LOAD THE COURSE
//        
//        double BA = (json.getJsonNumber(JSON_H).doubleValue() / 
//                            json.getJsonNumber(JSON_AB).doubleValue());
//
//        
//        
//        playerToLoad.setTeam(json.getString(JSON_TEAM));
//        playerToLoad.setLastName(json.getString(JSON_LASTNAME));
//        playerToLoad.setFirstName(json.getString(JSON_FIRSTNAME));
//        playerToLoad.setR(json.getInt(JSON_R));
//        playerToLoad.setHr(json.getInt(JSON_HR));
//        playerToLoad.setRbi(json.getJsonNumber(JSON_RBI).doubleValue());
//        playerToLoad.setSb(json.getJsonNumber(JSON_SB).intValue());
//        playerToLoad.setBa(BA);
//        playerToLoad.setBirth(json.getInt(JSON_BIRTH));
//        playerToLoad.setNation(json.getString(JSON_NATION));
//        playerToLoad.setNotes(json.getString(JSON_NOTES));
//
//    }
    
    @Override
    public ArrayList<Hitter> loadHitterData(String filePath, String arrayName) throws IOException {
        
        JsonObject json = loadJSONFile(filePath);
        ArrayList<Hitter> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        
        for ( int i = 0; i < jsonArray.size();i++) {
        
   
            
        Hitter a = new Hitter(jsonArray.getJsonObject(i).getString(JSON_LASTNAME),
                                jsonArray.getJsonObject(i).getString(JSON_FIRSTNAME));
        a.setTeam(jsonArray.getJsonObject(i).getString(JSON_TEAM));
        
        double BA = Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_H)) / 
                            Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_AB));
               
        a.setBA(      ((double)((int)(BA*100)))/100  );

        a.setPosition(jsonArray.getJsonObject(i).getString(JSON_QP));
        a.setR(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_R)));
        a.setH(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_H)));
        a.setHR(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_HR)));
        a.setRBI(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_RBI)));
        a.setSB(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_SB)));
        a.setNotes(jsonArray.getJsonObject(i).getString(JSON_NOTES));
        a.setBirth(jsonArray.getJsonObject(i).getString(JSON_BIRTH));
        a.setNation(jsonArray.getJsonObject(i).getString(JSON_NATION));
           
            items.add(a);
         
        }
        return items;
  
    }    
    
    
    
    
    
    
    
    
    
    @Override
    public ArrayList<Pitcher> loadPitcherData(String filePath, String arrayName) throws IOException {
        
        JsonObject json = loadJSONFile(filePath);
        ArrayList<Pitcher> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        
        for ( int i = 0; i < jsonArray.size();i++) {
        
        
            
        Pitcher a = new Pitcher(jsonArray.getJsonObject(i).getString(JSON_LASTNAME),
                                jsonArray.getJsonObject(i).getString(JSON_FIRSTNAME));
        a.setTeam(jsonArray.getJsonObject(i).getString(JSON_TEAM));
        
        double ERA = 9*(Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_ER)) / 
                         Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_IP)));
        double WHIP = (
                        Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_BB))+ 
                        Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_H))/
                        Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_IP))
                );
        a.setERA(      ((double)((int)(ERA*100)))/100  );
       
        a.setW(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_W)));
        a.setWHIP(      ((double)((int)(WHIP*100)))/100  );
        a.setIP(Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_IP)));
        a.setH(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_H)));
        a.setK(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_K)));
        a.setSV(Integer.valueOf(jsonArray.getJsonObject(i).getString(JSON_SV)));
        a.setNotes(jsonArray.getJsonObject(i).getString(JSON_NOTES));
        a.setBirth(jsonArray.getJsonObject(i).getString(JSON_BIRTH));
        a.setNation(jsonArray.getJsonObject(i).getString(JSON_NATION));
        a.setPosition("P");
            items.add(a);
         
        }
        return items;
  
    }    
    
    

    
   
   
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        return json;
    }    
   
    
    // LOADS AN ARRAY OF A SPECIFIC NAME FROM A JSON FILE AND
    // RETURNS IT AS AN ArrayList FULL OF THE DATA FOUND
    private ArrayList<String> loadArrayFromJSONFile(String jsonFilePath, String arrayName) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ArrayList<String> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        for (JsonValue jsV : jsonArray) {
            items.add(jsV.toString());
           
        }
        return items;
    }


    // BUILDS AND RETURNS A JsonArray CONTAINING THE PROVIDED DATA
    public JsonArray buildJsonArray(List<Object> data) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Object d : data) {
           jsb.add(d.toString());
        }
        JsonArray jA = jsb.build();
        return jA;
    }


    // BUILDS AND RETURNS A JsonObject CONTAINING A JsonArray
    // THAT CONTAINS THE PROVIDED DATA

//    public JsonObject buildJsonArrayObject(List<Object> data) {
//        JsonArray jA = buildJsonArray(data);
//        JsonObject arrayObject = Json.createObjectBuilder().add(JSON_SUBJECTS, jA).build();
//        return arrayObject;
//    }
}