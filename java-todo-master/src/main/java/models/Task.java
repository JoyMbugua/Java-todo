package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Task {

    private String description;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;

    public Task(String description){
        this.description = description;
        this.completed = false;
        this.createdAt = LocalDateTime.now();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return completed == task.completed &&
                id == task.id &&
                description.equals(task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompleted(), getDescription(), getId());
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }



    public boolean getCompleted(){
        return this.completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }



}
