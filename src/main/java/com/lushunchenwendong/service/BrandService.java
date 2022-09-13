package com.lushunchenwendong.service;

import com.lushunchenwendong.pojo.Brand;
import com.lushunchenwendong.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();
    /**
     * 添加
     */
    void add(Brand brand);
    /**
     * 删除
     */
    void deleteById(Integer id);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds( int[] ids);
    /**
     * UpDate修改数据
     */
    void upDate(Brand brand);
    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage, int pageSize);
    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
