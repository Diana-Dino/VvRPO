public class BudgetStudent extends Student
{

    public BudgetStudent(String firstName, String lastName, String studentId, SessionResult sessionResult)
    {
        super(firstName, lastName, studentId, sessionResult);
    }

    @Override
    public double calculateScholarship(double baseScholarship)
    {
        if (!sessionResult.isPassedOnTime()) return 0.0;

        double gpa = getAverageGrade();

        if (gpa >= 9.0) return baseScholarship * 1.50; // +50%
        else if (gpa >= 8.0) return baseScholarship * 1.25; // +25%
        else if (gpa >= 6.0) return baseScholarship; // Минимальная
        else return 0.0; // Меньше 6
    }
}

