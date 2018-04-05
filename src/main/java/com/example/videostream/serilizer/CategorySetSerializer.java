package com.example.videostream.serilizer;

import com.example.videostream.model.Category;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Set;

public class CategorySetSerializer extends JsonSerializer<Set<Category>> {
//    @Override
//    public void serialize(Category category, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
//        jsonGenerator.writeStartObject();
//        jsonGenerator.writeFieldName("categoryId");
//        jsonGenerator.writeNumber(category.getCategoryId());
//        jsonGenerator.writeFieldName("categoryName");
//        jsonGenerator.writeNumber(category.getCategoryName());
//        jsonGenerator.writeEndObject();
//    }

    @Override
    public void serialize(Set<Category> categories, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for(Category item:categories){
            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("categoryName");
            jsonGenerator.writeString(item.getCategoryName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
