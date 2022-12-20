import io.getunleash.UnleashContext;
import io.getunleash.strategy.Strategy;

import java.util.Map;
import java.util.Random;

/**
 * Custom strategy implementation. Need to override isEnabled method so the code knows
 * whether the flag is enabled.
 */
public class CustomStrategy implements Strategy {
    @Override
    public String getName() {
        return "CustomStrategyPartnerId";
    }

    @Override
    public boolean isEnabled(Map<String, String> map) {
        return false;
    }

    @Override
    public boolean isEnabled(Map<String, String> parameters, UnleashContext unleashContext) {
        Random random = new Random();
        int randNum = (random.nextInt(100 - 1 + 1) + 1);

        /*
        Parameters comes from Unleash server. The values are set from the UI, but the specific logic is here.
        This flag is enabled when the partnerId equals whatever you have set in the UI
        AND
        only a certain percent of traffic (which is also set in the UI)
         */
        return parameters.get("partnerId").equals(unleashContext.getProperties().get("partnerId"))
                && Integer.valueOf(parameters.get("percent")) > randNum;
    }
}
