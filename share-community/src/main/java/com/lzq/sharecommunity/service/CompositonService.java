package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.Composition;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CompositonService {
    public List<Composition> getAll();

    public Page<Composition> getByUserId(int pageNum,int userId);

    public Composition getById(int id);

    public boolean addCom(Composition composition);

    public boolean deleteCom(int id);

    public boolean updateCom(Composition composition);

    public Page<Composition> getAllByPage(int pageNum, int PageSize);

    public Page<Composition> getAllByPageAndUserId(int pageNum, int PageSize, int uId);
}
