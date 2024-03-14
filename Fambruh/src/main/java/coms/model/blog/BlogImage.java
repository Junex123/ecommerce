package coms.model.blog;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BlogImage {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long imgId;
    
    private String name;
    
    private String type;
    
    @Lob
    @Column(name = "image_data")
    private byte[] imageData;
    
    @OneToOne(mappedBy = "blogImage")
    @JsonBackReference
    private Blog blog;

    // Constructors
    public BlogImage() {
        super();
    }

    public BlogImage(Long imgId, String name, String type, byte[] imageData, Blog blog) {
        super();
        this.imgId = imgId;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.blog = blog;
    }

    // Getters and setters
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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
