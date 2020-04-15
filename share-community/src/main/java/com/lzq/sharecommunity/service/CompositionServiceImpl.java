package com.lzq.sharecommunity.service;

import com.lzq.sharecommunity.entity.Composition;
import com.lzq.sharecommunity.exception.MySQLException;
import com.lzq.sharecommunity.repositry.CompositionRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompositionServiceImpl implements CompositonService {
    @Autowired
    CompositionRepositry repositry;

    @Override
    public List<Composition> getAll() {
        return repositry.findAll();
    }

    @Override
    public Page<Composition> getByUserId(int pageNum,int userId) {
        //按时间排序
        int pageSize = 3;
        Sort s = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(pageNum,pageSize,s);
        return repositry.findByUserId(userId,pageable);
    }

    @Override
    public Composition getById(int id) {
        return repositry.getOne(id);
    }

    @Override
    public boolean addCom(Composition composition) {
        if (composition!=null && composition.getUserId()>=1000){            // >=1000是因为数据库用户id从1000开始自增
            composition.setCreatedDate(new Date());
            composition.setLike_count(0);
            composition.setCommentCount(0);
            String content = composition.getContent();
            int index = content.indexOf("![](");
            if (index!=-1){                             //判断有没有图片插入，markdown图片格式为![](xxx.jpg),并且找的是第一张图片
                index+=4;                               //获取路径起始位置
                composition.setFirstPicture(composition.getContent().substring(index,content.indexOf(")")));
            }
            repositry.save(composition);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCom(int id) {
        if (repositry.existsById(id)){
            repositry.deleteById(id);
            return true;
        }
        else {
            try {
                throw new MySQLException("删除失败，作品不存在");
            } catch (MySQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateCom(Composition composition){
        if (composition!=null){
            repositry.save(composition);
            return true;
        }
        else {
            try {
                throw new MySQLException("更新失败，输入参数为空");
            } catch (MySQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Page<Composition> getAllByPage(int pageNum, int PageSize) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Composition> all = repositry.findAll(pageable);
        return all;
    }

    @Override
    public Page<Composition> getAllByPageAndUserId(int pageNum, int PageSize, int uId) {
        Pageable pageable = PageRequest.of(pageNum, PageSize);
        Page<Composition> allByUserId = repositry.findAllByUserId(uId, pageable);
        return allByUserId;
    }
}
