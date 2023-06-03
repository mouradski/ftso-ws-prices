package dev.mouradski.prices.client.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    @JsonProperty("delta")
    private Double delta;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("remaining")
    private Double remaining;

    @JsonProperty("side")
    private String side;

    @JsonProperty("type")
    private String type;
}
