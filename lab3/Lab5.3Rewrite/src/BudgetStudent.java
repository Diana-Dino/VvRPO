

// [РЕФАКТОРИНГ] Класс бюджетного студента с улучшениями
public class BudgetStudent extends Student {
    // [РЕФАКТОРИНГ] Добавлены константы для порогов оценок и коэффициентов стипендии
    private static final double MIN_SCHOLARSHIP_GPA = 6.0;  // Минимальный балл для стипендии
    private static final double INCREASED_GPA_THRESHOLD = 8.0;  // Порог для +25%
    private static final double HIGH_GPA_THRESHOLD = 9.0;  // Порог для +50%

    private static final double RATE_STANDARD = 1.0;    // Коэффициент 100%
    private static final double RATE_INCREASED = 1.25;  // Коэффициент 125%
    private static final double RATE_HIGH = 1.50;       // Коэффициент 150%

    public BudgetStudent(String firstName, String lastName, String studentId, SessionResult sessionResult) {
        super(firstName, lastName, studentId, sessionResult);
    }

    @Override
    public double calculateScholarship(double baseScholarship)
    {
        if (!getSessionResult().isPassedOnTime())
        {
            return 0.0;  // Не сдал в срок - нет стипендии
        }

        double gpa = getAverageGrade();

        // [РЕФАКТОРИНГ] Использование констант вместо "магических чисел" в логике расчета
        if (gpa < MIN_SCHOLARSHIP_GPA) {
            return 0.0;  // Средний балл меньше 6.0
        } else if (gpa >= MIN_SCHOLARSHIP_GPA && gpa < INCREASED_GPA_THRESHOLD) {
            return baseScholarship * RATE_STANDARD;  // 6.0 - 7.99: обычная стипендия
        } else if (gpa >= INCREASED_GPA_THRESHOLD && gpa < HIGH_GPA_THRESHOLD) {
            return baseScholarship * RATE_INCREASED;  // 8.0 - 8.99: +25%
        } else {
            return baseScholarship * RATE_HIGH;  // 9.0 - 10.0: +50%
        }
    }

    // [РЕФАКТОРИНГ] Переопределение toString для явного указания типа студента
    @Override
    public String toString() {
        return "[Бюджет] " + super.toString();
    }
}