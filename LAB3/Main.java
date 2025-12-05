import java.util.List;

public class Main
{

    private static final double BASE_SCHOLARSHIP = 150.0;

    public static void main(String[] args)
    {
        StudentRepository repository = new StudentRepository();
        List<Student> allStudents = repository.getAllStudents();

        System.out.println("[---] Общий расчет стипендий [---]");
        for (Student s : allStudents)
        {
            double scholarship = s.calculateScholarship(BASE_SCHOLARSHIP);

            String studentType = "Бюджетник";
            if (s instanceof ContractStudent) studentType = "Платник";

            System.out.println(
                    "Студент: " + s.getFullName() +
                            " | Тип: " + studentType +
                            " | Средний балл: " + s.getAverageGrade() +
                            " | Сдал в срок: " + s.getSessionResult().isPassedOnTime() +
                            " | Стипендия: " + scholarship + " руб."
            );
        }

        // 1.5: Вывести студентов на платной основе
        System.out.println("\n[---] Студенты на платной основе [---]");
        int contractCount = 0;
        for (Student s : allStudents)
        {
            if (s instanceof ContractStudent) //instanceof true — если объект не null и принадлежит к указанному классу
            {
                System.out.println(s.toString());
                contractCount++;
            }
        }
        if (contractCount == 0)
            System.out.println("Студенты на платной основе не найдены.");


        // 1.6: Вывести средний балл по всем студентам
        System.out.println("\n[---] Средний балл по всем студентам [---]");
        double totalGpa = 0;

        for (Student s : allStudents)
            totalGpa += s.getAverageGrade();

        if (!allStudents.isEmpty())
        {
            double overallAverage = totalGpa / allStudents.size();
            System.out.println("Общий средний балл по " + allStudents.size() + " студентам: " + overallAverage);
        }
        else System.out.println("Список студентов пуст (все отчислены Авакяном)");
    }
}

