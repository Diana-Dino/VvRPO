public abstract class Student {
    protected String firstName;
    protected String lastName;
    protected String studentId;
    protected SessionResult sessionResult;

    public Student(String firstName, String lastName, String studentId, SessionResult sessionResult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.sessionResult = sessionResult;
    }

    // Абстрактный метод для расчета стипендии
    public abstract double calculateScholarship(double baseScholarship);

    // Получение среднего балла
    public double getAverageGrade() {
        return this.sessionResult.getAverageGrade();
    }

    // Полное имя студента
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // Геттер для результатов сессии
    public SessionResult getSessionResult() {
        return sessionResult;
    }

    @Override
    public String toString() {
        return "Студент: " + getFullName() + " (ID: " + studentId +
                "), Средний балл: " + String.format("%.2f", getAverageGrade());
    }
}