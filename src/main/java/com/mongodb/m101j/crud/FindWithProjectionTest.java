package com.mongodb.m101j.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.m101j.util.Helpers.printJson;

/**
 * Created by ViVFrolova on 27.11.2015.
 */
public class FindWithProjectionTest {
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
            coll.insertOne(new Document().
                    append("x", new Random().nextInt(2)).
                    append("y", new Random().nextInt(100)));
        }

        //Bson filter = new Document("x", 0).
        //        append("y", new Document("$gt", 10).append("$lt", 90));

        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        List<Document> all = coll.find(filter).into(new ArrayList<Document>());
        for(Document cur : all){
            printJson(cur);
        }

        long count = coll.count(filter);
        System.out.println();
        System.out.println(count);

    }

}
