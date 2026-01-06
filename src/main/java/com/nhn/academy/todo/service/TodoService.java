package com.nhn.academy.todo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nhn.academy.todo.model.Todo;
import com.nhn.academy.todo.model.Category;
import com.nhn.academy.todo.model.Category.Category1;
import com.nhn.academy.todo.model.Priority.Priority1;

public class TodoService {
    private List<Todo> todoList;

    public TodoService(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public void add(Todo a) {
        todoList.add(a);
    }

    public void getAll() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + todoList.get(i).toString());
        }
    }

    public void getEachCat(Category1 c) {
        int i = 0;
        for (Todo t : todoList) {
            if (t.getCategory() == c) {
                System.out.println("[" + (i + 1) + "] " + todoList.get(i).toString());
            }
            i++;
        }
    }

    public void getEachPri(Priority1 p) {
        for (int j = 0; j < todoList.size(); j++) {
            Todo t = todoList.get(j);
            if (t.getPriority() == p) {
                System.out.println("[" + (j + 1) + "] " + todoList.get(j).toString());
            }
        }
    }

    public List<Todo> searchByTitle(String keyword) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getTitle().contains(keyword)) {
                result.add(todo);
            }
        }
        return result;
    }

    public List<Todo> searchByTitleIgnoreCase(String keyword) {
        List<Todo> result = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (Todo todo : todoList) {
            if (todo.getTitle().toLowerCase().contains(lowerKeyword)) {
                result.add(todo);
            }
        }
        return result;
    }

    public List<Todo> filterByCategory(Category1 category) {
        return todoList.stream()
                .filter(todo -> todo.getCategory() == category)
                .collect(Collectors.toList());
    }

    public void printCategoryStats() {
        System.out.println("=== 구분별 통계 ===");
        for (Category1 category : Category1.values()) {
            List<Todo> filtered = filterByCategory(category);
            System.out.printf("%s: %d건%n",
                    category.getDisplayName(),
                    filtered.size());
        }
    }

    public List<Todo> getDueToday() {
        LocalDate today = LocalDate.now();
        List<Todo> result = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getDueDate().isEqual(today)) {
                result.add(todo);
            }
        }
        return result;
    }

    public List<Todo> filterByDueDateRange(LocalDate from, LocalDate to) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo : todoList) {
            LocalDate dueDate = todo.getDueDate();
            if (!dueDate.isBefore(from) && !dueDate.isAfter(to)) {
                result.add(todo);
            }
        }
        return result;
    }

    public List<Todo> getDueThisWeek() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(
                TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(
                TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return filterByDueDateRange(startOfWeek, endOfWeek);
    }

    public List<Todo> filterByDone(boolean done) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.isDone() == done) {
                result.add(todo);
            }
        }
        return result;
    }

    public double getCompletionRate() {
        if (todoList.isEmpty()) {
            return 0.0;
        }
        int completed = filterByDone(true).size();
        return (double) completed / todoList.size() * 100;
    }

    public List<Todo> search(String keyword, Category1 category, Boolean done) {
        List<Todo> result = new ArrayList<>();
        for (Todo todo : todoList) {
            if (keyword != null && !keyword.isEmpty()) {
                if (!todo.getTitle().contains(keyword)) {
                    continue;
                }
            }
            if (category != null) {
                if (todo.getCategory() != category) {
                    continue;
                }
            }
            if (done != null) {
                if (todo.isDone() != done) {
                    continue;
                }
            }
            result.add(todo);
        }
        return result;
    }

    public List<Todo> getOverDue() {
        LocalDate today = LocalDate.now();
        return todoList.stream()
                .filter(todo -> !todo.isDone())
                .filter(todo -> todo.getDueDate().isBefore(today))
                .collect(Collectors.toList());
    }

    public void printSearchResult(List<Todo> results, String searchType) {
        if (results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
            return;
        }
        System.out.printf("=== %s 검색 결과: %d건 ===%n", searchType, results.size());
        for (int i = 0; i < results.size(); i++) {
            Todo todo = results.get(i);
            System.out.printf("%d. [%s] %s (%s)%n",
                    i + 1,
                    todo.isDone() ? "완료" : "미완료",
                    todo.getTitle(),
                    todo.getCategory().getDisplayName());
        }
    }
}
