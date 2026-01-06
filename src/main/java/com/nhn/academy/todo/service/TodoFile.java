package com.nhn.academy.todo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import com.nhn.academy.todo.model.Todo;
import com.nhn.academy.todo.model.Category.Category1;
import com.nhn.academy.todo.model.Priority.Priority1;

public class TodoFile {
    private String filename;
    private ArrayList<Todo> arr1;
    private int nextId;

    public TodoFile(String filename, ArrayList<Todo> arr1){
        this.filename=filename;
        this.arr1=arr1;
        this.nextId=nextId;
    }

    public void loadFromFile() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + filename);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                int id = Integer.parseInt(temp[0]);
                String title = temp[1];
                int hours = Integer.parseInt(temp[2]);
                boolean done = Boolean.parseBoolean(temp[3]);
                Category1 category = Category1.valueOf(temp[4]);
                Priority1 priority = Priority1.valueOf(temp[5]);
                arr1.add(new Todo(title, hours, done, category, priority));

                if (id > maxId) {
                    maxId = id;
                }
            }

            nextId = maxId + 1;

            System.out.println("파일 로드 완료: " + filename + " (" + arr1.size() + "건)");
            System.out.println("===TODO 목록===");

            for (Todo t : arr1) {
                System.out.println(t.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            StringBuilder sb = new StringBuilder();
            for (Todo t : arr1) {
                sb.append(t.getId()).append(",")
                        .append(t.getTitle()).append(",")
                        .append(t.getHours()).append(",")
                        .append(t.isDone()).append(",")
                        .append(t.getCategory()).append(",")
                        .append(t.getPriority()).append("\n");
            }
            writer.write(sb.toString());
            System.out.println("파일 저장 완료: " + filename + " (" + arr1.size() + "건)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
