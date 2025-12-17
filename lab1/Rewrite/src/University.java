import java.util.*;

public class University
{
    /* --- [РЕФАКТОРИНГ] Замена List<Abiturient> на AbiturientRepository ---
       Теперь University не хранит список напрямую, а использует репозиторий
    */
    private final AbiturientRepository repository = new AbiturientRepository();

    // Списки для генерации случайных данных (остались без изменений)
    private final List<String> lastNames = Arrays.asList("Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов");
    private final List<String> firstNames = Arrays.asList("Иван", "Петр", "Алексей", "Дмитрий", "Сергей");
    private final List<String> patronymics = Arrays.asList("Иванович", "Петрович", "Алексеевич", "Дмитриевич");
    private final List<String> addresses = Arrays.asList("ул. Ленина", "пр. Мира", "ул. Советская", "ул. Гагарина");

    private final Random random = new Random();

    // Конструктор создает заданное количество абитуриентов
    public University(int count)
    {
        for (int i = 1; i <= count; i++)
        {
            // --- [РЕФАКТОРИНГ] используем метод add репозитория ---
            repository.add(createRandomAbiturient(i));
        }
    }

    // --- [НОВЫЙ МЕТОД] Геттер для получения репозитория ---
    public AbiturientRepository getRepository()
    {
        return repository;
    }

    // Метод для создания случайного абитуриента
    private Abiturient createRandomAbiturient(int id)
    {
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String patronymic = patronymics.get(random.nextInt(patronymics.size()));
        String address = addresses.get(random.nextInt(addresses.size()));

        // Генерируем случайный телефон
        String phone = "+375 29 " +
                (random.nextInt(900) + 100) + " " +
                (random.nextInt(90) + 10) +
                (random.nextInt(90) + 10);

        // Генерируем 5 случайных оценок (от 3 до 10)
        List<Integer> grades = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            grades.add(random.nextInt(8) + 3); // от 3 до 10
        }

        return new Abiturient(id, lastName, firstName, patronymic,
                address, phone, grades);
    }

    // ============================================
    // МЕТОДЫ ИЗ ЛАБОРАТОРНОЙ 1 (адаптированы для работы с репозиторием)
    // ============================================

    // а) Список абитуриентов с неудовлетворительными оценками
    public List<Abiturient> getWithUnsatisfactoryGrades(int minGrade)
    {
        List<Abiturient> result = new ArrayList<>();

        // --- [РЕФАКТОРИНГ] берем данные из репозитория ---
        List<Abiturient> all = repository.getAll();

        for (Abiturient a : all) {
            if (a.hasUnsatisfactoryGrades(minGrade))
            {
                result.add(a);
            }
        }
        return result;
    }

    // б) Список абитуриентов со средним баллом выше заданного
    public List<Abiturient> getWithAverageAbove(double target)
    {
        List<Abiturient> result = new ArrayList<>();

        // --- [РЕФАКТОРИНГ] берем данные из репозитория ---
        List<Abiturient> all = repository.getAll();

        for (Abiturient a : all)
        {
            if (a.getAverageGrade() > target)
            {
                result.add(a);
            }
        }
        return result;
    }

    // в) N абитуриентов с самым высоким средним баллом
    public List<Abiturient> getTopN(int n)
    {
        // --- [РЕФАКТОРИНГ] берем данные из репозитория ---
        List<Abiturient> all = repository.getAll();

        // Сортируем по убыванию среднего балла
        for (int i = 0; i < all.size() - 1; i++)
        {
            for (int j = i + 1; j < all.size(); j++)
            {
                if (all.get(i).getAverageGrade() < all.get(j).getAverageGrade())
                {
                    // Меняем местами
                    Abiturient temp = all.get(i);
                    all.set(i, all.get(j));
                    all.set(j, temp);
                }
            }
        }

        // Возвращаем первые n элементов
        int count = Math.min(n, all.size());
        List<Abiturient> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
        {
            result.add(all.get(i));
        }
        return result;
    }

    // г) Абитуриенты с полу-проходным баллом
    public List<Abiturient> getWithSemiPassingGrade(double min, double max)
    {
        List<Abiturient> result = new ArrayList<>();

        // --- [РЕФАКТОРИНГ] берем данные из репозитория ---
        List<Abiturient> all = repository.getAll();

        // Находим абитуриентов в заданном диапазоне
        for (Abiturient a : all)
        {
            double avg = a.getAverageGrade();
            if (avg >= min && avg <= max)
            {
                result.add(a);
            }
        }

        // Сортируем по убыванию балла
        for (int i = 0; i < result.size() - 1; i++)
        {
            for (int j = i + 1; j < result.size(); j++)
            {
                if (result.get(i).getAverageGrade() < result.get(j).getAverageGrade())
                {
                    Abiturient temp = result.get(i);
                    result.set(i, result.get(j));
                    result.set(j, temp);
                }
            }
        }

        return result;
    }

    // Вспомогательный метод для печати (остался без изменений)
    public void printAbiturients(String title, List<Abiturient> list)
    {
        System.out.println("=== " + title + " ===");
        if (list.isEmpty())
        {
            System.out.println("Список пуст");
        } else
        {
            for (Abiturient a : list)
            {
                System.out.println(a);
            }
        }
        System.out.println();
    }
}