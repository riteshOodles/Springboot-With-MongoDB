package com.mongoExample.config;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class MongoIndexConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDatabaseName'");
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

}
