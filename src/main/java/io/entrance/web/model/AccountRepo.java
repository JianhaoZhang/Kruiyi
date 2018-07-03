package io.entrance.web.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepo extends JpaRepository<Account,Long> {
	
	List<Account> findAll();
	Account findByName(String name);

}
