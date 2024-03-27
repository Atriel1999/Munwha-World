//package com.multi.bbs.shop.model.service;
//import java.io.IOException;
//import java.util.List;
//
//import javax.lang.model.util.Elements;
//import javax.swing.text.Document;
//import javax.swing.text.Element;
//
//import org.jsoup.Connection;
//import org.jsoup.Jsoup;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.multi.bbs.shop.model.repository.ShopRepository;
//import com.multi.bbs.shop.model.vo.Product;
//
//@Service
//public class ShopService {
//	 @Autowired
//	 private ShopRepository crawledDataRepository;
//
//	 public static List<Integer> getProductNoList(int page) throws IOException{
//
//			String URL = "https://khmall.or.kr/product/list.html?cate_no=90&page=" + page;
//			List<Integer> pnoList = new ArrayList<>();
//			
//			Connection conn = Jsoup.connect(URL);
//			Document doc = conn.get(); 
//			
//			System.out.println();
//			Elements lazys = doc.select(".name");
//			Elements add = doc.select(".name > a");
//			
//			for(int i = 0; i < lazys.size(); i++) {
//				System.out.println(lazys.get(i).text());
//				System.out.println(add.get(i).attr("href"));
//			}
//			
//			// 상품 번호
//			Elements els = doc.getElementsByClass("name");
//			String target = "&";
//	        for(int i = 0 ; i < els.size() ; i++) {
//	            Element el = els.get(i);
//	            String link = el.select("a").get(0).attr("href");
//	            String proNum = link.substring(link.indexOf("product_no=")+11);
//	            System.out.println("상품 번호 : " + proNum.substring(0, proNum.indexOf(target)));
//	            pnoList.add(Integer.parseInt(proNum.substring(0, proNum.indexOf(target))));
//	        }
//	        return pnoList;
//		}
//		
//		public static Product getProductInfoList(int pno) throws IOException{
//
//	        System.out.println("=========================================");
//	        System.out.println("상세 이미지");
//	        
//	        String URL = "https://khmall.or.kr/product/detail.html?product_no=" + pno + "&cate_no=90&display_group=1";
//	        System.out.println(URL);
//	        Connection conn = Jsoup.connect(URL);
//	        Document doc = conn.get();
//	        
//	        Elements metaTags = doc.getElementsByTag("meta");
//
//	        Product p = new Product();
//	        p.setPno(pno);
//	        for(int i =0; i< metaTags.size(); i++) {
//	        	if(metaTags.get(i).attr("property").contains("title")) {
//	        		p.setTitle(metaTags.get(i).attr("content"));
//	        	}
//	        	if(metaTags.get(i).attr("property").contains("description")) {
//	        		p.setDescription(metaTags.get(i).attr("content"));
//	        	}
//	        	if(metaTags.get(i).attr("property").contains("price:amount")) {
//	        		p.setPrice(metaTags.get(i).attr("content"));
//	        	}
//	        	if(metaTags.get(i).attr("property").contains("image")) {
//	        		p.setImage(metaTags.get(i).attr("content"));
//	        	}
//	        }
//	        
//	        // ThumbImage 가져오기
//	        Elements imageTags = doc.getElementsByClass("ThumbImage");
//	        List<String> imageList = new ArrayList<>();
//	        for(int i = 0; i < imageTags.size(); i++) {
//	        	imageList.add("https:" + imageTags.get(i).attr("src"));
//	        }
//	        p.setBigImageList(imageList);
//	        
//	        List<String> contentList = new ArrayList<>();
//	        // content 가져오기 
//	        try {
//	        	Element contentTag = doc.getElementsByClass("cont").get(0);
//	        	Elements imageTags2 = contentTag.getElementsByTag("img");
//	        	for(int i = 0; i < imageTags2.size(); i++) {
//	        		String url = "https:" + imageTags2.get(i).attr("ec-data-src");
//	        		contentList.add(url);
//	        	}
//	        	
//			} catch (Exception e) {}
//	        p.setContentImageList(contentList);
//	        
//	        return p;
//		}
//		
//		
//		public static void main(String[] args) throws Exception {
//			List<Integer> pnoList = new ArrayList<>();
//			
//			for(int i = 0 ; i < 3; i++) {
//				List<Integer> tempList = getProductNoList(i+1);
//				pnoList.addAll(tempList);
//			}
//			
//			System.out.println(pnoList);
//	        System.out.println("=================== pno 추출 완료 =====================");
//
//	        List<Product> pList = new ArrayList<>();
//	        for(int pno : pnoList) {
//	        	Product p = getProductInfoList(pno);
//	        	if(p != null) {
//	        		pList.add(p);
//	        		System.out.println(p);
//	        	}
//	        }
//	        
//	        System.out.println("=================== Product 추출 완료 =====================");
//	        for(Product p : pList) {
//	        	System.out.println(p);
//	        	System.out.println("ThumbImage");
//	        	for(String str : p.getBigImageList()) {
//	        		System.out.println(str);
//	        	}
//	        	System.out.println("content");
//	        	for(String str : p.getContentImageList()) {
//	        		System.out.println(str);
//	        	}
//	        }
//	        
//		}
//}