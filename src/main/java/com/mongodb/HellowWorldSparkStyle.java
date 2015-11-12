package com.mongodb;

import spark.*;
import spark.Response;

/**
 * Created by ViVFrolova on 28.10.2015.
 */
public class HellowWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello world Spark";
            }
        });
    }
}
