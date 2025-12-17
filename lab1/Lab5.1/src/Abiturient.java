import java.util.ArrayList;
import java.util.List;

public class Abiturient
{
    public int id;
    public String lastName; // фамилия
    public String name;
    public String surname; // отчество
    public String address;
    public String phoneNumber;
    public List<Integer> grades; // оценки

    // конструктор
    // this ссылается на текущий объект который вызвал метод и т.п
    public Abiturient(int id, String LName, String Name, String SName,
                      String address, String PNum, List<Integer> grades)
    {
        this.id = id;
        this.lastName = LName;
        this.name = Name;
        this.surname = SName;
        this.address = address;
        this.phoneNumber = PNum;
        this.grades = new ArrayList<>(grades);
    }

    // средний балл абитуриента

    public double getAverageGrade()
    {
        if (grades == null || grades.isEmpty()) return 0.0;

        double sum = 0;
        for (Integer grade : grades)
            sum += grade;
        return sum / grades.size();
    }

    // наличие неудовлетворительных оценок
    public boolean hasUnsatisfactoryGrades(int threshold)
    {
        if (grades == null) return false;
        for (Integer grade : grades)
            if (grade < threshold) return true;
        return false;
    }

    @Override
    public String toString()
    {
        String avgGrade = String.format("%.2f", getAverageGrade());

        /*
          StringBuilder это класс, который позволяет создавать строку на ходу,
          добавлять элементы и в конце преобразовывать в строку
         */
        StringBuilder gradesBuilder = new StringBuilder();
        gradesBuilder.append("[");
        for (int i = 0; i < grades.size(); i++)
        {
            gradesBuilder.append(grades.get(i));
            if (i < grades.size() - 1)
                gradesBuilder.append(", ");
        }
        gradesBuilder.append("]");
        String gradesString = gradesBuilder.toString();

        return "ID: " + id +
                ", ФИО: " + lastName + " " + name + " " + surname +
                ", Средний балл: " + avgGrade +
                ", Оценки: " + gradesString +
                ", Телефон: " + phoneNumber;
    }
}