package com.luxre.relux.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.luxre.relux.banner.domain.Banner;
import com.luxre.relux.banner.service.BannerService;
import com.luxre.relux.common.FileManager;

@Controller
@RequestMapping("/admin/banner")
public class BannerController {
	@Autowired
    private BannerService bannerService;

	@GetMapping("/list-view")
    public String bannerList(Model model) {
        model.addAttribute("banners", bannerService.getBanners());
        return "/admin/banner-list";
    }

    @PostMapping("/add")
    public String addBanner(@RequestParam("image") MultipartFile imageFile, 
                            @RequestParam("title") String title,
                            @RequestParam("linkurl") String linkUrl, 
                            @RequestParam("displayOrder") int displayOrder) {
        String imagePath = FileManager.saveFile(17, imageFile); 
        if (imagePath == null) {
            return "redirect:/admin/banner?error";
        }

        Banner banner = new Banner();
        banner.setImagePath(imagePath);
        banner.setTitle(title);
        banner.setLinkurl(linkUrl);
        banner.setDisplayOrder(displayOrder);
        bannerService.saveBanner(banner);

        return "redirect:/admin/banner/list-view";
    }
    
    @PostMapping("/delete")
    public String deleteBanner(@RequestParam("id") int id) {
        bannerService.deleteBanner(id);
        return "redirect:/admin/banner/list-view";
    }


}
