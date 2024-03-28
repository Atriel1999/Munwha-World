package com.multi.bbs.shop.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.shop.model.repository.ProductRepository;
import com.multi.bbs.shop.model.vo.Product;

@Service
public class ShopService {
	@Autowired
	private ProductRepository prepo;
//	@Autowired
//	private ShopRepository srepo;
//	@Autowired
//	private ProductReplyRepository prrepo;
//	@Autowired
//	private OrdersRepository orepo;
//	@Autowired
//	private CartRepository crepo;
	
	public Product findByPno(int pno) {
		return prepo.findByPno(pno);
	}
	
	
	public List<Product> searchProductByTitle(String title, int limit, int offset) {
		return prepo.findByTitleContaining(title, limit, offset);
	}
	public List<Product> searchProductByTitleDesc(String title, int limit, int offset) {
		return prepo.findByTitleContainingDesc(title, limit, offset);
	}
	public int getproductcount(String title) {
		return prepo.countByTitleContaining(title);
	}
	
	
}