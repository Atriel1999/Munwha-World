package com.multi.bbs.shop.model.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.bbs.board.model.vo.Board;
import com.multi.bbs.board.model.vo.BoardAttachFile;
import com.multi.bbs.shop.model.repository.CartRepository;
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
	@Autowired
	private CartRepository crepo;
	
	
	public List<Product> searchProductByTitle(String title, int limit, int offset) {
		return prepo.findByTitleContaining(title, limit, offset);
	}
	public List<Product> searchProductByTitleDesc(String title, int limit, int offset) {
		return prepo.findByTitleContainingDesc(title, limit, offset);
	}
	public int getproductcount(String title) {
		return prepo.countByTitleContaining(title);
	}
	
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteBoard(int bno, String rootPath) throws Exception {
//		Board board = this.findByNo(bno);
//		
//		if(board.getBoardAttachFileList() != null) {
//			for(BoardAttachFile file : board.getBoardAttachFileList()) {
//				deleteFile(rootPath, file);
//				this.deleteAttachFile(file);
//			}
//		}
//		boardRepo.deleteById(board.getBno());
//	}
//	
//	@Transactional(rollbackFor = Exception.class)
//	public void deleteReply(int no) {
//		boardReplyRepo.deleteById(no);
//	}
//	
	
	
}