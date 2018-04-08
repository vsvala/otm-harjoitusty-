/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitme.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import fitme.domain.Diary;
import fitme.domain.User;
/**
 *
 * @author svsv
 */
public class FileDiaryDao implements DiaryDao{
  public List<Diary> todos;
    private String file;

    public FileDiaryDao(String file, UserDao users) throws Exception {
        todos = new ArrayList<>();
        this.file = file;
        try {
            Scanner reader = new Scanner(new File(file));
            while (reader.hasNextLine()) {
                String[] parts = reader.nextLine().split(";");
                int id = Integer.parseInt(parts[0]);
                boolean done = Boolean.parseBoolean(parts[2]);
//                String kcal = (parts[4]);
                User user = users.getAll().stream().filter(u->u.getUsername().equals(parts[3])).findFirst().orElse(null); 
                Diary todo = new Diary(id, parts[1], done, user);
                todos.add(todo);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        
    }
    
    private void save() throws Exception{
        try (FileWriter writer = new FileWriter(new File(file))) {
            for (Diary todo : todos) {
                writer.write(todo.getId() + ";" + todo.getContent() + ";" + todo.isDelete() + ";" + todo.getUser().getUsername() + "\n");
            }
        }
    }    
    
    private int generateId() {
        return todos.size() + 1;
    }
    
    @Override
    public List<Diary> getAll() {
        return todos;
    }
    
    @Override
    public Diary create(Diary todo) throws Exception {
        todo.setId(generateId());
        todos.add(todo);
        save();
        return todo;
    }   
    
    @Override
    public void setDelete(int id) throws Exception {
        for (Diary t : todos) {
            if (t.getId() == id) {
                t.setDelete();
            }
        }
        save();
    }    


}

