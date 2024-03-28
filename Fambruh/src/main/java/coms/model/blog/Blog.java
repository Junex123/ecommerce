package coms.model.blog;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date; // Import Date class

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    @Lob
    private String contentshort;
    
    
    @Lob
    private String content1;

    @Lob
    private String content2;

    @Lob
    private String content3;

    // New field for author
    private String author;

    // Field to store the date of the post
    @Temporal(TemporalType.TIMESTAMP)
    private Date postDate;
    
    private int viewCount;

    @OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private BlogImage blogImage;

    // Constructors, getters, and setters
    public Blog() {
    }

    
    



	public Blog(Long id, String title, String contentshort, String content1, String content2, String content3,
			String author, Date postDate, int viewCount, BlogImage blogImage) {
		super();
		this.id = id;
		this.title = title;
		this.contentshort = contentshort;
		this.content1 = content1;
		this.content2 = content2;
		this.content3 = content3;
		this.author = author;
		this.postDate = postDate;
		this.viewCount = viewCount;
		this.blogImage = blogImage;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentshort() {
		return contentshort;
	}

	public void setContentshort(String contentshort) {
		this.contentshort = contentshort;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getContent3() {
		return content3;
	}

	public void setContent3(String content3) {
		this.content3 = content3;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public BlogImage getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(BlogImage blogImage) {
		this.blogImage = blogImage;
	}

  

}
