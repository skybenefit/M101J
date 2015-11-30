package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by ViVFrolova on 30.11.2015.
 */
public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findWithFilterTest");
        coll.drop();

        for (int i = 0; i < 8; i++) {
            coll.insertOne(new Document().
                    append("_id", i).
                    append("x", i));
        }


        //coll.replaceOne(eq("x", 5),  new Document("_id", 5).append("x", 20).append("update", true));
        //coll.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)));
        //coll.updateOne(eq("_id", 9), new Document("$set", new Document("x", 20)), new UpdateOptions().upsert(false));
        coll.updateMany(gte("_id", 5), new Document("$inc", new Document("x", 1)));

        List<Document> all = coll.find().into(new ArrayList<Document>());
        for (Document cur : all) {
            printJson(cur);
        }

    }
}
