package com.meru.promotions.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meru.promotions.dao.PromotionsDAO;
import com.meru.promotions.entity.Promotions;

@Service
@Transactional
public class PromotionsService {
	
	@Autowired
	private PromotionsDAO promotionsDAO;
	
	public Promotions createOffer(Promotions promotions) {
		return promotionsDAO.createOffer(promotions);
	}

}
