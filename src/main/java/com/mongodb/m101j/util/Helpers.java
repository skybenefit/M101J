package com.mongodb.m101j.util;

import org.bson.Document;

import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;

/**
 * Created by ViVFrolova on 27.11.2015.
 */
public class Helpers {
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder().
                        isEncodingCollectibleDocument(true).
                        build());

        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
