package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class App 
{
    //first_commit
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        //MongoClient client = new MongoClient("localhost", 27017);
        //MongoClient client = new MongoClient( new ServerAddress("localhost", 27017));
        //MongoClient client = new MongoClient( asList(new ServerAddress("localhost", 27017)));
        //MongoClient client = new MongoClient( new MongoClientURI ("mongodb://localhost:27017"));

        // you might want to configure it in an IoC container like Spring or Juice
        //MongoClientOptions options = MongoClientOptions.builder().build(); //is immutable
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(500).build(); // default connection pool 100
        MongoClient client = new MongoClient( new ServerAddress(), options); //hardweight
        //MongoDatabase db = client.getDatabase("test"); //lightweight, immutable
        MongoDatabase db = client.getDatabase("test").withReadPreference(ReadPreference.secondary()); //Preference предпочтение
        //db.getCollection("people").withReadPreference(); //lightweight, immutable

        //MongoCollection<Document> coll = db.getCollection("test");
        MongoCollection<BsonDocument> coll = db.getCollection("test", BsonDocument.class);






    }
}
