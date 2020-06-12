package com.thoughtworks.capability.gtb.vo;

import java.io.IOException;
import java.sql.Time;
import java.time.*;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author itutry
 * @create 2020-05-21_16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = EventVoDeserializer.class)
public class EventVo {

  private String id;
  private String name;
  private EventType type;


  //@JsonDeserialize(using = TimeDeserializer.class)
  @JsonSerialize(using = TimeSerializer.class)
  private LocalDateTime time;

  @JsonUnwrapped
  private UserVo user;

  private static class TimeSerializer extends StdSerializer<LocalDateTime> {

    protected TimeSerializer() {
      super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
      gen.writeNumber(value.atZone(ZoneOffset.UTC).toEpochSecond());
    }
  }

  private static class TimeDeserializer extends StdDeserializer<LocalDateTime> {

    protected TimeDeserializer() {
      super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
      JsonNode node = p.getCodec().readTree(p);
      LocalDateTime time = LocalDateTime.ofInstant(Instant.ofEpochSecond(node.asLong()), ZoneId.systemDefault());
      return time;
    }
  }

}
