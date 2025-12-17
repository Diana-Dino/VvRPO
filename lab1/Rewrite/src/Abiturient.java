import java.util.ArrayList;
import java.util.List;

public class Abiturient {
    // Поля остались public для простоты
    public int id;
    public String lastName;    // фамилия
    public String name;        // имя
    public String surname;     // отчество
    public String address;     // адрес
    public String phoneNumber; // телефон
    public List<Integer> grades; // список оценок

    // Конструктор
    // this ссылается на текущий объект
    public Abiturient(int id, String lastName, String name, String surname,
                      String address, String phone, List<Integer> grades) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phone;
        // Создаем копию списка оценок для безопасности
        this.grades = new ArrayList<>(grades);
    }

    // --- [РЕФАКТОРИНГ] Добавлен геттер для ID, нужен для репозитория ---
    public int getId() {
        return id;
    }

    // Метод для вычисления среднего балла
    public double getAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    // Проверка на наличие неудовлетворительных оценок
    public boolean hasUnsatisfactoryGrades(int minGrade) {
        if (grades == null) {
            return false;
        }

        for (int grade : grades) {
            if (grade < minGrade) {
                return true;
            }
        }
        return false;
    }

    // Красивое строковое представление объекта
    @Override
    public String toString() {
        String avg = String.format("%.2f", getAverageGrade());

        // Формируем строку с оценками
        String gradesStr = "[";
        for (int i = 0; i < grades.size(); i++) {
            gradesStr += grades.get(i);
            if (i < grades.size() - 1) {
                gradesStr += ", ";
            }
        }
        gradesStr += "]";

        return "ID: " + id +
                ", ФИО: " + lastName + " " + name + " " + surname +
                ", Ср.балл: " + avg +
                ", Оценки: " + gradesStr +
                ", Тел: " + phoneNumber;
    }
}