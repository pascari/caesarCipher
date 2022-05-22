package cryptanalizer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Decryption {
    public static void decryption(String codedMessagePath, int key) throws IOException {
        File file = new File(codedMessagePath);
        StringBuilder encryptedMessageSb = new StringBuilder();
        StringBuilder decryptedMessageSb = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                encryptedMessageSb.append(scanner.nextLine());
            }
        }
        int keyAbsolut = key % 41;
        int keyShift = Alphabet.ALPHABET.length - keyAbsolut;
        for (int i = 0; i < encryptedMessageSb.length(); i++) {
            Character ch = encryptedMessageSb.charAt(i);
            if (Arrays.asList(Alphabet.ALPHABET).contains(ch)) {
                for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
                    if (ch.equals(Alphabet.ALPHABET[j])) {
                        int offset = (keyShift + j) % 41;
                        decryptedMessageSb.append(Alphabet.ALPHABET[offset]);
                    }
                }
            } else {
                decryptedMessageSb.append(ch);
            }
        }
        System.out.println("Текст из файла codedMessage был расшифрован и сохранён в созданный файл decodedMessage");
        String decodedMessage = decryptedMessageSb.toString();
        try (FileOutputStream fileOutputStream = new FileOutputStream("/Users/serg/Desktop/decodedMessage")) {
            fileOutputStream.write(decodedMessage.getBytes());
        }
    }
}
