package dao;

import models.Task;
import org.sql2o.*;

import java.util.List;

public class sql2oTaskDao implements TaskDao{

    private final Sql2o sql2o;

    public sql2oTaskDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Task task){
        String sql = "INSERT INTO tasks (description) VALUES (:description)"; //raw sql

        try(Connection con = sql2o.open()){
           int id = (int) con.createQuery(sql, true)
                   .bind(task).executeUpdate().getKey(); //make a new variable

            task.setId(id);
        } catch (Sql2oException e){
            System.out.println(e);
        }
    }
    @Override
    public List<Task> getAll(){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tasks").executeAndFetch(Task.class);
        }
    }
    @Override
    public Task findById(int id){
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM tasks WHERE id = :id")
                    .addParameter("id", id).executeAndFetchFirst(Task.class);
        }
    }
}
