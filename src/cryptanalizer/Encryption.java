package cryptanalizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Encryption {
    public static void encryption(String messagePath, int key) throws IOException {
        File file = new File(messagePath);
        StringBuilder messageSb = new StringBuilder();
        StringBuilder encryptedMessageSb = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                messageSb.append(scanner.nextLine());
            }
        }
        for (int i = 0; i < messageSb.length(); i++) {
            Character ch = messageSb.charAt(i);
            if (Arrays.asList(Alphabet.ALPHABET).contains(ch)) {
                for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
                    if (ch.equals(Alphabet.ALPHABET[j])) {
                        int offsetNew = (key + j) % 41;
                        encryptedMessageSb.append(Alphabet.ALPHABET[offsetNew]);
                    }
                }
            } else {
                encryptedMessageSb.append(ch);
            }
        }
        System.out.println("Текст из файла message был зашифрован и сохранён в созданный файл codedMessage");
        String codedMessage = encryptedMessageSb.toString();
        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/serg/Desktop/codedMessage")) {
            fileOutputStream.write(codedMessage.getBytes());
        }
    }
}
