package com.lushunchenwendong.web.servlet;

import com.alibaba.fastjson.JSON;
import com.lushunchenwendong.pojo.Brand;
import com.lushunchenwendong.pojo.PageBean;
import com.lushunchenwendong.service.BrandService;
import com.lushunchenwendong.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //调用service查询
        List<Brand> brands = brandService.selectAll();
        //转为JSON
        String jsonString = JSON.toJSONString(brands);
        //写数据
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加
     * @param request
     * @param response
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //1.接收品牌数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();//json字符串
        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);
        //调用service的添加方法
        brandService.add(brand);

        //响应成功标识
        response.getWriter().write("SUCCESS");
    }
    /**
     * 单个删除
     */
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("删除数据");
        String _id=request.getParameter("id");

        int id=Integer.parseInt(_id);
        //2.调用service方法添加
        brandService.deleteById(id);
        //3.响应添加成功的标识s
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据 json [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2. 调用service添加
        brandService.deleteByIds(ids);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }
    /**
     * 修改
     */
    public void upDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收修改后的品牌数据
        BufferedReader br = request.getReader();
        String params=br.readLine();  //json字符串
        Brand _brand = JSON.parseObject(params, Brand.class);
        //将需要修改的内容封装为一个brand对象
        System.out.println("修改执行6");
        Brand brand=new Brand();
        brand.setId(_brand.getId());
        brand.setBrandName(_brand.getBrandName());
        brand.setCompanyName(_brand.getCompanyName());
        brand.setOrdered(_brand.getOrdered());
        brand.setDescription(_brand.getDescription());
        brand.setStatus(_brand.getStatus());
        //2.调用service方法进行修改
        brandService.upDate(brand);
        //3.响应修改成功的标识
        response.getWriter().write("success");
    }
    /**
     *分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    /**
     * 分页条件查询
    * @param request
    * @param response
    * @throws ServletException
    * @throws IOException
    */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
         // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);
         //2. 调用service查询
        PageBean<Brand> pageBean =
                brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
