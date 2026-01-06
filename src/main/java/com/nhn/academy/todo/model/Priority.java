package com.nhn.academy.todo.model;

public class Priority {
    public enum Priority1{
        LOW("LOW"),
        MEDIUM("MEDIUM"),
        HIGH("HIGH");

        private final String displayName;

        private Priority1(){
            this.displayName="error";
        }

        private Priority1(String displayName){
            this.displayName=displayName;
        }
    }
}
