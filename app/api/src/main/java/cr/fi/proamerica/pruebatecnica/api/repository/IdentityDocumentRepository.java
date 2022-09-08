package cr.fi.proamerica.pruebatecnica.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import cr.fi.proamerica.pruebatecnica.api.model.IdentityDocument;

public interface IdentityDocumentRepository extends MongoRepository<IdentityDocument, String> {

	@Query("{number:'?0'}")
	IdentityDocument findIdentityDocumentByNumber(String number);
	
}
