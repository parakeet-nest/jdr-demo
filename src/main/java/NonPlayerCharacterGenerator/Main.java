package NonPlayerCharacterGenerator;

import org.parakeetnest.parakeet4j.llm.Message;
import org.parakeetnest.parakeet4j.llm.Options;
import org.parakeetnest.parakeet4j.llm.Query;

import java.util.List;

import static org.parakeetnest.parakeet4j.completion.Completion.ChatStream;

public class Main {

    static void generateCompletion(Options options, String input) {
        List<Message> messages = List.of(
                new Message("system", Data.IdentityAndPurpose),
                new Message("system", Data.Steps),
                new Message("system", Data.OutputInstructions),
                new Message("user", input)
        );
        Query queryChat = new Query()
                .setModel("qwen2:0.5b")
                .setMessages(messages)
                .setOptions(options);

        System.out.println(input);
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

        //generateCompletion(options, "Create an orc");
        generateCompletion(options, "Create an elf");


    }
}