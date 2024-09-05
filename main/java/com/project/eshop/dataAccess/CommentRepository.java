package com.project.eshop.dataAccess;

import com.project.eshop.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
