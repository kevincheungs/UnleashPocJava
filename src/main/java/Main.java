import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import io.getunleash.Variant;
import io.getunleash.util.UnleashConfig;

import java.util.UUID;

public class Main {

    // Maps to feature name on UI
    private static String toggleName = "buttonColor3";
    private static String apiKey = "*:production.cd88efb5abf76fed1648e6e98080c087f39adfb5568ba2edea859dc9";

    public static void main(String[] args) throws InterruptedException {

        // Define the SDK configurations including custom strategy, api key, etc.
        UnleashConfig config = UnleashConfig.builder()
                .appName("default")
                .instanceId("compy1")
                .environment("production")
                .unleashAPI("http://localhost:4242/api/")
                .apiKey(apiKey)
                .fallbackStrategy(new CustomStrategy())
                .synchronousFetchOnInitialisation(true)
                .build();

        Unleash unleash = new DefaultUnleash(config);

        if (unleash.isEnabled(toggleName)) {
            //do some magic
            System.out.println(toggleName + " is Enabled");

            for (int i = 0; i < 100; i++) {
                String sessionId = UUID.randomUUID().toString();

                /*
                 Setting the context in this case allows you to pass in things like session and user id and any
                 custom defined properties like partnerId
                 */
                UnleashContext context = UnleashContext.builder()
                        .appName("default")
                        .environment("production")
                        .sessionId(sessionId)
                        .userId(sessionId)
                        .addProperty("partnerId", "abc")
                        .build();

                System.out.println(unleash.isEnabled(toggleName, context));
                System.out.println("Polling...");

                Thread.sleep(1000);
                Variant variant = unleash.getVariant(toggleName, context);
                System.out.println(variant.getName());
            }
        } else {
            //do old boring stuff
            System.out.println("NOT Enabled");
        }
    }
}
