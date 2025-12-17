import java.util.*;

public class Main {
    private static final double BASE_SCHOLARSHIP = 150.0;

    public static void main(String[] args) {
        // Создаем список студентов вручную (без репозитория)
        List<Student> students = createStudents();

        System.out.println("[---] Общий расчет стипендий [---]");
        for (Student s : students) {
            double scholarship = s.calculateScholarship(BASE_SCHOLARSHIP);

            String studentType = "Бюджетник";
            if (s instanceof ContractStudent) studentType = "Платник";

            System.out.println(
                    "Студент: " + s.getFullName() +
                            " | Тип: " + studentType +
                            " | Средний балл: " + String.format("%.2f", s.getAverageGrade()) +
                            " | Сдал в срок: " + s.getSessionResult().isPassedOnTime() +
                            " | Стипендия: " + scholarship + " руб."
            );
        }

        // 1.4: Вывести 10 студентов с повышенной стипендией (увеличенной на 25%)
        System.out.println("\n[---] Студенты со стипендией, увеличенной на 25% [---]");
        int count25 = 0;
        for (Student s : students) {
            if (s instanceof BudgetStudent) {
                BudgetStudent bs = (BudgetStudent) s;
                double scholarship = bs.calculateScholarship(BASE_SCHOLARSHIP);
                if (scholarship == BASE_SCHOLARSHIP * 1.25 && bs.getSessionResult().isPassedOnTime()) {
                    System.out.println(bs.toString() + " | Стипендия: " + scholarship + " руб.");
                    count25++;
                    if (count25 >= 10) break;
                }
            }
        }
        if (count25 == 0) {
            System.out.println("Нет студентов с повышенной на 25% стипендией.");
        }

        // 1.5: Вывести студентов на платной основе
        System.out.println("\n[---] Студенты на платной основе [---]");
        int contractCount = 0;
        for (Student s : students) {
            if (s instanceof ContractStudent) {
                System.out.println(s.toString());
                contractCount++;
            }
        }
        if (contractCount == 0) {
            System.out.println("Студенты на платной основе не найдены.");
        }

        // 1.6: Вывести средний балл по всем студентам
        System.out.println("\n[---] Средний балл по всем студентам [---]");
        double totalGpa = 0;
        int studentCount = 0;

        for (Student s : students) {
            totalGpa += s.getAverageGrade();
            studentCount++;
        }

        if (studentCount > 0) {
            double overallAverage = totalGpa / studentCount;
            System.out.println("Общий средний балл по " + studentCount + " студентам: " +
                    String.format("%.2f", overallAverage));
        } else {
            System.out.println("Список студентов пуст.");
        }
    }

    // Метод для создания тестовых данных (вместо репозитория)
    private static List<Student> createStudents() {
        List<Student> students = new ArrayList<>();

        // Создаем 10 бюджетных студентов с разными оценками
        for (int i = 1; i <= 10; i++) {
            SessionResult sr = new SessionResult(true); // сдали в срок
            sr.addGrade("Математика", 8 + i % 3);
            sr.addGrade("Физика", 7 + i % 4);
            sr.addGrade("Программирование", 9);

            students.add(new BudgetStudent(
                    "Иван" + i,
                    "Иванов",
                    "BUD" + (100 + i),
                    sr
            ));
        }

        // Создаем 5 платных студентов
        for (int i = 1; i <= 5; i++) {
            SessionResult sr = new SessionResult(i % 3 != 0); // не все сдали в срок
            sr.addGrade("Математика", 6 + i % 5);
            sr.addGrade("Экономика", 7 + i % 4);
            sr.addGrade("Менеджмент", 8);

            students.add(new ContractStudent(
                    "Петр" + i,
                    "Петров",
                    "CNT" + (200 + i),
                    sr
            ));
        }

        // Добавляем несколько студентов без стипендии
        SessionResult badSession = new SessionResult(false); // не сдал в срок
        badSession.addGrade("Математика", 9);
        badSession.addGrade("Физика", 10);
        students.add(new BudgetStudent("Алексей", "Сидоров", "BUD999", badSession));

        SessionResult lowGrades = new SessionResult(true);
        lowGrades.addGrade("Математика", 4);
        lowGrades.addGrade("Физика", 5);
        lowGrades.addGrade("Программирование", 6);
        students.add(new BudgetStudent("Дмитрий", "Кузнецов", "BUD888", lowGrades));

        return students;
    }
}