package chukhlantsev.oleg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {


        File input = new File("Input.txt");

        List<String> strings = new ArrayList<>();

        Map<String, Integer>  counter = new HashMap<>();
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String currentString = scanner.nextLine();
            strings.add(currentString);

            int newValue = counter.getOrDefault(currentString, 0) + 1;
            counter.put(currentString, newValue);
        }
        scanner.close();

        System.out.println("Enter number of sorting: 1 - by alphabet, 2 - by lenght, 3 - by word lenght");

        Scanner scanner2 = new Scanner(System.in);
        int way = scanner2.nextInt();


        if(way==1)
            sordByAlphabet(strings);
        else if (way==2)
            sordByLenght(strings);
        else if (way==3) {
            System.out.println("Enter word number");
            int wordNumber = scanner2.nextInt();
            sordByWordNumber(strings,wordNumber);
        }
        saveFile(strings);

        scanner2.close();

    }

    private static void sordByAlphabet(List<String> strings)
    {
        Collections.sort(strings);
    }
    private static void sordByLenght(List<String> strings)
    {
        strings.sort(Comparator.comparingInt(String::length));
    }
    private static void sordByWordNumber(List<String> strings, int wordNumber)
    {

        strings.sort((s1, s2) ->
        {
            String[] s1Words = s1.split(" ");
            String[] s2Words = s2.split(" ");

            try {

                String keyWords1 = s1Words[wordNumber - 1];
                String keyWords2 = s1Words[wordNumber - 1];

                if(keyWords1.length() > keyWords2.length())
                    return 1;
                else if (keyWords1.length() < keyWords2.length())
                    return -1;
                else return 0;

            }
            catch (Exception e)
            {
                throw new IllegalArgumentException("Не у всех строк есть слово с введенным порядковым номером");
            }

        });

    }

    private static void saveFile(List<String> strings)  {

        try (FileWriter writer = new FileWriter("output.txt")) {
            strings.forEach(string -> {
                try {
                    writer.write(string +"\n");
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка при записи в файл");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл");
        }


        }
    }


