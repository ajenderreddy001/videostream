package com.example.videostream.serilizer;

import com.example.videostream.model.Media;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Set;

public class MediaSetSerializer extends JsonSerializer<Set<Media>> {
//    public void serialize(Media media, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeFieldName("mediaId");
//        jsonGenerator.writeNumber(media.getMediaId());
//        jsonGenerator.writeFieldName("mediaName");
//        jsonGenerator.writeNumber(media.getMediaName());
//        jsonGenerator.writeEndObject();
//        //jsonGenerator.writeFieldName("third");
//        //jsonGenerator.writeNumber(value.getAnyAnotherClass().getThirdValue());
//
//    }

    @Override
    public void serialize(Set<Media> media, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for(Media item:media){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("mediaId");
            jsonGenerator.writeNumber(item.getMediaId());
            jsonGenerator.writeFieldName("mediaName");
            jsonGenerator.writeString(item.getMediaName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
