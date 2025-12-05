import java.util.ArrayList;
import java.util.List;

public class StudentRepository
{
    public List<Student> getAllStudents()
    {
        List<Student> allStudents = new ArrayList<>();

        // 1.4: 10 студентов с +25% стипендией (Средний балл 8.0 - 8.99)
        for (int i = 0; i < 10; i++)
        {
            SessionResult result25 = new SessionResult(true);
            result25.addGrade("Физика", 8);
            result25.addGrade("Математика", 9);
            result25.addGrade("Программирование", 8);
            result25.addGrade("История", 9); // Средний балл = 8.5
            allStudents.add(new BudgetStudent(
                    "Иван",
                    "Иванов" + i,
                    "B" + i,
                    result25)
            );
        }

        // Бюджетник, +50% (Средний балл 9.5)
        SessionResult res50 = new SessionResult(true);
        res50.addGrade("Дискретка", 10);
        res50.addGrade("ВвРПО", 9);
        allStudents.add(new BudgetStudent("Петр", "Петров", "B10", res50));

        // Бюджетник, 100% (Средний балл 7.0)
        SessionResult resMin = new SessionResult(true);
        resMin.addGrade("Английский", 7);
        resMin.addGrade("Физкультура", 7);
        allStudents.add(new BudgetStudent("Анна", "Сидорова", "B11", resMin));

        // Бюджетник, 0% (Средний балл 5.0)
        SessionResult resLow = new SessionResult(true);
        resLow.addGrade("Экономика", 5);
        resLow.addGrade("Право", 5);
        allStudents.add(new BudgetStudent("Сергей", "Смирнов", "B12", resLow));

        // Бюджетник, сдал не в срок (0%)
        SessionResult resLate = new SessionResult(false);
        resLate.addGrade("МатЛогика", 10);
        allStudents.add(new BudgetStudent("Олег", "Попов", "B13", resLate));

        // Платник, высокий балл (0%)
        SessionResult resContractHigh = new SessionResult(true);
        resContractHigh.addGrade("Маркетинг", 10);
        allStudents.add(new ContractStudent("Мария", "Кузнецова", "K1", resContractHigh));

        // Платник, низкий балл (0%)
        SessionResult resContractLow = new SessionResult(true);
        resContractLow.addGrade("Менеджмент", 6);
        allStudents.add(new ContractStudent("Дмитрий", "Васильев", "K2", resContractLow));

        return allStudents;
    }
}
