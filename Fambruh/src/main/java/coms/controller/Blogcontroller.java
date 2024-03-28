package coms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import coms.configuration.ImageUtil;
import coms.model.*;
import coms.model.blog.Blog;
import coms.model.blog.BlogImage;
import coms.service.Blogservices;

@RestController
@CrossOrigin(origins = "*")
public class Blogcontroller {

    @Autowired
    private Blogservices blogService;

    @Autowired
    private ObjectMapper objectMapper;

 // Add new blog post
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add/blog")
    public ResponseEntity<?> addNewBlog(@RequestParam("blog") String blogData,
                                        @RequestParam("image") MultipartFile file) throws IOException {
        // Check if the uploaded file is an image or GIF
        if (!file.getContentType().startsWith("image/") && !file.getContentType().equals("image/gif")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only images and GIFs are allowed.");
        }

        // Process the upload if it's an image or GIF
        BlogImage img = new BlogImage();
        img.setName(file.getOriginalFilename());
        img.setType(file.getContentType());
        img.setImageData(ImageUtil.compressImage(file.getBytes()));

        Blog blog = null;
        try {
            blog = objectMapper.readValue(blogData, Blog.class);
            blog.setBlogImage(img);
            blog.setPostDate(new Date()); // Set the post date to the current date
            blog.setViewCount(0); // Initialize view count to 0
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Request");
        }

        Blog savedBlog = this.blogService.saveBlog(blog);
        return ResponseEntity.ok(savedBlog);
    }


    // Update existing blog post
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/blog/{id}")
    public ResponseEntity<?> updateBlog(@PathVariable("id") Long id, @Valid @RequestBody Blog blog) {
        Blog existingBlog = blogService.getBlogById(id);
        if (existingBlog != null) {
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContent1(blog.getContent1());
            existingBlog.setContent2(blog.getContent2());
            existingBlog.setContent3(blog.getContent3());
            existingBlog.setContentshort(blog.getContentshort());
            existingBlog.setAuthor(blog.getAuthor());
            existingBlog.setPostDate(new Date()); // Update the post date to current date
            existingBlog.setViewCount(blog.getViewCount()); // Preserve existing view count
            // Add any other fields to update
            blogService.saveBlog(existingBlog);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get blog post by ID
    @GetMapping("/get/blog/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable("id") Long id) {
        Blog blog = blogService.getBlogById(id);
        if (blog != null) {
            BlogImage img = new BlogImage();
            img.setImageData(ImageUtil.decompressImage(blog.getBlogImage().getImageData()));
            img.setImgId(blog.getBlogImage().getImgId());
            img.setName(blog.getBlogImage().getName());
            img.setType(blog.getBlogImage().getType());
            blog.setBlogImage(img);
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Get all blog posts
    @GetMapping("/get/all-blogs")
    public ResponseEntity<?> getAllBlogs() {
        List<Blog> allBlogs = blogService.getAllBlogs();
        allBlogs.forEach(blog -> {
            BlogImage img = new BlogImage();
            img.setImageData(ImageUtil.decompressImage(blog.getBlogImage().getImageData()));
            img.setImgId(blog.getBlogImage().getImgId());
            img.setName(blog.getBlogImage().getName());
            img.setType(blog.getBlogImage().getType());
            blog.setBlogImage(img);
        });
        if (allBlogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(allBlogs);
        }
    }

    // Delete blog post by ID
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable("id") Long id) {
        blogService.deleteBlogById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    
   
    @GetMapping("/get/blogByTitle/{title}")
    public ResponseEntity<?> getBlogByTitle(@PathVariable("title") String title) {
        Blog blog = blogService.getBlogByTitle(title);
        if (blog != null) {
            BlogImage img = new BlogImage();
            img.setImageData(ImageUtil.decompressImage(blog.getBlogImage().getImageData()));
            img.setImgId(blog.getBlogImage().getImgId());
            img.setName(blog.getBlogImage().getName());
            img.setType(blog.getBlogImage().getType());
            blog.setBlogImage(img);
            return ResponseEntity.ok(blog);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
