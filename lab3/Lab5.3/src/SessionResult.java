import java.util.HashMap;
import java.util.Map;

public class SessionResult {
    private final Map<String, Integer> grades;
    private final boolean passedOnTime;

    public SessionResult(boolean passedOnTime) {
        this.passedOnTime = passedOnTime;
        this.grades = new HashMap<>();
    }

    // Добавление оценки по предмету
    public void addGrade(String subject, int grade) {
        // Ограничиваем оценку диапазоном 0-10
        if (grade < 0) {
            grade = 0;
        } else if (grade > 10) {
            grade = 10;
        }
        this.grades.put(subject, grade);
    }

    // Проверка, сдана ли сессия в срок
    public boolean isPassedOnTime() {
        return passedOnTime;
    }

    // Расчет среднего балла
    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }

    // Дополнительный метод для получения всех оценок
    public Map<String, Integer> getGrades() {
        return new HashMap<>(grades); // Возвращаем копию для защиты данных
    }
}