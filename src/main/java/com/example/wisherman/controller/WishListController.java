package com.example.wisherman.controller;

import com.example.wisherman.model.WishList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WishListController {

    @GetMapping("/wishlist/")
    public String wishListGet(){
        return "wishlist";
    }

    @GetMapping("/wishlist/new-wishlist")
    public String newWishListGet(Model model){
        model.addAttribute("wishlist", new WishList());
        return "new-wishlist";
    }

    @PostMapping("/wishlist/new-wishlist")
    public RedirectView newWishListPost(
            @ModelAttribute WishList wishList,
            RedirectAttributes redirectAttributes){
        System.out.println(wishList);
        if (wishList.isValidWishList(wishList)){
            redirectAttributes.addFlashAttribute("wishlist",wishList);
            return new RedirectView("/wishlist/new-wishlist-success", true);
        } else {
            return new RedirectView("/wishlist/new-wishlist", true);
        }
    }

     @GetMapping("/wishlist/new-wishlist-success")
    public String newWishListGetSuccess(HttpServletRequest request, Model model){
         Map<String,?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
         if (inputFlashMap != null){
             WishList wishList = (WishList) inputFlashMap.get("wishlist");
             model.addAttribute("wishlist",wishList);
             return "new-wishlist-success";
         } else {
             return "new-wishlist";
         }
     }
}
