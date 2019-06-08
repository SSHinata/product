package com.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.product.dao.ProductDao;
import com.product.model.Product;
import com.product.model.ProductExample;
import com.product.service.IProductService;
import com.product.util.StringUtil;
import com.product.util.UUIDUtil;
import com.product.util.page.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service("productService")
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getProduct(ProductExample example) {
        return productDao.selectByExample(example);
    }

    public void addImg(MultipartFile pic,String filePath,String fileName) throws IOException {
        //先判断文件夹是否存在
        File path = new File(filePath);
        System.out.println(path);
        if(!path.exists()){
            path.mkdirs();
        }
        pic.transferTo(new File(filePath, fileName));
    }

    public void deleteImg(String filePath,String fileName){
        File file = new File(filePath + "/" + fileName);
        if(file.exists() && file.isFile()){
            file.delete();
        }
    }

    @Override
    public void add(Product product, MultipartFile pic) throws Exception {
        String originalFileName = pic.getOriginalFilename();
        String oldFileType = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = UUIDUtil.getUUID() + oldFileType;
        product.setImgName(fileName);
        // 文件路径 存到对象中 // fileName 文件名
        // 完成文件的 写入
        addImg(pic,product.getImgUrl(),fileName);
        System.out.println("图片上传---"+product.getImgUrl()+fileName);
        productDao.insert(product);
    }

    @Override
    public Integer update(Product product) {
        int i = productDao.updateByPrimaryKeySelective(product);
        return i;
    }

    @Override
    public Product getById(Integer productId) {
        Product product = productDao.selectByPrimaryKey(productId);
        return product;
    }

    @Override
    public void updateProduct(Product product, MultipartFile pic) throws IOException {
        if(pic != null && pic.getSize() > 0){
            String originalFileName = pic.getOriginalFilename();
            String oldFileType = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = UUIDUtil.getUUID() + oldFileType;
            //删除图片
            deleteImg(product.getImgUrl(),product.getImgName());
            product.setImgName(fileName);
            //添加图片
            addImg(pic,product.getImgUrl(),fileName);
        }
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public PageBean<Product> getProductPage(Product product) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        criteria.andProductStatusEqualTo(1);
        PageHelper.startPage(product.getPageNum(),8);
        List<Product> products = getProduct(productExample);
        return new PageBean<Product>(products);
    }

    @Override
    public PageBean<Product> getProductPageByManager(Product product) {
        ProductExample productExample = new ProductExample();
        ProductExample.Criteria criteria = productExample.createCriteria();
        if(!StringUtil.isEmpty(product.getProductName())){
            criteria.andProductNameLike("%"+product.getProductName()+"%");
        }
        PageHelper.startPage(product.getPageNum(),10);
        List<Product> products = getProduct(productExample);
        return new PageBean<Product>(products);
    }
}
