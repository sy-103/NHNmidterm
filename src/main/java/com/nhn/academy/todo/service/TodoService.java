package com.nhn.academy.todo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.nhn.academy.todo.model.Todo;
import com.nhn.academy.todo.model.Category.Category1;
import com.nhn.academy.todo.model.Priority.Priority1;

public class TodoService {
    private ArrayList<Todo> todoList;

    public TodoService(ArrayList<Todo> todoList){
        this.todoList=todoList;
    }

    public void add(Todo a){
        todoList.add(a);
    }

    public void getAll(){
        for (int i=0; i<todoList.size(); i++){
            System.out.println("["+(i+1)+"] "+todoList.get(i).toString());
        }
    }

    public void getEachCat(Category1 c){
        int i=0;
        for (Todo t: todoList){
            if (t.getCategory()==c){
                System.out.println("["+(i+1)+"] "+todoList.get(i).toString());
            } 
            i++;
        }
    }

    public void getEachPri(Priority1 p){
        for (int j = 0; j < todoList.size(); j++) {
            Todo t = todoList.get(j);
            if (t.getPriority()==p){
                System.out.println("["+(j+1)+"] "+todoList.get(j).toString());
            } 
        }
    }

    

    public List<Todo> getOverDue(){
        LocalDate today = LocalDate.now();

        return todoList.stream()
            .filter(todo -> !todo.isDone())
            .filter(todo -> todo.getDueDate().isBefore(today))
            .collect(Collectors.toList());
    }
}
