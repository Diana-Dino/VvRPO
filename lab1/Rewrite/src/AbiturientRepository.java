import java.util.ArrayList;
import java.util.List;

// --- [НОВЫЙ КЛАСС] Класс-репозиторий для управления коллекцией абитуриентов ---
// Репозиторий отвечает за хранение, добавление, удаление и изменение данных
public class AbiturientRepository {

    // --- [НОВАЯ КОЛЛЕКЦИЯ] Внутренний список для хранения абитуриентов ---
    private final List<Abiturient> students = new ArrayList<>();

    // --- [НОВЫЙ МЕТОД] Добавление абитуриента в коллекцию ---
    public void add(Abiturient student) {
        // Проверка на уникальность ID
        for (Abiturient s : students) {
            if (s.id == student.id) {
                System.out.println("Внимание: Абитуриент с ID " + student.id + " уже существует!");
                return;
            }
        }
        students.add(student);
    }

    // --- [НОВЫЙ МЕТОД] Удаление абитуриента по ID ---
    public boolean removeById(int id) {
        // Ищем абитуриента с нужным ID
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                students.remove(i); // удаляем если нашли
                return true;
            }
        }
        return false; // не нашли такого абитуриента
    }

    // --- [НОВЫЙ МЕТОД] Изменение данных абитуриента ---
    public boolean update(int id, Abiturient newData) {
        // Ищем абитуриента по ID
        for (Abiturient student : students) {
            if (student.id == id) {
                // обновляем все поля найденного объекта
                student.lastName = newData.lastName;
                student.name = newData.name;
                student.surname = newData.surname;
                student.address = newData.address;
                student.phoneNumber = newData.phoneNumber;

                // Создаем новую копию списка оценок
                student.grades = new ArrayList<>(newData.grades);
                return true;
            }
        }
        return false;
    }

    // --- [НОВЫЙ МЕТОД] Поиск абитуриента по ID ---
    public Abiturient findById(int id)
    {
        for (Abiturient student : students)
        {
            if (student.id == id)
            {
                return student;
            }
        }
        return null;
    }

    // --- [НОВЫЙ МЕТОД] Получение всех абитуриентов ---
    public List<Abiturient> getAll()
    {
        return new ArrayList<>(students);
    }

    // --- [НОВЫЙ МЕТОД] Количество абитуриентов в репозитории ---
    public int count()
    {
        return students.size();
    }

    // --- [НОВЫЙ МЕТОД] Очистка репозитория ---
    public void clear()
    {
        students.clear();
    }
}