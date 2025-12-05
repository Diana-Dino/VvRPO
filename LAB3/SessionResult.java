import java.util.HashMap;
import java.util.Map;

public class SessionResult
{
    private final Map<String, Integer> grades;
    private final boolean passedOnTime;

    public SessionResult(boolean passedOnTime)
    {
        this.passedOnTime = passedOnTime;
        this.grades = new HashMap<>();
    }

    public void addGrade(String subject, int grade) // метод соотношения предмет-оценка
    {
        if (grade < 0) grade = 0;
        else if (grade > 10) grade = 10;
        this.grades.put(subject, grade);
    }

    public boolean isPassedOnTime()
    {
        return passedOnTime;
    }

    public double getAverageGrade()
    {
        if (grades.isEmpty()) return 0.0;

        double sum = 0;
        for (int grade : grades.values())  sum += grade;
        return sum / grades.size();
    }
}

