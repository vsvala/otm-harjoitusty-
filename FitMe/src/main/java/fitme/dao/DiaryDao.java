/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

/**
 *
 * @author svsv
 */

import java.util.List;
import fitme.domain.Diary;

public interface DiaryDao {
    
     Diary create(Diary diary) throws Exception;

    List<Diary> getAll();

    void setDelete(int id) throws Exception;  //setDone  ...korvattu delete
    
    
}
