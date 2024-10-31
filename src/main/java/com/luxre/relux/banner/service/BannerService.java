package com.luxre.relux.banner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxre.relux.banner.domain.Banner;
import com.luxre.relux.banner.repository.BannerRepository;

@Service
public class BannerService {
	 @Autowired
	    private BannerRepository bannerRepository;

	    public List<Banner> getBanners() {
	        return bannerRepository.findAllByOrderByDisplayOrderAsc();
	    }

	    public Banner saveBanner(Banner banner) {
	        return bannerRepository.save(banner);
	    }

	    public void deleteBanner(int id) {
	        bannerRepository.deleteById(id);
	    }
}
