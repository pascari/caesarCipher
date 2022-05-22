package cryptanalizer;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Выбери действие:   [1 - ШИФРОВАНИЕ]   [2 - РАСШИФРОВКА]   [3 - BRUTE FORCE]");
        Scanner scannerAction = new Scanner(System.in);
        String action = scannerAction.nextLine();
        switch (action) {
            case "1" -> {
                System.out.println(Alphabet.PATH);
                Scanner scannerMessagePath = new Scanner(System.in);
                String messagePath = scannerMessagePath.nextLine();
                System.out.println(Alphabet.KEY);
                Scanner scannerKey = new Scanner(System.in);
                int key = scannerKey.nextInt();
                Encryption.encryption(messagePath, key);
            }
            case "2" -> {
                System.out.println(Alphabet.PATH);
                Scanner scannerCodedMessagePath = new Scanner(System.in);
                String codedMessagePath = scannerCodedMessagePath.nextLine();
                System.out.println(Alphabet.KEY);
                Scanner scannerKey = new Scanner(System.in);
                int key = scannerKey.nextInt();
                Decryption.decryption(codedMessagePath, key);
            }
            case "3" -> {
                System.out.println("Введи путь к зашифрованному файлу:");
                Scanner scannerCodedMessagePath = new Scanner(System.in);
                String codedMessagePath = scannerCodedMessagePath.nextLine();
                System.out.println("Введи путь к файлу с образцом текста:");
                Scanner scannerPatternMessagePath = new Scanner(System.in);
                String patternMessagePath = scannerPatternMessagePath.nextLine();
                BrutForce.brutForce(codedMessagePath, patternMessagePath);
            }
            default -> System.out.println("Данного действия не существует, проверь вводимые данные");
        }
    }
}
