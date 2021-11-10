package com.example.wisherman.controller;

import com.example.wisherman.model.User;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;
import com.example.wisherman.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Controller
public class WishListController {

    private WishListRepository wishlistrepository;
    private UserService userService = new UserService();

    public WishListController() {
        wishlistrepository = new WishListRepository();
    }

    @GetMapping("/wishlist/show-all-wishlists")
    public String showAllWishlists(Model model)   {
        List<WishList> wishListList = wishlistrepository.getAllWishLists();
        model.addAttribute("wishListList", wishListList);
        return "show-all-wishlists";
    }

    @GetMapping("/wishlist/show-user-wishlists") //TODO Userpanel -
    public String showUserWishlists(HttpSession session, Model model, Model modelWL)   {
        //List<WishList> wishListList = wishlistrepository.getUserWishLists(((User)session.getAttribute("user")).getUserID());

        List<WishList> wishListList = wishlistrepository.getUserWishLists(userService.getUserSessionID(session));
        model.addAttribute("wishListList", wishListList);
        modelWL.addAttribute("wishlist", new WishList());
        return "show-user-wishlists";
    }

    @GetMapping("/wishlist/")
    public String wishListGet(){
        return "wishlist";
    }

    @GetMapping("/wishlist/new-wishlist")
    public String newWishListGet(Model model){
        model.addAttribute("wishlist", new WishList());
        return "new-wishlist";
    }

    @PostMapping("/wishlist/new-wishlist") // en form på show-user-wishlists.html
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
