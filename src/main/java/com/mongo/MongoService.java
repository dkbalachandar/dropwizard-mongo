package com.mongo;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class MongoService extends Service<MongoConfiguration> {

    public static void main(String[] args) throws Exception {
        new MongoService().run(args);
    }

    @Override
    public void initialize(Bootstrap<MongoConfiguration> bootstrap) {
        bootstrap.setName("mongo");
    }

    @Override
    public void run(MongoConfiguration configuration, Environment environment) throws Exception {

        //Create Mongo instance
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        //Add Managed for managing the Mongo instance
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);
        //Add Health check for Mongo instance. This will be used from the Health check admin page
        environment.addHealthCheck(new MongoHealthCheck(mongo));
        //Create DB instance and wrap it in a Jackson DB collection
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<EmployeeData, String> jacksonDBCollection = JacksonDBCollection.wrap(db.getCollection("mongo"), EmployeeData.class, String.class);
        environment.addResource(new EmployeeResource(jacksonDBCollection));

    }
}
