package com.project.eshop.business.concretes;

import com.project.eshop.business.abstracts.CommentService;
import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.core.utilities.results.ErrorDataResult;
import com.project.eshop.core.utilities.results.SuccessDataResult;
import com.project.eshop.dataAccess.CommentRepository;
import com.project.eshop.entities.concretes.Comment;
import com.project.eshop.entities.dto.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentManager implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentManager(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<CommentDto>> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(commentDtos, "Comments retrieved successfully");
    }

    @Override
    public DataResult<CommentDto> getById(long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (commentOptional.isEmpty()) {
            return new ErrorDataResult<>("Comment not found");
        }

        Comment comment = commentOptional.get();
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return new SuccessDataResult<>(commentDto, "Comment found successfully");
    }

    @Override
    public DataResult<CommentDto> add(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Comment savedComment = commentRepository.save(comment);
        CommentDto savedCommentDto = modelMapper.map(savedComment, CommentDto.class);
        return new SuccessDataResult<>(savedCommentDto, "Comment added successfully");
    }

    @Override
    public DataResult<CommentDto> update(CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDto.getId());
        if (commentOptional.isEmpty()) {
            return new ErrorDataResult<>("Comment not found");
        }

        Comment comment = commentOptional.get();
        comment.setComment(commentDto.getComment());
        Comment updatedComment = commentRepository.save(comment);
        CommentDto updatedCommentDto = modelMapper.map(updatedComment, CommentDto.class);
        return new SuccessDataResult<>(updatedCommentDto, "Comment updated successfully");
    }

    @Override
    public DataResult<String> delete(long id) {
        commentRepository.deleteById(id);
        return new SuccessDataResult<>("Comment deleted successfully");
    }
}
