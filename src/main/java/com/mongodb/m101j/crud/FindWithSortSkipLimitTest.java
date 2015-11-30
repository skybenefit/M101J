package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by ViVFrolova on 27.11.2015.
 */
public class FindWithSortSkipLimitTest {
    public static void main(String[] args) {
        // Enable MongoDB logging in general
        //System.setProperty("DEBUG.MONGO", "true");

         // Enable DB operation tracing
        //System.setProperty("DB.TRACE", "true");
        Logger mongoLogger = Logger.getLogger("com.mongodb");
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.

        MongoClient               client = new MongoClient( );
        MongoDatabase             db     = client.getDatabase("course");
        MongoCollection<Document> coll   = db.getCollection("findWithFilterTest");
        coll.drop();

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++) {
                coll.insertOne(new Document().
                        append("i", i).
                        append("j", j));
            }
        }

        Bson projection = fields(include("i", "j"), excludeId());
        //Bson sort = new Document("i", 1).append("j", -1);
        //Bson sort = Sorts.orderBy(Sorts.ascending("i"), Sorts.descending("j"));
        Bson sort = descending("j", "i");

        List<Document> all = coll.find().
                projection(projection).
                sort(sort).
                skip(20).
                limit(50).
                into(new ArrayList<Document>());

        for(Document cur : all){
            printJson(cur);
        }

    }

}
