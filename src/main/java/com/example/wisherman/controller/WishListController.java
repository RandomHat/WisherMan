package com.example.wisherman.controller;

import com.example.wisherman.model.User;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishListRepository;
import com.example.wisherman.service.UserService;
import com.example.wisherman.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class WishListController {


    private WishListRepository wishlistrepository = new WishListRepository();
    private UserService userService = new UserService();
    private WishListService wishListService = new WishListService();


    @GetMapping("/show-all-wishlists")
    public String showAllWishlists(Model model)   {
        List<WishList> wishListList = wishlistrepository.getAllWishLists(); // Wishlist service
        model.addAttribute("wishListList", wishListList);
        return "Shareables/show-all-wishlists";
    }

    @GetMapping("/user-panel") //TODO Userpanel -
    public String showUserWishlists(HttpSession session, Model model, Model modelWL)   {
        //List<WishList> wishListList = wishlistrepository.getUserWishLists(((User)session.getAttribute("user")).getUserID());
        if(userService.getUserSessionID(session) < 0){
            return "redirect:/";
        }
        List<WishList> wishListList = wishlistrepository.getUserWishLists(userService.getUserSessionID(session));
        model.addAttribute("wishListList", wishListList);
        modelWL.addAttribute("wishlist", new WishList());
        return "Account/show-user-wishlists";
    }

    @PostMapping("/user-panel")
    public String selectedWishList(HttpSession session, @ModelAttribute WishList wishlist){
        session.setAttribute("wishlist",wishlist);
        System.out.println(wishlist.toString());

        return "redirect:/user-panel/wishlist";
    }

    @GetMapping("/user-panel/new-wishlist")
    public String newWishListGet(Model model){
        model.addAttribute("wishlist", new WishList());
        return "Account/new-wishlist";
    }

    @PostMapping("/user-panel/new-wishlist") // en form på show-user-wishlists.html
    public RedirectView newWishListPost(
            @ModelAttribute WishList wishList,
            RedirectAttributes redirectAttributes,
            HttpSession session){
        System.out.println(wishList);
        if (wishList.isValidWishList(wishList)){
            redirectAttributes.addFlashAttribute("wishlist",wishList);
            wishList.setUserid(((User)session.getAttribute("user")).getUserID());
            wishListService.addWishListToDB(wishList);
            return new RedirectView("/user-panel/new-wishlist-success", true);
        } else {
            return new RedirectView("/user-panel", true);
        }
    }

     @GetMapping("/user-panel/new-wishlist-success") //link i html til wishlist.
    public String newWishListGetSuccess(HttpServletRequest request, Model model){
         Map<String,?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
         if (inputFlashMap != null){
             WishList wishList = (WishList) inputFlashMap.get("wishlist");
             model.addAttribute("wishlist",wishList);
             return "Account/new-wishlist-success";
         } else {
             return "Account/show-user-wishlists";
         }
     }

    @GetMapping("/user")
    public String shareWishlists(@RequestParam (defaultValue = "0") String iD, Model model) {
        int userId = Integer.parseInt(iD);
        if (userId == 0) {
            return "redirect:/show-all-wishlists";
        } else {
            List<WishList> wishListList = wishlistrepository.getUserWishLists(userId); // ændres til service
            model.addAttribute("wishListList", wishListList);
            return "Shareables/share-user-wishlists";
        }
    }
}
