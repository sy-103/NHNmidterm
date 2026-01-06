package com.nhn.academy.todo.model;
import java.time.LocalDate;

import com.nhn.academy.todo.model.Category.Category1;
import com.nhn.academy.todo.model.Priority.Priority1;

public class Todo {
    private static int count=1;

    private int id;
    private String title;
    private int hours;
    private boolean done;
    private Category1 category;
    private Priority1 priority;
    private LocalDate dueDate;

    public Todo(){
        this.id=0;
        this.title="무제";
        this.hours=0;
        this.done=false;
        this.category=null;
        this.priority=null;
    }

    public Todo(String title, int hours, boolean done, Category1 category, Priority1 priority){
        this.id=count++;
        this.title=title;
        this.hours=hours;
        this.done=done;
        this.category=category;
        this.priority=priority;
    }
    public int getId() {
        return id;
    }
    public String getTitle(){
        return title;
    }

    public int getHours(){
        return hours;
    }

    public boolean isDone(){
        return done;
    }

    public Category1 getCategory(){
        return category;
    }

    public Priority1 getPriority(){
        return priority;
    }

    @Override
    public String toString(){
        String status;
        if (done==true){
            status= "[완료]";
        }
        else {
            status="[미완료]";
        }
        return getTitle()+" | "+ getHours()+"시간 | "+ getCategory()+" | "+ getPriority()+ " | "+status;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }
}
