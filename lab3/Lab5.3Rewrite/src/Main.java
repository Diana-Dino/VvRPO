import java.util.*;

public class Main {
    // Базовая стипендия
    private static final double BASE_SCHOLARSHIP = 150.0;

    public static void main(String[] args) {
        // Создаем список студентов
        List<Student> students = createStudents();

        // [РЕФАКТОРИНГ] Логика вывода разбита на отдельные методы для лучшей читаемости
        printAllScholarships(students);
        printHighScholarshipStudents(students);
        printContractStudents(students);
        printAverageGpaStats(students);
    }

    // [РЕФАКТОРИНГ] Методы выделены из main() для лучшей организации кода

    private static void printAllScholarships(List<Student> students) {
        System.out.println("[---] Общий расчет стипендий [---]");

        for (Student student : students) {
            double scholarship = student.calculateScholarship(BASE_SCHOLARSHIP);

            String studentType;
            if (student instanceof ContractStudent) {
                studentType = "Платник";
            } else {
                studentType = "Бюджетник";
            }

            System.out.println("Студент: " + student.getFullName() +
                    " | Тип: " + studentType +
                    " | Средний балл: " + String.format("%.2f", student.getAverageGrade()) +
                    " | Сдал в срок: " + student.getSessionResult().isPassedOnTime() +
                    " | Стипендия: " + scholarship + " руб.");
        }
    }

    // Задача 1.4
    private static void printHighScholarshipStudents(List<Student> students) {
        System.out.println("\n[---] Студенты со стипендией, увеличенной на 25% [---]");

        int count = 0;

        for (Student student : students) {
            if (student instanceof BudgetStudent) {
                BudgetStudent budgetStudent = (BudgetStudent) student;

                double scholarship = budgetStudent.calculateScholarship(BASE_SCHOLARSHIP);

                // [РЕФАКТОРИНГ] Исправлено сравнение double с помощью Math.abs() для учета погрешности вычислений
                double expectedScholarship = BASE_SCHOLARSHIP * 1.25;
                boolean hasIncreasedScholarship = Math.abs(scholarship - expectedScholarship) < 0.01;

                if (hasIncreasedScholarship && budgetStudent.getSessionResult().isPassedOnTime()) {
                    System.out.println(budgetStudent.toString() +
                            " | Стипендия: " + scholarship + " руб.");

                    count++;

                    // [РЕФАКТОРИНГ] Добавлено явное ограничение счетчика до 10 студентов
                    if (count >= 10) {
                        break;
                    }
                }
            }
        }

        if (count == 0) {
            System.out.println("Нет студентов с повышенной на 25% стипендией.");
        }
    }

    // Задача 1.5
    private static void printContractStudents(List<Student> students) {
        System.out.println("\n[---] Студенты на платной основе [---]");

        int count = 0;

        for (Student student : students) {
            if (student instanceof ContractStudent) {
                System.out.println(student.toString());
                count++;
            }
        }

        if (count == 0) {
            System.out.println("Студенты на платной основе не найдены.");
        }
    }

    // Задача 1.6
    private static void printAverageGpaStats(List<Student> students) {
        System.out.println("\n[---] Средний балл по всем студентам [---]");

        double totalGpa = 0.0;
        int studentCount = 0;

        for (Student student : students) {
            totalGpa += student.getAverageGrade();
            studentCount++;
        }

        if (studentCount > 0) {
            double overallAverage = totalGpa / studentCount;
            System.out.println("Общий средний балл по " + studentCount +
                    " студентам: " + String.format("%.2f", overallAverage));
        } else {
            System.out.println("Список студентов пуст.");
        }
    }

    // Метод для создания тестовых данных
    private static List<Student> createStudents() {
        List<Student> students = new ArrayList<>();

        // Создаем 10 бюджетных студентов
        for (int i = 1; i <= 10; i++) {
            SessionResult sr = new SessionResult(true);
            sr.addGrade("Математика", 8 + i % 3);
            sr.addGrade("Физика", 7 + i % 4);
            sr.addGrade("Программирование", 9);
            students.add(new BudgetStudent("Иван" + i, "Иванов", "BUD" + (100 + i), sr));
        }

        // Создаем 5 платных студентов
        for (int i = 1; i <= 5; i++) {
            SessionResult sr = new SessionResult(i % 3 != 0);
            sr.addGrade("Математика", 6 + i % 5);
            sr.addGrade("Экономика", 7 + i % 4);
            sr.addGrade("Менеджмент", 8);
            students.add(new ContractStudent("Петр" + i, "Петров", "CNT" + (200 + i), sr));
        }

        // Добавляем студентов без стипендии (не сдал в срок)
        SessionResult badSession = new SessionResult(false);
        badSession.addGrade("Математика", 9);
        badSession.addGrade("Физика", 10);
        students.add(new BudgetStudent("Алексей", "Сидоров", "BUD999", badSession));

        // Добавляем студента с низкими оценками
        SessionResult lowGrades = new SessionResult(true);
        lowGrades.addGrade("Математика", 4);
        lowGrades.addGrade("Физика", 5);
        lowGrades.addGrade("Программирование", 6);
        students.add(new BudgetStudent("Дмитрий", "Кузнецов", "BUD888", lowGrades));

        return students;
    }
}

