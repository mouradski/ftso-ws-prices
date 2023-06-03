package dev.mouradski.prices.client.gemini;

import dev.mouradski.prices.client.AbstractClientEndpoint;
import dev.mouradski.prices.service.PriceService;
import dev.mouradski.prices.utils.Constants;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class GeminiClientEndpointConfig {

    @Bean
    public List<GeminiClientEndpoint> geminiEndpoints(PriceService priceSender, ConfigurableListableBeanFactory beanFactory) {

        List<GeminiClientEndpoint> beans = new ArrayList<>();

        Constants.SYMBOLS.forEach(symbol -> {
            GeminiClientEndpoint geminiClientEndpoint = new GeminiClientEndpoint(priceSender, symbol, "usdt");
            String beanName = geminiClientEndpoint.getExchange() + "_" + symbol + "usdt";
            beanFactory.initializeBean(geminiClientEndpoint, beanName);
            beanFactory.autowireBean(geminiClientEndpoint);
            beanFactory.registerSingleton(beanName, geminiClientEndpoint);

            beans.add(geminiClientEndpoint);
        });

        return beans;
    }
}
