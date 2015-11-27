package com.mongodb;

import org.bson.*;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by ViVFrolova on 27.11.2015.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document().append("str", "MongoDB, Hello").
                append("int", 42).
                append("l", 1L).
                append("double", 1.1).
                append("b", false).
                append("date", new Date()).
                append("objectId", new ObjectId()).
                append("null", null).
                append("embeddedDoc", new Document("x", 0)).
                append("list", Arrays.asList(1, 2, 3));

        String str = document.getString("str");
        int i = document.getInteger("int");

        printJson(document);

        BsonDocument bsonDocument = new BsonDocument().append("str", new BsonString("MongoDB, Hello")).
                append("int", new BsonInt32(42)).
                append("l", new BsonInt64(1L)).
                append("double", new BsonDouble(1.1)).
                append("b", new BsonBoolean(false)).
                append("date", new BsonDateTime(new Date().getTime())).
                append("objectId", new BsonObjectId(new ObjectId())).
                append("null", null).
                append("embeddedDoc", new BsonDocument("x", new BsonInt32(0))).
                append("list", new BsonArray(Arrays.asList(new BsonInt32(1), new BsonInt32(2), new BsonInt32(3))));



    }
}
