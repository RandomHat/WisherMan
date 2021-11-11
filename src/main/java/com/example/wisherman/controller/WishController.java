package com.example.wisherman.controller;

import com.example.wisherman.model.Wish;
import com.example.wisherman.model.WishList;
import com.example.wisherman.repositories.WishRepository;
import com.example.wisherman.service.UserService;
import com.example.wisherman.service.WishListService;
import com.example.wisherman.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class WishController {

    private WishService wishService = new WishService();
    private WishListService wishListService = new WishListService();
    private UserService userService = new UserService();

    public WishController() {
    }


    @GetMapping("/user-panel/wishlist")
    public String createWishGet(HttpSession session, Model model, @RequestParam int listId) {

        WishList wishlist = wishListService.getWishList(listId);
        session.setAttribute("wishlist",wishlist);

        if ( wishlist.getUserid() == userService.getUserSessionID(session)) {
            model.addAttribute("wish", new Wish());
            //int listID = Integer.parseInt(listId);
            List<Wish> listOfWishes = wishService.getWishListWishes(listId);
            //wishrepository.getUserWishes(userService.getUserSessionID(session));
            model.addAttribute("listOfWishes", listOfWishes);
            return "Account/show-user-wishes";
        } else{

        return "redirect:/";}
    }


    @PostMapping("/user-panel/wishlist")
    public RedirectView createWishPost(
            @ModelAttribute("wish") Wish wish,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        wish.setWishListID(((WishList) session.getAttribute("wishlist")).getIdwishlist());
        System.out.print(wish);
        System.out.println(wish.isValidWish(wish));

        if (wish.isValidWish(wish)) {

            wishService.addWishToDB(wish); // adding wish to database
            //wishrepository.addWishToWishList(wish);
                redirectAttributes.addFlashAttribute("wish", wish);
                System.out.println("inside true. " + "Added wish: " + wish); //debugging. print added wish
                return new RedirectView("/user-panel/new-wish-success", true);

        } else {
            return new RedirectView("/user-panel/wishlist", true);
        }
    }


    @GetMapping("/user-panel/new-wish-success") // return til user-panel/wishlist i HTML.
    public String createWishSuccess(HttpServletRequest request, Model model)   {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            Wish wish = (Wish) inputFlashMap.get("wish");
            model.addAttribute("wish", wish);
            return "Account/new-wish-success";
        } else {
            return "Account/show-user-wishes";
        }
    }


    @GetMapping("/wishlist/show-all-wishes")
    public String showAllWishes(Model model) {
        List<Wish> listOfWishes = wishService.getAllWishes();
        model.addAttribute("listOfWishes", listOfWishes);
        return "show-all-wishes";
    }


    @GetMapping("/user/wishlist")
    public String shareWishlist(@RequestParam (defaultValue = "0") String listId, Model model ){
        int wishlistId = Integer.parseInt(listId);

        if (wishlistId <= 0){
            return "redirect:/wishlist/show-all-wishlists";
        } else{
            List<Wish> wishList = wishService.getWishListWishes(wishlistId);
            model.addAttribute("wishlistWishes",wishList);
            return "Shareables/share-wishlist-wishes";
        }
    }
/*
    @GetMapping("/wishlist/show-user-wishes")
    public String showUserWishes(HttpSession session, Model model) {
        //((User)session.getAttribute("user")).getUserID()
        List<Wish> listOfWishes = wishrepository.getUserWishes(userService.getUserSessionID(session));
        model.addAttribute("listOfWishes", listOfWishes);
        return "show-user-wishes";
    }

 */
}
