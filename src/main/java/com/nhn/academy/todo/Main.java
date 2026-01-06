package com.nhn.academy.todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nhn.academy.todo.exception.TodoNotFoundException;
import com.nhn.academy.todo.model.Todo;
import com.nhn.academy.todo.model.Category.Category1;
import com.nhn.academy.todo.model.Priority.Priority1;
import com.nhn.academy.todo.service.TodoService;
import com.nhn.academy.todo.service.TodoFile;


public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Todo> arr1 = new ArrayList<>();
        TodoService service = new TodoService(arr1);
        TodoFile todoFile = new TodoFile("todos.csv",arr1);

        todoFile.loadFromFile();

        while(true){
            System.out.println("===TODO 앱===");
            System.out.println("1. 등록");
            System.out.println("2. 조회");
            System.out.println("3. 수정");
            System.out.println("4. 삭제");
            System.out.println("0. 종료");
            try{
                System.out.print("선택> ");
                String input = reader.readLine();

                switch (input) {
                    case "1":
                        System.out.println("===TODO 등록===");
                        System.out.print("제목> ");
                        String input1 = reader.readLine();
                        System.out.print("예상 시간> ");
                        String input1_1 = reader.readLine();
                        int num1_1 = Integer.parseInt(input1_1);
                        System.out.print("구분 (1:WORK, 2:STUDY, 3:PERSONAL, 4:HEALTH, 5:OTHER) > ");
                        String input1_2 = reader.readLine();
                        Category1 cat=null;
                        switch (input1_2) {
                            case "1":
                                cat= Category1.WORK;
                                break;
                            case "2":
                                cat= Category1.STUDY;
                                break;
                            case "3":
                                cat= Category1.PERSONAL;
                                break;
                            case "4":
                                cat= Category1.HEALTH;
                                break;
                            case "5":
                                cat= Category1.OTHER;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                                break;
                            }
                        System.out.print("중요도 (1:LOW, 2:MEDIUM, 3:HIGH) > ");
                        String input1_3 = reader.readLine();
                        Priority1 pri=null;
                        switch (input1_3) {
                            case "1":
                                pri=Priority1.LOW;
                                break;
                            case "2":
                                pri=Priority1.MEDIUM;
                                break;
                            case "3":
                                pri=Priority1.HIGH;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                                break;
                        }
                        Todo temp = new Todo(input1, num1_1, false, cat, pri);
                        service.add(temp);
                        System.out.println("등록 완료!!!");
                        break;
                    case "2":
                        boolean a=true;
                        while (a==true) {
                            System.out.println("===조회 메뉴===");
                            System.out.println("1. 전체 조회");
                            System.out.println("2. 구분별 조회");
                            System.out.println("3. 중요도별 조회");
                            System.out.println("0. 이전");
                            System.out.print("선택> ");
                            String input2 = reader.readLine();
                            switch (input2) {
                                case "1":
                                    service.getAll();
                                    break;
                                case "2":
                                    System.out.print("구분 (1:WORK, 2:STUDY, 3:PERSONAL, 4:HEALTH, 5:OTHER) > ");
                                    String input2_2 = reader.readLine();
                                    Category1 cat2=null;
                                    switch (input2_2) {
                                        case "1":
                                            cat2= Category1.WORK;
                                            break;
                                        case "2":
                                            cat2= Category1.STUDY;
                                            break;
                                        case "3":
                                            cat2= Category1.PERSONAL;
                                            break;
                                        case "4":
                                            cat2= Category1.HEALTH;
                                            break;
                                        case "5":
                                            cat2= Category1.OTHER;
                                            break;
                                        default:
                                            System.out.println("잘못된 입력입니다.");
                                            break;
                                        }
                                    service.getEachCat(cat2);
                                    break;
                                case "3":
                                    System.out.print("중요도 (1:LOW, 2:MEDIUM, 3:HIGH) > ");
                                    String input2_3 = reader.readLine();
                                    Priority1 pri2=null;
                                    switch (input2_3) {
                                        case "1":
                                            pri2=Priority1.LOW;
                                            break;
                                        case "2":
                                            pri2=Priority1.MEDIUM;
                                            break;
                                        case "3":
                                            pri2=Priority1.HIGH;
                                            break;
                                        default:
                                            System.out.println("잘못된 입력입니다.");
                                            break;
                                    }
                                    service.getEachPri(pri2);
                                    break;
                                case "0":
                                    a=false;
                                    break;
                                default:
                                    System.out.println("잘못된 입력입니다.");
                                    break;
                            }
                        }
                        break;
                    case "3":
                        System.out.println("===TODO 수정===");
                        System.out.print("수정 할 TODO ID> ");
                        String input3 = reader.readLine();
                        int num3 = Integer.parseInt(input3);
                        arr1.remove(num3-1);
                        System.out.print("새 제목> ");
                        String input3_1 = reader.readLine();
                        System.out.print("새 예상 시간> ");
                        String input3_2 = reader.readLine();
                        int num3_2 = Integer.parseInt(input3_2);
                        System.out.print("새 구분 (1:WORK, 2:STUDY, 3:PERSONAL, 4:HEALTH, 5:OTHER) > ");
                        String input3_3 = reader.readLine();
                        Category1 cat3=null;
                        switch (input3_3) {
                            case "1":
                                cat3= Category1.WORK;
                                break;
                            case "2":
                                cat3= Category1.STUDY;
                                break;
                            case "3":
                                cat3= Category1.PERSONAL;
                                break;
                            case "4":
                                cat3= Category1.HEALTH;
                                break;
                            case "5":
                                cat3= Category1.OTHER;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                                break;
                            }
                        System.out.print("중요도 (1:LOW, 2:MEDIUM, 3:HIGH) > ");
                        String input3_4 = reader.readLine();
                        Priority1 pri3=null;
                        switch (input3_4) {
                            case "1":
                                pri3=Priority1.LOW;
                                break;
                            case "2":
                                pri3=Priority1.MEDIUM;
                                break;
                            case "3":
                                pri3=Priority1.HIGH;
                                break;
                            default:
                                System.out.println("잘못된 입력입니다.");
                                break;
                        }
                        Todo temp3 = new Todo(input3_1, num3_2, false, cat3, pri3);
                        service.add(temp3);
                        System.out.println("수정 완료!!!");
                        break;
                    case "4":
                        System.out.println("===TODO 삭제===");
                        System.out.print("삭제 할 TODO ID> ");
                        String input4 = reader.readLine();
                        int num4 = Integer.parseInt(input4);
                        arr1.remove(num4-1);
                        System.out.println("삭제 완료!!!");
                        break;
                    case "0":
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다.");
                        break;
                }
            }
            catch (IOException e){
                System.out.println("입출력 오류입니다.");
            }
            catch (NumberFormatException e){
                System.out.println("숫자를 입력해주세요.");
            }
            catch(IllegalArgumentException e){
                System.out.println("잘못된 입력입니다.");
            }
            catch(TodoNotFoundException e){
                System.out.println(e.getMessage());
            }

        todoFile.saveToFile();

        }
    }
}