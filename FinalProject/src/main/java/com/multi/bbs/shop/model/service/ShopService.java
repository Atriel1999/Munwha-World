package com.multi.bbs.shop.model.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.bbs.board.model.vo.Board;
import com.multi.bbs.board.model.vo.BoardAttachFile;
import com.multi.bbs.heritage.model.vo.Himage;
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