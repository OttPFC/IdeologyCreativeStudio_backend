package com.ideologyCreativeStudio.test.businesslayer.interfaces;

import com.ideologyCreativeStudio.test.businesslayer.dto.CommentDTO;
import com.ideologyCreativeStudio.test.businesslayer.dto.response.CommentResponseDTO;
import com.ideologyCreativeStudio.test.businesslayer.interfaces.generics.CRUDService;

public interface CommentService extends CRUDService<CommentDTO, CommentResponseDTO> {
}
