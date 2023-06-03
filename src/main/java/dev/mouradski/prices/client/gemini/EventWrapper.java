package dev.mouradski.prices.client.gemini;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EventWrapper {
    @JsonProperty("eventId")
    private long eventId;

    @JsonProperty("events")
    private List<Event> events;

    @JsonProperty("socket_sequence")
    private int socketSequence;

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("timestampms")
    private long timestampms;

    @JsonProperty("type")
    private String type;

}





