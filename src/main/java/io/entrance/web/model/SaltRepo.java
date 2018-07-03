package io.entrance.web.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "salt", path = "salt")
public interface SaltRepo extends JpaRepository<Salt,Long> {
	
	List<Salt> findAll();
	Salt findByName(String name);

}
