package com.multi.bbs.museum.model.vo;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "MuseumBookmark")
@Transactional
@DynamicInsert
@DynamicUpdate
public class MuseumBookmark {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bno;
	
	
	@ManyToOne
    private Museum museum;
	
	
	@Column
	private int msno;
	
	@Column
	private int mno;
	

}
