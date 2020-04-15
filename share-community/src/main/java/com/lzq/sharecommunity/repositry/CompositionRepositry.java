package com.lzq.sharecommunity.repositry;

import com.lzq.sharecommunity.entity.Composition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompositionRepositry extends JpaRepository<Composition,Integer> {
    public Page<Composition> findByUserId(int userId,Pageable pageable);

    @Override
    public Page<Composition> findAll(Pageable pageable);

    public Page<Composition> findAllByUserId(int userId, Pageable pageable);

}
