package com.multi.bbs.shop.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.bbs.shop.model.repository.CartRepository;
import com.multi.bbs.shop.model.repository.OrdersRepository;
import com.multi.bbs.shop.model.repository.ProductReplyRepository;
import com.multi.bbs.shop.model.repository.ProductRepository;
import com.multi.bbs.shop.model.repository.ShopRepository;
import com.multi.bbs.shop.model.vo.Product;

@Service
public class ShopService {
	@Autowired
	private ProductRepository prepo;
	@Autowired
	private ShopRepository srepo;
	@Autowired
	private ProductReplyRepository prrepo;
	@Autowired
	private OrdersRepository orepo;
	@Autowired
	private CartRepository crepo;
	
	
	public List<Product> searchProductByTitle(String title) {
		return prepo.findByTitleContaining(title);
	}
	
	
	
}