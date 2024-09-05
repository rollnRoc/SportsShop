package com.project.eshop.business.abstracts;

import com.project.eshop.core.utilities.results.DataResult;
import com.project.eshop.entities.dto.CommentDto;

import java.util.List;

public interface CommentService {
    DataResult<List<CommentDto>> getAll();
    DataResult<CommentDto> getById(long id);
    DataResult<CommentDto> add(CommentDto commentDto);
    DataResult<CommentDto> update(CommentDto commentDto);
    DataResult<String> delete(long id);
}
