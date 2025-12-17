import java.util.List;

public class Main
{

    public static void main(String[] args)
    {
        // 1. Создаем уник и генерируем 20 случайных абитуриентов
        University university = new University(20);

        // Выводим полный список для наглядности
        university.printAbiturients(
                "ПОЛНЫЙ СПИСОК АБИТУРИЕНТОВ",
                university.getAbiturients()
        );

        // --- Выполнение задания ---

        // а) Вывести список абитуриентов с неудовлетворительными оценками
        // Любой экзамен ниже 4
        int unsatisfactoryThreshold = 4;
        List<Abiturient> failingStudents = university.getWithUnsatisfactoryGrades(unsatisfactoryThreshold);
        university.printAbiturients(
                "a) АБИТУРИЕНТЫ С ОЦЕНКАМИ НИЖЕ " + unsatisfactoryThreshold,
                failingStudents
        );

        // b) Вывести список абитуриентов со средним баллом выше заданного
        double targetAverage = 8.0;
        List<Abiturient> highAchievers = university.getWithAverageAbove(targetAverage);
        university.printAbiturients(
                "b) АБИТУРИЕНТЫ СО СРЕДНИМ БАЛЛОМ ВЫШЕ " + targetAverage,
                highAchievers
        );

        // c) Выбрать n абитуриентов с самым высоким баллом и тех, у кого полупроходной балл
        int n = 5; // количество мест для зачисления
        double minPassingAverage = 4.0; // минимальный средний балл для допуска

        // находим и выводим топ-N абитуриентов
        List<Abiturient> topN = university.getTopN(n);
        university.printAbiturients(
                "c) ТОП-" + n + " АБИТУРИЕНТОВ",
                topN
        );

        // с) находим и выводим абитуриентов с полупроходным баллом
        double minGr = 5.5, maxGr = 6;
        List<Abiturient> semiPassing = university.getWithSemiPassingGrade(minGr, maxGr);
        university.printAbiturients(
                "c) АБИТУРИЕНТЫ С ПОЛУПРОХОДНЫМ БАЛЛОМ (5.5 - 6.0)",

                semiPassing
        );
    }
}