package cr.fi.proamerica.pruebatecnica.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cr.fi.proamerica.pruebatecnica.api.model.IdentityDocument;
import cr.fi.proamerica.pruebatecnica.api.repository.IdentityDocumentRepository;

@RestController
@CrossOrigin
public class IdentityDocumentController {

	@Autowired
	IdentityDocumentRepository idr;

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/idocs")	
	List<IdentityDocument> todos() {
		return idr.findAll();
	}

	@PreAuthorize("hasAuthority('admin')")
	@GetMapping("/idoc/{number}")
	ResponseEntity<?> leer(@PathVariable String number) {	
		ResponseEntity<?> resp = null;
		IdentityDocument idoc = idr.findIdentityDocumentByNumber(number);
		if(idoc != null) {
			resp = ResponseEntity.ok(idoc);
		}else {
			resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
		}		
		return resp;		
	}

	@PreAuthorize("hasAuthority('admin')")
	@PostMapping("/idocs")
	ResponseEntity<?> nuevo(@RequestBody IdentityDocument idoc) {

		ResponseEntity<?> resp = null;
		try {
			idr.save(idoc);
			resp = ResponseEntity.ok(idoc);
		}catch( Exception ex ) {
			ex.printStackTrace();
			resp = ResponseEntity.internalServerError().body(
					"error interno");
		}

		return resp;
	}

	@PreAuthorize("hasAuthority('admin')")
	@PutMapping("/idocs")
	ResponseEntity<?> editar(@RequestBody IdentityDocument idoc) {

		ResponseEntity<?> resp = null;
		IdentityDocument tmpIdoc = null;		

		tmpIdoc = idr.findIdentityDocumentByNumber(idoc.getNumber());

		if(tmpIdoc!=null) {

			tmpIdoc.setExpiryDate(idoc.getExpiryDate());
			tmpIdoc.setEmissionDate(idoc.getEmissionDate());
			tmpIdoc.setDocumentType(idoc.getDocumentType());
			
			idr.save(tmpIdoc);
			resp = ResponseEntity.ok(tmpIdoc);

		}else{			
			resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
		}

		return resp;
	}
	
	@PreAuthorize("hasAuthority('admin')")
	@DeleteMapping("/idoc/{number}")
	ResponseEntity<?> editar(@PathVariable String number) {

		ResponseEntity<?> resp = null;
		IdentityDocument tmpIdoc = null;		

		tmpIdoc = idr.findIdentityDocumentByNumber(number);

		if(tmpIdoc!=null) {
			
			idr.delete(tmpIdoc);
			resp = ResponseEntity.ok("borrado");

		}else{			
			resp = ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
		}

		return resp;
	}

}
