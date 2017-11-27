package com.xu.spider4j.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Mongo配置
 * 去掉_class列
 */
@Configuration
public class MongoConfig {

	 @Bean
	 public MongoDbFactory mongoDbFactory() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://101.200.44.47:27017");
		MongoClient mongoClient = new MongoClient(mongoClientURI);
		return new SimpleMongoDbFactory(mongoClient,"springboot");
	}

	 @Bean
	 public MongoTemplate mongoTemplate() throws Exception {
		MappingMongoConverter converter =
				new MappingMongoConverter( new DefaultDbRefResolver(mongoDbFactory()), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);
		return mongoTemplate;
	}

}
