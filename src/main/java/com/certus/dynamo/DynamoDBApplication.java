package com.certus.dynamo;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.certus.dynamo.datos.model.Blog;
import com.certus.dynamo.datos.repository.BlogRepository;


@EnableDynamoDBRepositories(basePackages = "com.certus.dynamo.datos.repository")
@EntityScan(basePackages = "com.certus.dynamo.datos.model")
@ComponentScan(basePackages = "com.certus.dynamo")
@SpringBootApplication
public class DynamoDBApplication implements CommandLineRunner{
	
	
	 private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;
	    
	@Autowired
	private BlogRepository blogRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DynamoDBApplication.class);
	}

	@Override
	public void run(String... args) throws Exception {
		
		 dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
	     CreateTableRequest tableRequest = dynamoDBMapper
	                .generateCreateTableRequest(Blog.class);

	        tableRequest.setProvisionedThroughput(
	                new ProvisionedThroughput(1L, 1L));

	     TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
	     
	     Blog blog = new Blog();
	     blog.setId("1");
	     blog.setDescripcion("Dfsdf");
	     blog.setNombre("dfdw");
	     
	     blog = new Blog();
	     blog.setId("2");
	     blog.setDescripcion("Dfsdf");
	     blog.setNombre("orlando puto");
		
	     blog = blogRepository.save(blog);
	}

}
