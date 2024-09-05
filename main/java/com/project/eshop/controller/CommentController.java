package com.project.eshop.controller;

import com.project.eshop.business.abstracts.CommentService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public DataResult<List<CommentDto>> getAllComments() {
        return commentService.getAll();
    }

    @GetMapping("/{id}")
    public DataResult<CommentDto> getCommentById(@PathVariable long id) {
        return commentService.getById(id);
    }

    @PostMapping("/add")
    public DataResult<CommentDto> addComment(@RequestBody CommentDto commentDto) {
        return commentService.add(commentDto);
    }

    @PutMapping("/update")
    public DataResult<CommentDto> updateComment(@RequestBody CommentDto commentDto) {
        return commentService.update(commentDto);
    }

    @DeleteMapping("/delete/{id}")
    public DataResult<String> deleteComment(@PathVariable long id) {
        return commentService.delete(id);
    }
}
