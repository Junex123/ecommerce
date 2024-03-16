package coms.model.product;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ProductImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imgId;
    
    private String name;
    
    private String type;
    
    @Lob
    @Column(name = "image_data")
    private byte[] imageData;
    
   

    // Constructors
    public ProductImage() {
        super();
    }



	public ProductImage(Long imgId, String name, String type, byte[] imageData) {
		super();
		this.imgId = imgId;
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	}



	public Long getImgId() {
		return imgId;
	}



	public void setImgId(Long imgId) {
		this.imgId = imgId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public byte[] getImageData() {
		return imageData;
	}



	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

   



}
