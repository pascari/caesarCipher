package cryptanalizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class BrutForce {

    public static void brutForce(String codedMessagePath, String patternMessagePath) throws IOException {
        int shift = 0;
        int maximalCoincidence = 0;
        Path pathPatternMessage = Paths.get(patternMessagePath);
        String patternMessageSting = Files.readString(pathPatternMessage);
        String[] wordsFromPatternMessage = patternMessageSting.split(" ");

        for (int k = 1; k <= Alphabet.ALPHABET.length; k++) {
            Path filePath = Path.of(codedMessagePath);
            StringBuilder encryptedMessageSb = new StringBuilder();
            StringBuilder decryptedMessageSb = new StringBuilder();
            try (Scanner scanner = new Scanner(filePath)) {
                while (scanner.hasNextLine()) {
                    encryptedMessageSb.append(scanner.nextLine());
                }
            }
            for (int i = 0; i < encryptedMessageSb.length(); i++) {
                Character ch = encryptedMessageSb.charAt(i);
                if (Arrays.asList(Alphabet.ALPHABET).contains(ch)) {
                    for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
                        if (ch.equals(Alphabet.ALPHABET[j])) {
                            int offset = (k + j) % 41;
                            decryptedMessageSb.append(Alphabet.ALPHABET[offset]);
                        }
                    }
                } else {
                    decryptedMessageSb.append(ch);
                }
            }
            String decodedMessage = decryptedMessageSb.toString();
            String[] wordsFromDecodedMessage = decodedMessage.split(" ");
            int currentCoincidence = 0;
            for (String s1 : wordsFromPatternMessage) {
                for (String s2 : wordsFromDecodedMessage) {
                    if (s1.equals(s2)) {
                        currentCoincidence++;
                    }
                }
            }
            if (currentCoincidence > maximalCoincidence) {
                maximalCoincidence = currentCoincidence;
                shift = k;
            }
        }
        int key = 41 - shift;
        System.out.println("Оптимальным ключом для расшифровки является " + key);
        Decryption.decryption(codedMessagePath, key);
    }
}
