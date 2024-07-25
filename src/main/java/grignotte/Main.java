package grignotte;

import org.parakeetnest.parakeet4j.llm.Message;
import org.parakeetnest.parakeet4j.llm.Options;
import org.parakeetnest.parakeet4j.llm.Query;

import java.util.List;

import static org.parakeetnest.parakeet4j.completion.Completion.ChatStream;

public class Main {

    static void generateCompletion(Options options, String question) {
        List<Message> messages = List.of(
                new Message("system", Data.instruction),
                new Message("user", Data.content),
                new Message("user", question)
        );
        Query queryChat = new Query()
                .setModel("qwen2:0.5b")
                .setMessages(messages)
                .setOptions(options);

        System.out.println(question);
        ChatStream("http://0.0.0.0:11434", queryChat,
                chunk -> {
                    System.out.print(chunk.getMessage().getContent());
                    return null;
                });
        System.out.println();
        System.out.println("---------------------------------------------");
    }

    public static void main(String[] args) {

        Options options = new Options()
                .setTemperature(0.2)
                .setRepeatLastN(2)
                .setRepeatPenalty(2.0)
                .setTopK(10)
                .setTopP(0.5);

        generateCompletion(options, "Comment t'appelles tu ?");

        generateCompletion(options, "Quel est l'endroit où tu vis ?");

        generateCompletion(options, "Explique moi ton métier en une phrase ?");

        generateCompletion(options, "Quels sont tes goûts vestimentaires ?");

        generateCompletion(options, "Raconte moi ton histoire ?");

        generateCompletion(options, "qu'est-ce que tu aimes manger ?");

        generateCompletion(options, "quel est ton âge ?");

    }
}