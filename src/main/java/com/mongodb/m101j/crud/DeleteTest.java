package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by ViVFrolova on 30.11.2015.
 */
public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("test");
        coll.drop();

        for (int i = 0; i < 8; i++) {
            coll.insertOne(new Document().
                    append("_id", i));
        }


        //coll.deleteMany(gt("_id", 4));
        coll.deleteOne(eq("_id", 4));


        List<Document> all = coll.find().into(new ArrayList<Document>());
        for (Document cur : all) {
            printJson(cur);
        }

    }
}
