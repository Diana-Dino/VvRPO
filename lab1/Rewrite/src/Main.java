import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        // 1. Создаем университет и генерируем 5 случайных абитуриентов
        University university = new University(5);

        // --- [РЕФАКТОРИНГ] получаем список через репозиторий ---
        System.out.println("=== ВСЕ АБИТУРИЕНТЫ (после генерации) ===");
        List<Abiturient> all = university.getRepository().getAll();
        for (Abiturient a : all)
        {
            System.out.println(a);
        }
        System.out.println();

        // ==========================================
        // ДЕМОНСТРАЦИЯ РАБОТЫ РЕПОЗИТОРИЯ (НОВОЕ ЗАДАНИЕ)
        // ==========================================

        System.out.println("*** ДЕМОНСТРАЦИЯ РЕПОЗИТОРИЯ ***\n");
        AbiturientRepository repo = university.getRepository();

        // ДОБАВЛЕНИЕ (Create) - новый метод репозитория
        System.out.println("1. ДОБАВЛЕНИЕ нового абитуриента:");
        Abiturient newStudent = new Abiturient(
                100,
                "Новиков",
                "Олег",
                "Олегович",
                "ул. Тестовая",
                "+375 29 1234567",
                Arrays.asList(10, 9, 10, 9, 10)
        );
        repo.add(newStudent);
        System.out.println("   Добавлен: " + newStudent);
        System.out.println();

        // ИЗМЕНЕНИЕ (Update) - новый метод репозитория
        System.out.println("2. ИЗМЕНЕНИЕ абитуриента с ID 1:");
        Abiturient updateInfo = new Abiturient(
                1, // тот же ID
                "ИЗМЕНЕНОВ", // новая фамилия
                "ИЗМЕН",     // новое имя
                "ИЗМЕНОВИЧ", // новое отчество
                "ул. Новая", // новый адрес
                "+375 44 7654321", // новый телефон
                Arrays.asList(8, 8, 9, 9, 8) // новые оценки
        );
        boolean isUpdated = repo.update(1, updateInfo);
        if (isUpdated)
        {
            System.out.println("   Абитуриент с ID 1 успешно изменен");
        } else
        {
            System.out.println("   Абитуриент с ID 1 не найден");
        }
        System.out.println();

        // УДАЛЕНИЕ (Delete) - новый метод репозитория
        System.out.println("3. УДАЛЕНИЕ абитуриента с ID 2:");
        boolean isDeleted = repo.removeById(2);
        if (isDeleted)
        {
            System.out.println("   Абитуриент с ID 2 удален");
        } else
        {
            System.out.println("   Абитуриент с ID 2 не найден");
        }
        System.out.println();

        // Итоговый список
        System.out.println("=== СПИСОК ПОСЛЕ РЕДАКТИРОВАНИЯ ===");
        List<Abiturient> afterEdit = repo.getAll();
        for (Abiturient a : afterEdit)
        {
            System.out.println(a);
        }
        System.out.println();

        // Поиск по ID - новый метод репозитория
        System.out.println("5. ПОИСК абитуриента с ID 3:");
        Abiturient found = repo.findById(3);
        if (found != null)
        {
            System.out.println("   Найден: " + found);
        } else
        {
            System.out.println("   Абитуриент с ID 3 не найден");
        }
        System.out.println();



        System.out.println("*** ИСХОДНЫЕ ЗАДАНИЯ ИЗ 1 ЛАБОРАТОРНОЙ ***\n");

        // Абитуриенты с неудовлетворительными оценками
        int badGrade = 4;
        List<Abiturient> badStudents = university.getWithUnsatisfactoryGrades(badGrade);
        System.out.println("а) Абитуриенты с оценками ниже " + badGrade + ":");
        if (badStudents.isEmpty())
        {
            System.out.println("   Нет таких абитуриентов");
        } else
        {
            for (Abiturient a : badStudents)
            {
                System.out.println("   - " + a.lastName + " " + a.name +
                        " (средний: " + String.format("%.1f", a.getAverageGrade()) + ")");
            }
        }
        System.out.println();

        // Абитуриенты со средним баллом выше заданного
        double target = 7.5;
        List<Abiturient> goodStudents = university.getWithAverageAbove(target);
        System.out.println("b) Абитуриенты со средним баллом выше " + target + ":");
        if (goodStudents.isEmpty()) {
            System.out.println("   Нет таких абитуриентов");
        } else {
            for (Abiturient a : goodStudents) {
                System.out.println("   - " + a.lastName + " " + a.name +
                        " (средний: " + String.format("%.1f", a.getAverageGrade()) + ")");
            }
        }
    }
}