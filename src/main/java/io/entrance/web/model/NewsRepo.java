package io.entrance.web.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface NewsRepo extends JpaRepository<News,Long> {
	
	List<News> findAll();
	List<News> findById(String id);

}
