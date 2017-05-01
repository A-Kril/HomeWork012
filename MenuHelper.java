import java.util.Scanner;

public class MenuHelper {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean isExit = false;
    private static ArrayHolder arrayHolder;
    private static int choice;

    private static int readInt(String request) {
        System.out.print(request);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print(request);
            }
        }
    }

    private static int enterSizeArray() {
        int size;
        while ((size  = readInt("Введите длину массива: ")) < 1);
        return size;
    }

    private static void initArrayHolder() {
        int[] array = new int[enterSizeArray()];
        for (int i = 0; i < array.length; i++) {
            array[i] = readInt("введите " + i + "-й индекс = ");
        }
        arrayHolder = new ArrayHolder(array);
    }

    private static void printMenu() {
        System.out.println("");
        System.out.println("Сделайте свой выбор:");
        System.out.println("1: снова ввести массив;");
        System.out.println("2: найти число по значению;");
        System.out.println("3: сортировать массив;");
        System.out.println("4: выход;");
        System.out.println("");
    }

    private static void runFindIndexMenu() {
        int index = -1;
        int number = readInt("Введите число: ");
        StringBuilder builder = new StringBuilder();
        while ((index = arrayHolder.findIndex(index + 1, number)) != -1) {
            builder.append(index);
            builder.append(", ");
        }
        if (builder.length() > 0) {
            builder.delete(builder.length() - 2, builder.length());
            System.out.println("\nИндексом ваше числа является: " + builder.toString());
        } else {
            System.out.println("\nВаше число не из этого массива!");
        }
    }

    private static void runSortMenu() {
        arrayHolder.sortArray();
        System.out.println("\nМассив отсортирован!");
    }

    private static void runExitMenu() {
        System.out.println("До свидания!");
        scanner.close();
        isExit = true;
    }

    private static void doChoice() {
        do {
            choice = readInt("Ваш выбор ... ");
        } while (choice < 1 || choice > 4);
    }

    private static void runMenu() {
        switch (choice)  {
            case 1: initArrayHolder(); break;
            case 2: runFindIndexMenu(); break;
            case 3: runSortMenu(); break;
            default: runExitMenu();
        }
    }

    public static void start() {
        initArrayHolder();
        while (true) {
            printMenu();
            doChoice();
            runMenu();
            if (isExit) return;
        }
    }
}