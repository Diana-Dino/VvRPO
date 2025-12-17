// [РЕФАКТОРИНГ] Класс платного студента с улучшениями
public class ContractStudent extends Student {

    public ContractStudent(String firstName, String lastName, String studentId, SessionResult sessionResult) {
        super(firstName, lastName, studentId, sessionResult);
    }

    @Override
    public double calculateScholarship(double baseScholarship) {
        // Студенты на платной основе не получают стипендию
        return 0.0;
    }

    // [РЕФАКТОРИНГ] Переопределение toString для явного указания типа студента
    @Override
    public String toString() {
        return "[Платник] " + super.toString();
    }
}
