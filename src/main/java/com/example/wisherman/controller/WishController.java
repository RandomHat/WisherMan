package com.example.wisherman.controller;

import com.example.wisherman.model.Wish;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WishController {

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/wishlist/create-wish")
    public String createWishGet(Model model){
        model.addAttribute("wish", new Wish());
        return "create-wish";
    }

    @PostMapping("/wishlist/create-wish")
    public RedirectView createWishPost(
            @ModelAttribute("wish") Wish wish,
            RedirectAttributes redirectAttributes) {
        System.out.print(wish);
        System.out.println(wish.isValidWish(wish));
        if (wish.isValidWish(wish)) {
            redirectAttributes.addFlashAttribute("wish", wish);
            System.out.println("inside true");
            return new RedirectView("/wishlist/create-wish-success", true);
        } else {
            return new RedirectView("/wishlist/create-wish", true);
        }
    }

    @GetMapping("/wishlist/create-wish-success")
    public String createWishSuccess(HttpServletRequest request, Model model)   {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            Wish wish = (Wish) inputFlashMap.get("wish");
            model.addAttribute("wish", wish);
            return "create-wish-success";
        } else {
            return "create-wish";
        }
    }

}
