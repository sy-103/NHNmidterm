package com.nhn.academy.todo.model;

public class Category {
    public enum Category1 {
        WORK("WORK"),
        STUDY("STUDY"),
        PERSONAL("PERSONAL"),
        HEALTH("HEALTH"),
        OTHER("OTHER");

        private final String displayName;

        private Category1() {
            this.displayName = "error";
        }

        private Category1(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
