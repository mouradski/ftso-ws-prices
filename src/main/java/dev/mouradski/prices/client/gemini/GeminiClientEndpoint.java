package dev.mouradski.prices.client.gemini;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.mouradski.prices.client.AbstractClientEndpoint;
import dev.mouradski.prices.model.Trade;
import dev.mouradski.prices.service.PriceService;
import jakarta.websocket.ClientEndpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ClientEndpoint
public class GeminiClientEndpoint extends AbstractClientEndpoint {

    protected GeminiClientEndpoint(PriceService priceSender, String symbol, String quote) {
        super(priceSender, new ArrayList<>(), null);
        this.uniqueSymbol = symbol;
        this.uniqueQuote = quote;
        this.connect();
    }

    @Override
    protected String getUri() {
        return "wss://api.gemini.com/v1/marketdata/" +  uniqueSymbol + uniqueQuote + "?heartbeat=true&trades=true&bids=false&offers=true";
    }

    @Override
    protected String getExchange() {
        return "gemini";
    }

    @Override
    protected void subscribe() {
    }

    @Override
    protected boolean pong(String message) {
        return super.pong(message);
    }

    @Override
    protected List<Trade> mapTrade(String message) throws JsonProcessingException {

        if (!message.contains("event") || !message.contains("trade")) {
            return new ArrayList<>();
        }

        var evenWrapper = this.objectMapper.readValue(message, EventWrapper.class);

        var trades = new ArrayList<Trade>();

        evenWrapper.getEvents().stream().filter(event -> "trade".equals(event.getReason())).forEach(event -> {
            trades.add(Trade.builder().symbol(this.uniqueSymbol).quote(this.uniqueQuote).price(event.getPrice()).build());
        });

        return trades;
    }
}
