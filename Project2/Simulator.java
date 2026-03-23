import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;

public class Simulator {

    public static void main(String[] args) throws FileNotFoundException {

        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        Scanner scanner = new Scanner(new File("commands.csv"));

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        };

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine().trim();

            if (line.length() == 0) {
                continue;
            }

            String[] words = line.split(",");

            switch (words[0].toLowerCase()) {

                case "create":
                    linkedList = new DoubleLinkedList<>();
                    System.out.println("create");
                    break;

                case "insert":
                    if (words.length >= 3) {
                        int position = Integer.parseInt(words[1].trim());
                        int item = Integer.parseInt(words[2].trim());
                        linkedList.insert(position, item);
                    }
                    break;

                case "remove":
                    if (words.length >= 2) {
                        int position = Integer.parseInt(words[1].trim());
                        Integer removed = linkedList.remove(position);
                        System.out.println(removed);
                    }
                    break;

                case "get":
                    if (words.length >= 2) {
                        int position = Integer.parseInt(words[1].trim());
                        System.out.println(linkedList.getEntry(position));
                    }
                    break;

                case "print":
                    if (words.length >= 2) {
                        int option = Integer.parseInt(words[1].trim());

                        if (option == 0) {
                            System.out.println(linkedList);
                        } else if (option == 1) {
                            linkedList.sort(comp);
                            System.out.println(linkedList);
                        }
                    }
                    break;

                default:
                    System.out.println("Unknown Command");
            }
        }

        scanner.close();
    }
}