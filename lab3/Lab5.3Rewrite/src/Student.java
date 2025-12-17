
// [РЕФАКТОРИНГ] Абстрактный класс Student с улучшенной инкапсуляцией
public abstract class Student {
    // [РЕФАКТОРИНГ] Поля изменены с protected на private для лучшей инкапсуляции
    private String firstName;
    private String lastName;
    private String studentId;
    private SessionResult sessionResult;

    public Student(String firstName, String lastName, String studentId, SessionResult sessionResult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.sessionResult = sessionResult;
    }

    public abstract double calculateScholarship(double baseScholarship);

    public double getAverageGrade() { return this.sessionResult.getAverageGrade(); }

    public String getFullName() { return firstName + " " + lastName; }

    public SessionResult getSessionResult() { return sessionResult; }

    // [РЕФАКТОРИНГ] Добавлен геттер для studentId, так как поле теперь private
    public String getStudentId() { return studentId; }

    @Override
    public String toString()
    {
        return "Студент: " + getFullName() + " (ID: " + studentId +
                "), Средний балл: " + String.format("%.2f", getAverageGrade());
    }
}


