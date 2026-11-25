package app;

import data.Command;
import model.Animal;
import model.Cat;
import model.Dog;
import model.Duck;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Animal> animals = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Добро пожаловать! Доступные команды: add / list / exit");
        while (true) {
            System.out.println("Введите команду (add / list / exit):");
            Command command = readCommand();
            switch (command) {
                case ADD:
                    addAnimal();
                    break;
                case LIST:
                    printAnimals();
                    break;
                case EXIT:
                    System.out.println("До свидания!");
                    return;
            }
        }
    }

    private static Command readCommand() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Command.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Такой команды нет. Введите add, list или exit:");
            }
        }
    }

    private static void addAnimal() {
        System.out.println("Какое животное добавить? (cat/dog/duck)");
        String type = scanner.nextLine().trim().toLowerCase();

        Animal animal;
        switch (type) {
            case "cat":
                animal = new Cat();
                break;
            case "dog":
                animal = new Dog();
                break;
            case "duck":
                animal = new Duck();
                break;
            default:
                System.out.println("Не знаю такого животного, возвращаюсь в меню");
                return;
        }

        System.out.println("Введите имя:");
        animal.setName(scanner.nextLine());

        System.out.println("Введите возраст:");
        animal.setAge(readInt());

        System.out.println("Введите вес:");
        animal.setWeight(readInt());

        System.out.println("Введите цвет:");
        animal.setColor(scanner.nextLine());

        animals.add(animal);
        animal.say();
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value < 0) {
                    System.out.println("Значение не может быть отрицательным, попробуйте ещё раз:");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Нужно целое число, попробуйте ещё раз:");
            }
        }
    }

    private static void printAnimals() {
        if (animals.isEmpty()) {
            System.out.println("Список животных пуст");
            return;
        }
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }
    }
}