package com.ideologyCreativeStudio.test.businesslayer.impl;

import com.ideologyCreativeStudio.test.businesslayer.dto.CommentDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.CommentResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class CommentServiceImpl implements CommentService {
    @Override
    public Page<CommentDTO> getAll(Pageable p) {
        return null;
    }

    @Override
    public CommentDTO getById(Long id) {
        return null;
    }

    @Override
    public CommentDTO save(CommentResponseDTO e) {
        return null;
    }

    @Override
    public CommentDTO update(Long id, CommentResponseDTO e) {
        return null;
    }

    @Override
    public CommentDTO delete(Long id) {
        return null;
    }
}
