package com.mongodb.m101j.spark;

import com.mongodb.HelloWorldFreemarkerStyle;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.bson.Document;
import spark.Request;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ViVFrolova on 30.11.2015.
 */
public class HelloWorldMongoDBSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldMongoDBSparkFreemarkerStyle.class, "/");

        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        final MongoCollection<Document> collection = database.getCollection("hello");
        collection.drop();

        collection.insertOne(new Document("name", "MongoDB"));

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, spark.Response response) {
                StringWriter writer = new StringWriter();

                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");

                    Document document = collection.find().first();
                    helloTemplate.process(document, writer);

                } catch (IOException e) {
                    halt(500);
                    e.printStackTrace();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }

                return writer;
            }
        });
    }
}
