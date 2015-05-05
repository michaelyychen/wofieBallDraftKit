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
import java.math.BigDecimal;
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
import static wofieballdraftkit.WBDK_StartUpConstants.PATH_DRAFT;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.FantasyTeam;
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
    String JSON_BB = "BB";
    String JSON_K = "K";
    String JSON_EXT = ".json";
    String SLASH = "/";
    String JSON_FANTASYTEAM = "FANTASY_TEAM";
    String JSON_FANTASYTEAMS = "FANTASY_TEAMS";
    String JSON_CONTRACT = "CONTRACT";
    String JSON_POSITION = "POSITION";
    String JSON_SALARY = "SALARY";
    String JSON_OWNER = "OWNER";
    String JSON_DRAFT = "DRAFT";
    String JSON_PLAYERS = "PLAYERS";
    String JSON_PROTEAM = "PRO_TEAM";
    String JSON_POSITIONS = "POSITIONS";
    String JSON_RW = "RW";
    String JSON_HRSV = "HRSV";
    String JSON_RBIK = "RBIK";
    String JSON_SBERA = "SBERA";
    String JSON_BAWHIP = "BAWHIP";
    String JSON_ESTIMATED = "ESTIMATED";
    String JSON_EXTRAPLAYERS = "EXTRA PLAYERS";
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
        // BUILD THE FILE PATH
        
        String jsonFilePath = PATH_DRAFT+SLASH+draftToSave.getDraftName() + JSON_EXT;
        
        // INIT THE WRITER
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os);  
        
        // MAKE A JSON ARRAY FOR THE PAGES ARRAY
        JsonArray teamsJsonArray = makeTeamsJsonArray(draftToSave.getTeamList());
        JsonArray playerJsonArray = makesFantasyPlayerArray(draftToSave.getTeamList());
        JsonArray extraPlayerArray = makesExtraPlayerArray(draftToSave.getExtraPlayerList());
        // NOW BUILD THE COURSE USING EVERYTHING WE'VE ALREADY MADE
        JsonObject courseJsonObject = Json.createObjectBuilder()
                                    .add(JSON_DRAFT, draftToSave.getDraftName())
                                    .add(JSON_FANTASYTEAMS, teamsJsonArray)
                                    .add(JSON_PLAYERS ,playerJsonArray )
                                   // .add(JSON_EXTRAPLAYERS, extraPlayerArray)
                                        
                .build();
        
        // AND SAVE EVERYTHING AT ONCE
        jsonWriter.writeObject(courseJsonObject);
    }    
    
    @Override
    public void loadDraft(Draft draftToload, String jsonFilePath) throws IOException {
         // LOAD THE JSON FILE WITH ALL THE DATA
        JsonObject json = loadJSONFile(jsonFilePath);
        
        // NOW LOAD THE DRAFT
        draftToload.setDraftName(json.getString(JSON_DRAFT));
               
        // GET THE FantasyTeams TO INCLUDE 
        draftToload.clearTeamList();
        JsonArray jsonFTeamArray = json.getJsonArray(JSON_FANTASYTEAMS);
        for (int i = 0; i < jsonFTeamArray.size(); i++){
            JsonObject jso = jsonFTeamArray.getJsonObject(i);
            FantasyTeam team = new FantasyTeam();
            team.setTeamName(jso.getString(JSON_FANTASYTEAM));
            team.setOwner(jso.getString(JSON_OWNER));
            draftToload.getTeamList().add(team);
                   }
        
        JsonArray jsonPlayersArray = json.getJsonArray(JSON_PLAYERS);
        for (int i = 0; i < jsonPlayersArray.size(); i++){
            JsonObject jso = jsonPlayersArray.getJsonObject(i);
            Player p = new Player();
            p.setFantasyTeam(jso.getString(JSON_FANTASYTEAM));
            p.setPosition(jso.getString(JSON_POSITION));
            p.setFirstName(jso.getString(JSON_FIRSTNAME));
            p.setLastName(jso.getString(JSON_LASTNAME));
            p.setProTeam(jso.getString(JSON_PROTEAM));
            p.setQualifyPosition(jso.getString(JSON_POSITIONS));
            
            p.setRW(Integer.valueOf(jso.getString(JSON_RW)));
            p.setHRSV(Integer.valueOf(jso.getString(JSON_HRSV)));
            p.setRBIK(Integer.valueOf(jso.getString(JSON_RBIK)));
            p.setSBERA(Double.valueOf(jso.getString(JSON_SBERA)));
            p.setBAWHIP(Double.valueOf(jso.getString(JSON_BAWHIP)));
            
            
            p.setEstimated(jso.getString(JSON_LASTNAME));
            p.setContract(jso.getString(JSON_CONTRACT));
            p.setSalary(jso.getInt(JSON_SALARY));
            
            p.setNotes(jso.getString(JSON_NOTES));
            p.setBirth(jso.getString(JSON_BIRTH));
            p.setNation(jso.getString(JSON_NATION));
            
            draftToload.getTeamByName(p.getFantasyTeam()).getTeamPlayer().add(p);
            
        }
        

        
        
    }
        
    @Override
    public ArrayList<Hitter> loadHitterData(String filePath, String arrayName) throws IOException {
        
        JsonObject json = loadJSONFile(filePath);
        ArrayList<Hitter> items = new ArrayList();
        JsonArray jsonArray = json.getJsonArray(arrayName);
        
        for ( int i = 0; i < jsonArray.size();i++) {
        
   
            
        Hitter a = new Hitter(jsonArray.getJsonObject(i).getString(JSON_LASTNAME),
                                jsonArray.getJsonObject(i).getString(JSON_FIRSTNAME));
        a.setProTeam(jsonArray.getJsonObject(i).getString(JSON_TEAM));
        
        double BA = Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_H)) / 
                            Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_AB));
               
        a.setBA(      ((double)((int)(BA*100)))/100  );

        a.setQualifyPosition(jsonArray.getJsonObject(i).getString(JSON_QP));
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
        a.setProTeam(jsonArray.getJsonObject(i).getString(JSON_TEAM));
        
        double ERA = 9*(Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_ER)) / 
                         Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_IP)));
        double WHIP = (
                        (Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_BB))+ 
                        Double.valueOf(jsonArray.getJsonObject(i).getString(JSON_H)))/
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
        a.setQualifyPosition("P");
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
    private JsonObject makeTeamsObject(FantasyTeam t) {
        JsonObject jso = Json.createObjectBuilder().add(JSON_FANTASYTEAM,t.getTeamName())
                                             .add(JSON_OWNER,t.getOwner()).build();
                return jso;
    }    
    private JsonArray makeTeamsJsonArray(ObservableList<FantasyTeam> teamList) {
     JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (FantasyTeam t : teamList) {
           jsb.add(makeTeamsObject(t));
                
        }
        JsonArray jA = jsb.build();
        return jA;        
    }
    
    
    private JsonObject makePlayerObejct(Player p) {
  
        JsonObject jso = Json.createObjectBuilder().add(JSON_FANTASYTEAM,p.getFantasyTeam())
                                            .add(JSON_POSITION,p.getPosition())    
                                            .add(JSON_FIRSTNAME,p.getFirstName())
                                            .add(JSON_LASTNAME,p.getLastName())
                                            .add(JSON_PROTEAM, p.getProTeam())
                                            .add(JSON_POSITIONS, p.getQualifyPosition())
                                            .add(JSON_RW, Integer.toString(p.getRW()))
                                            .add(JSON_HRSV, Integer.toString(p.getHRSV()))
                                            .add(JSON_RBIK, Integer.toString(p.getRBIK()))
                                            .add(JSON_SBERA, Double.toString(p.getSBERA()))
                                            .add(JSON_BAWHIP, Double.toString(p.getBAWHIP()))
                                            .add(JSON_ESTIMATED, p.getEstimated())
                                            .add(JSON_CONTRACT,p.getContract())
                                            .add(JSON_BIRTH, p.getBirth())
                                            .add(JSON_NOTES, p.getNotes())
                                            .add(JSON_NATION, p.getNation())
                                            .add(JSON_SALARY,p.getSalary()).build();
                                            
                return jso;
    }
    private JsonArray makesFantasyPlayerArray(ObservableList<FantasyTeam> teamList) {
     JsonArrayBuilder jsb = Json.createArrayBuilder();
     
        for (FantasyTeam t : teamList) {
            for(int i = 0; i < t.getTeamPlayer().size(); i++){
             Player p = t.getTeamPlayer().get(i);
             
                jsb.add(makePlayerObejct(p));  
           
            }
        }
        JsonArray jA = jsb.build();
        return jA;        
    }
    
        private JsonArray makesExtraPlayerArray(ArrayList<Player> teamList) {
     JsonArrayBuilder jsb = Json.createArrayBuilder();
     
        
            for(int i = 0; i < teamList.size(); i++){
             Player p = teamList.get(i);
             
                jsb.add(makePlayerObejct(p));  
           
            }
        
        JsonArray jA = jsb.build();
        return jA;        
    }


    
    
    
    
}