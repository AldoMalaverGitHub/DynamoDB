package com.certus.dynamo.datos.repository;

import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;

import com.certus.dynamo.datos.model.Blog;

public interface BlogRepository extends DynamoDBCrudRepository<Blog, String>{

}
