package io.entrance.web.model;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "productsrepo", path = "productsrepo")
public interface ProductsRepo extends JpaRepository<Products,Long> {
	
	List<Products> findAll();
	List<Products> findByPid(long id);
	List<Products> findByBrand(String brand);

}
