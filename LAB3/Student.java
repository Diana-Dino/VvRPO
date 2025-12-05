public abstract class Student {

    protected String firstName;
    protected String lastName;
    protected String studentId;
    protected SessionResult sessionResult;

    public Student(String firstName, String lastName, String studentId, SessionResult sessionResult)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.sessionResult = sessionResult;
    }

    public abstract double calculateScholarship(double baseScholarship);

    public double getAverageGrade()
    {
        return this.sessionResult.getAverageGrade();
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public SessionResult getSessionResult()
    {
        return sessionResult;
    }

    public String toString()
    {
        return "Студент: " + getFullName() + " (id: " + studentId + "), Средний балл: " + getAverageGrade();
    }
}
