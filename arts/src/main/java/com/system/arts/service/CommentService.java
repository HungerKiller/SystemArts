package com.system.arts.service;

import com.system.arts.entity.Comment;
import com.system.arts.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + id));
    }

    public List<Comment> getCommentsByResourceId(int resourceId) {
        return commentRepository.findByResourceId(resourceId);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment updatedComment) {
        Optional<Comment> optionalComment = commentRepository.findById(updatedComment.getId());
        if (optionalComment.isPresent()) {
            Comment existingComment = optionalComment.get();
            existingComment.setContent(updatedComment.getContent());
            existingComment.setUser(updatedComment.getUser());
            existingComment.setResourceId(updatedComment.getResourceId());
            return commentRepository.save(existingComment);
        } else {
            throw new IllegalArgumentException("Comment not found with id " + updatedComment.getId());
        }
    }

    public void deleteComment(int id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id " + id));
        commentRepository.delete(comment);
    }
}
