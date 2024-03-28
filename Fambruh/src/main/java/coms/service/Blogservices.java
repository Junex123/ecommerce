package coms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

import coms.model.blog.Blog;
import coms.repository.Blogrepository;


import java.util.List;

@Service
public class Blogservices {

    @Autowired
    private Blogrepository blogRepository;

    // Service method to retrieve all blog posts
    public List<Blog> getAllBlogs() {
        // Add any necessary business logic here
        return blogRepository.findAll();
    }

    // Service method to retrieve a blog post by its ID
    public Blog getBlogById(Long id) {
        // Add any necessary business logic here
        return blogRepository.findById(id).orElse(null);
    }

    // Service method to retrieve a blog post by its title
    public Blog getBlogByTitle(String title) {
        return blogRepository.findByTitle(title);
    }

    // Service method to save a new blog post
    public Blog saveBlog(Blog blog) {
        // Set the post date to the current date when saving
        blog.setPostDate(new Date());
        // Add any necessary business logic here
        return blogRepository.save(blog);
    }

    // Service method to update an existing blog post
    public Blog updateBlog(Long id, Blog blog) {
        // Add any necessary business logic here
        Blog existingBlog = blogRepository.findById(id).orElse(null);
        if (existingBlog != null) {
            // Update the existing blog post with the new data
            existingBlog.setTitle(blog.getTitle());
            existingBlog.setContentshort(blog.getContentshort());
            existingBlog.setContent1(blog.getContent1());
            existingBlog.setContent2(blog.getContent2());
            existingBlog.setContent3(blog.getContent3());
            existingBlog.setAuthor(blog.getAuthor());
            // Add any other fields to update
            return blogRepository.save(existingBlog);
        }
        return null;
    }

    // Service method to delete a blog post by its ID
    public void deleteBlogById(Long id) {
        // Add any necessary business logic here
        blogRepository.deleteById(id);
    }

    // Service method to increment the view count of a blog post
    public void incrementViewCount(Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog != null) {
            blog.setViewCount(blog.getViewCount() + 1); // Increment view count
            blogRepository.save(blog);
        }
    }
}
