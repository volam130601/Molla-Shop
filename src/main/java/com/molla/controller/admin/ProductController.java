package com.molla.controller.admin;

import com.molla.entity.Brand;
import com.molla.entity.Category;
import com.molla.entity.Product;
import com.molla.service.BrandService;
import com.molla.service.CategoryService;
import com.molla.service.ProductService;
import com.molla.service.UserService;
import com.molla.util.AuthenticationUser;
import com.molla.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
    @Autowired
    AuthenticationUser authenticationUser;
    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    public void common(Model model) {
        authenticationUser.filterUser(model);
    }


    @ModelAttribute("brands")
    public List<Brand> getAllBrands() {
        return brandService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/list")
    public String viewProductTable(Model model) {
        common(model);
        model.addAttribute("products", productService.findAll());
        return "admin/products";
    }

    @GetMapping("/addOrEdit")
    public String viewAddOrEdit(@RequestParam(value = "id", required = false) String productId,
                                Model model) {
        common(model);
        Product product = new Product();
        if (productId != null) {
            product = productService.findById(Long.parseLong(productId));
            product.setBrandId(product.getBrand().getId());
            product.setCategoryId(product.getCategory().getId());
            model.addAttribute("product", product);
            System.out.println("Edit");
        } else model.addAttribute("product", new Product());
        return "admin/product-form";
    }

    @PostMapping("/save")
    public String save(Model model, Product product,
                       @RequestParam(value = "main_image", required = false) MultipartFile mainImage,
                       @RequestParam(value = "extraImage", required = false) MultipartFile[] multipartFiles
    ) throws IOException {
        if (product.getId() != null) {
            Product existProduct = productService.findById(product.getId());
            existProduct.setName(product.getName());
            existProduct.setShortDescription(product.getShortDescription());
            existProduct.setFullDescription(product.getFullDescription());
            existProduct.setPrice(product.getPrice());
            existProduct.setQuantity(product.getQuantity());
            existProduct.setDiscount(product.getDiscount());
            existProduct.setEnabled(product.isEnabled());
            existProduct.setInStock(product.isInStock());
            existProduct.setUpdateAt(new Date());
            existProduct.setBrand(brandService.findById(product.getBrandId()));
            existProduct.setCategory(categoryService.findById(product.getCategoryId()));
            String uploadDir = "./product-images/" + product.getId();
            if (mainImage.getOriginalFilename().length() > 0) {
                FileUploadUtil.removeFile(uploadDir, existProduct.getMainImage());
                existProduct.setMainImage(mainImage.getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, existProduct.getMainImage(), mainImage);
            }
            if (multipartFiles[0].getOriginalFilename().length() > 0) {
                FileUploadUtil.removeFile(uploadDir, existProduct.getExtraImage1());
                existProduct.setExtraImage1(multipartFiles[0].getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, existProduct.getExtraImage1(), multipartFiles[0]);
            }
            if (multipartFiles[1].getOriginalFilename().length() > 0) {
                FileUploadUtil.removeFile(uploadDir, existProduct.getExtraImage2());
                existProduct.setExtraImage2(multipartFiles[1].getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, existProduct.getExtraImage2(), multipartFiles[1]);
            }
            if (multipartFiles[2].getOriginalFilename().length() > 0) {
                FileUploadUtil.removeFile(uploadDir, existProduct.getExtraImage3());
                existProduct.setExtraImage3(multipartFiles[2].getOriginalFilename());
                FileUploadUtil.saveFile(uploadDir, existProduct.getExtraImage3(), multipartFiles[2]);
            }
            productService.save(existProduct);
        } else {
            product.setMainImage(mainImage.getOriginalFilename());
            product.setExtraImage1(multipartFiles[0].getOriginalFilename());
            product.setExtraImage2(multipartFiles[1].getOriginalFilename());
            product.setExtraImage3(multipartFiles[2].getOriginalFilename());
            product.setBrand(brandService.findById(product.getBrandId()));
            product.setCategory(categoryService.findById(product.getCategoryId()));
            product.setUser(userService.findByEmail("admin@gmail.com"));

            product = productService.save(product);
            String uploadDir = "./product-images/" + product.getId();

            FileUploadUtil.saveFile(uploadDir, product.getMainImage(), mainImage);
            for (MultipartFile extraFile : multipartFiles) {
                String fileName = extraFile.getOriginalFilename();
                FileUploadUtil.saveFile(uploadDir, fileName, extraFile);
            }
        }
        return "redirect:/admin/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(Model model, @PathVariable("id") Long productId) throws IOException {
        String removeDir = "./product-images/" + productId;
        FileUploadUtil.removeFileAll(removeDir);
        productService.deleteById(productId);
        return "redirect:/admin/product/list";
    }
}
