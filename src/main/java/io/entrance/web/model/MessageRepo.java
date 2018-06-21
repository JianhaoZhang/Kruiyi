package io.entrance.web.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "message", path = "message")
public interface MessageRepo extends JpaRepository<Message,Long> {
	
	List<Message> findAll();

}
