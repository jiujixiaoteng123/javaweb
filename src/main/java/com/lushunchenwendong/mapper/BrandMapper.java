package com.lushunchenwendong.mapper;

import com.lushunchenwendong.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from lushun.tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加数据
     * @param brand
     */
    @Insert("insert into lushun.tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);
    /**
     * 删除
     */
    //DELETE FROM cs_user WHERE username = "巴巴"
    //@Delete("delete from product where id=#{id}")
    @Delete("delete from lushun.tb_brand where id=#{id}")
    void deleteById(Integer id);
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);
    /**
     * 修改数据
     */
    void upDate(Brand brand);
    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     */
    @Select("select * from lushun.tb_brand limit #{begin} , #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);
    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from lushun.tb_brand ")
    int selectTotalCount();
    /**
     * 分页条件查询
     * @param begin
     * @param size
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int
            size,@Param("brand") Brand brand);
    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}
