public class ContractStudent extends Student
{

    public ContractStudent(String firstName, String lastName, String studentId, SessionResult sessionResult)
    {
        super(firstName, lastName, studentId, sessionResult);
    }

    @Override
    public double calculateScholarship(double baseScholarship)
    {
        return 0.0;
    }
}