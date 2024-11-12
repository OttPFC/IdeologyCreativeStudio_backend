package com.ideologyCreativeStudio.test.businesslayer.interfaces.generics;

import com.ideologyCreativeStudio.test.businesslayer.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CRUDService <T extends BaseDTO, A extends BaseDTO>{

    Page<T> getAll(Pageable p);

    T getById(Long id);

    T save(A e);

    T update(Long id, A e);

    T delete(Long id);
}
