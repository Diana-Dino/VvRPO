
// [РЕФАКТОРИНГ] Класс SessionResult с улучшениями
import java.util.HashMap;
import java.util.Map;

public class SessionResult {
    // [РЕФАКТОРИНГ] Добавлены константы для минимальной и максимальной оценки
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 10;

    private final Map<String, Integer> grades;
    private final boolean passedOnTime;

    public SessionResult(boolean passedOnTime) {
        this.passedOnTime = passedOnTime;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int grade) {
        // [РЕФАКТОРИНГ] Использование констант вместо "магических чисел" для ограничения оценок
        int finalGrade = grade;
        if (grade < MIN_GRADE) {
            finalGrade = MIN_GRADE;
        } else if (grade > MAX_GRADE) {
            finalGrade = MAX_GRADE;
        }
        this.grades.put(subject, finalGrade);
    }

    public boolean isPassedOnTime() {
        return passedOnTime;
    }

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

    // [РЕФАКТОРИНГ] возвращается защитная копия для предотвращения изменения внутренних данных
    public Map<String, Integer> getGrades() {
        return new HashMap<>(grades);
    }
}

