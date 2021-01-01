package net.spehl.mbm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static net.spehl.mbm.Helpers.randomList;
import static net.spehl.mbm.Helpers.randomMap;
import static net.spehl.mbm.Helpers.randomString;


@State(Scope.Benchmark)
public class JsonBench {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JsonMessage {
        UUID id;
        Instant createTime;
        Map<String, String> headers;
        String message;
        List<String> trailers;

        public static JsonMessage generate() {
            return JsonMessage.builder()
                .id(UUID.randomUUID())
                .createTime(Instant.now())
                .headers(randomMap())
                .message(randomString(64))
                .trailers(randomList())
                .build();
        }

    }

    final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    final JsonMessage message = JsonMessage.generate();
    String serializedMessage;
    JsonNode tree;

    @Setup
    public void setup() throws JsonProcessingException {
        serializedMessage = mapper.writeValueAsString(message);
        tree = mapper.readTree(serializedMessage);
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void deserialize_asTree() {
        try {
            mapper.readTree(serializedMessage);
        } catch (Exception e) {
            // pass
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void deserialize_asClass() {
        try {
            mapper.readValue(serializedMessage, JsonMessage.class);
        } catch (Exception e) {
            // pass
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    public void convertValue_asClass() {
        JsonMessage message = mapper.convertValue(tree, JsonMessage.class);
    }

    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }

}
