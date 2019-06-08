package com.product.service;

import com.product.model.Product;
import com.product.model.ProductExample;
import com.product.util.page.PageBean;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {
    List<Product> getProduct(ProductExample example);
    public void add(Product product, MultipartFile pic) throws Exception;
    public Integer update(Product product);

    Product getById(Integer productId);

    void updateProduct(Product product, MultipartFile productFile) throws IOException;

    PageBean<Product> getProductPage(Product product);

    PageBean<Product> getProductPageByManager(Product product);
}
