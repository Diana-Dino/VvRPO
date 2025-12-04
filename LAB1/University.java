import java.util.*; // пакет с классами list? arraylist и тд

public class University
{
    /*
      Слово final означает
      что штуки изменениям не подлежат
     */
    private final List<Abiturient> abiturients = new ArrayList<>();

    // списки для генерации случайных данных
    private final List<String> lastNames = Arrays.asList("Иванов", "Петров", "Сидоров", "Кузнецов", "Смирнов", "Попов", "Васильев");
    private final List<String> firstNames = Arrays.asList("Иван", "Петр", "Алексей", "Дмитрий", "Сергей", "Андрей", "Михаил");
    private final List<String> patronymics = Arrays.asList("Иванович", "Петрович", "Григорьевич", "Алексеевич", "Дмитриевич", "Сергеевич");
    private final List<String> addresses = Arrays.asList("ул. Ленина", "пр. Мира", "ул. Советская", "ул. Гагарина", "ул. Пушкина");
    private final Random randomizer = new Random();

    public University(int numberOfAbiturients)
    {
        for (int i = 1; i <= numberOfAbiturients; i++)
            this.abiturients.add(generateAbiturient(i));
    }

    // генерация случайного абитуриента
    private Abiturient generateAbiturient(int id)
    {
        String lastName = lastNames.get(randomizer.nextInt(lastNames.size()));
        String firstName = firstNames.get(randomizer.nextInt(firstNames.size()));
        String patrName = patronymics.get(randomizer.nextInt(patronymics.size()));
        String address = addresses.get(randomizer.nextInt(addresses.size()));
           // от 900 + 100 для того что бы начиналось от 100 а не от 0, гарантируя 3 цифры
        String phoneNumber = "+375 29 " + (randomizer.nextInt(900) + 100) + " " + (randomizer.nextInt(100)+10)  + (randomizer.nextInt(100)+10);
        List<Integer> grades = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            grades.add(randomizer.nextInt(2,11));
        return new Abiturient(id, lastName, firstName, patrName, address, phoneNumber, grades);
    }
        // Возврат всего списка абитуриентов для работы с методами извне
    public List<Abiturient> getAbiturients() {
        return this.abiturients;
    }


    // а) Возвращает список абитуриентов, имеющих неудовлетворительные оценки.

    public List<Abiturient> getWithUnsatisfactoryGrades(int threshold)
    {
        List<Abiturient> resultList = new ArrayList<>();
        for (Abiturient abiturient : this.abiturients)
            if (abiturient.hasUnsatisfactoryGrades(threshold))
                resultList.add(abiturient);
        return resultList;
    }

    // б) Возвращает список абитуриентов, средний балл у которых выше заданного.

    public List<Abiturient> getWithAverageAbove(double targetAverage)
    {
        List<Abiturient> resultList = new ArrayList<>();
        for (Abiturient abiturient : this.abiturients)
            if (abiturient.getAverageGrade() > targetAverage)
                resultList.add(abiturient);
        return resultList;
    }

    // с 1) Возвращает заданное число n абитуриентов с самым высоким средним баллом.

    public List<Abiturient> getTopN(int n)
    {
        // создаем копию, чтобы не изменять исходный список
        List<Abiturient> sortedList = new ArrayList<>(this.abiturients);

        // сортировка копии списка по убыванию среднего балла. Возвращает:
        //отрицательное число — если a меньше b (то есть a хуже),
        //ноль — если они равны,
        //положительное число — если a больше b (то есть a лучше).
        sortedList.sort(new Comparator<Abiturient>()
        {

            public int compare(Abiturient a, Abiturient b)
            {
                // сравниваем абитуриентов б, а, чтобы получить убывающий порядок
                return Double.compare(b.getAverageGrade(), a.getAverageGrade());
            }
        });

        // определяем, сколько элементов возвращать (на случай, если n > размера списка)
        int limit = Math.min(n, sortedList.size());

        // возвращаем новый список, содержащий первые 'limit' элементов
        return new ArrayList<>(sortedList.subList(0, limit));
    }

    // с 2) Возвращает список абитуриентов, имеющих полу-проходной балл.

    public List<Abiturient> getWithSemiPassingGrade(double minGr, double maxGr)
    {
        List<Abiturient> result = new ArrayList<>();

        // Поиск абитуриентов в диапазоне баллов
        for (Abiturient abiturient : abiturients) {
            double grade = abiturient.getAverageGrade();
            if (grade >= minGr && grade <= maxGr) {
                result.add(abiturient);
            }
        }

        // Сортировка по убыванию балла
        result.sort(new Comparator<Abiturient>() {

            public int compare(Abiturient a, Abiturient b) {
                return Double.compare(b.getAverageGrade(), a.getAverageGrade());
            }
        });

        return result;

    }

    // вспомогательный метод для печати списка
    public void printAbiturients(String title, List<Abiturient> list)
    {
        System.out.println("-----------[ " + title + " ]-----------");
        if (list.isEmpty())
            System.out.println("Список пуст :(");
         else for (Abiturient abiturient : list) System.out.println(abiturient);

        System.out.println();
    }
}