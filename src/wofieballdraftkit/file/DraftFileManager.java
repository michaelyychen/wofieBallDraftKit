/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wofieballdraftkit.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import wofieballdraftkit.data.Draft;
import wofieballdraftkit.data.Hitter;
import wofieballdraftkit.data.Pitcher;
import wofieballdraftkit.data.Player;

/**
 *
 * @author MiChAeL
 */
public interface DraftFileManager {
    public void                              saveDraft(Draft draftToSave) throws IOException;
    public void                              loadDraft(Draft draftToload, String coursePath) throws IOException;
    public ArrayList<Pitcher>                loadPitcherData(String filePath, String arrayName)throws IOException;
    public ArrayList<Hitter>                 loadHitterData(String filePath, String arrayName) throws IOException;

}
