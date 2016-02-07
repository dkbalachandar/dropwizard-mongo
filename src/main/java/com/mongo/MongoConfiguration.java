package com.mongo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MongoConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String mongohost;

    @JsonProperty
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "mongoDB";

    public String getMongohost() {
        return mongohost;
    }

    public int getMongoport() {
        return mongoport;
    }

    public void setMongoport(int mongoport) {
        this.mongoport = mongoport;
    }

    public String getMongodb() {
        return mongodb;
    }

    public void setMongodb(String mongodb) {
        this.mongodb = mongodb;
    }

    public void setMongohost(String mongohost) {
        this.mongohost = mongohost;

    }
}
