package cr.fi.proamerica.pruebatecnica.api.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document("identitydocuments")
public class IdentityDocument {
	
	 @Id
	private String number;
	
	private String expiryDate;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date emissionDate = new Date();
	
	private DocumentType documentType;
	
	public IdentityDocument() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IdentityDocument(String number, String expiryDate, Date emissionDate, DocumentType documentType) {
		super();
		this.number = number;
		this.expiryDate = expiryDate;
		this.emissionDate = emissionDate;
		this.documentType = documentType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getEmissionDate() {
		return emissionDate;
	}

	public void setEmissionDate(Date emissionDate) {
		this.emissionDate = emissionDate;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}	

}
