package com.luxre.relux.banner.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxre.relux.banner.domain.Banner;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
	List<Banner> findAllByOrderByDisplayOrderAsc();
}
