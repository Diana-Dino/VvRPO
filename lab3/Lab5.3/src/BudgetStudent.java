public class BudgetStudent extends Student {

    public BudgetStudent(String firstName, String lastName, String studentId, SessionResult sessionResult) {
        super(firstName, lastName, studentId, sessionResult);
    }

    @Override
    public double calculateScholarship(double baseScholarship) {
        // Только студенты, обучающиеся на бюджетной форме и сдавшие сессию в срок, получают стипендию
        if (!sessionResult.isPassedOnTime()) {
            return 0.0;
        }

        double gpa = getAverageGrade();

        // 1.3: Логика расчета стипендии по среднему баллу
        if (gpa < 5.0) {
            return 0.0; // Меньше 5 - не получает
        } else if (gpa >= 5.0 && gpa < 6.0) {
            return 0.0; // Меньше 6 - не получает (по условию)
        } else if (gpa >= 6.0 && gpa < 8.0) {
            return baseScholarship; // Минимальная (6-7)
        } else if (gpa >= 8.0 && gpa < 9.0) {
            return baseScholarship * 1.25; // +25% (8-8.99)
        } else if (gpa >= 9.0 && gpa <= 10.0) {
            return baseScholarship * 1.50; // +50% (9-10)
        } else {
            return 0.0; // На всякий случай
        }
    }
}