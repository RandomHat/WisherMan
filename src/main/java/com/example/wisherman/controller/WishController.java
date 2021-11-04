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

    @GetMapping("/wishlist/new-wish")
    public String createWishGet(Model model){
        model.addAttribute("wish", new Wish());
        return "new-wish";
    }

    @PostMapping("/wishlist/new-wish")
    public RedirectView createWishPost(
            @ModelAttribute("wish") Wish wish,
            RedirectAttributes redirectAttributes) {
        System.out.print(wish);
        System.out.println(wish.isValidWish(wish));
        if (wish.isValidWish(wish)) {
            redirectAttributes.addFlashAttribute("wish", wish);
            System.out.println("inside true");
            return new RedirectView("/wishlist/new-wish-success", true);
        } else {
            return new RedirectView("/wishlist/new-wish", true);
        }
    }

    @GetMapping("/wishlist/new-wish-success")
    public String createWishSuccess(HttpServletRequest request, Model model)   {
        Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap != null) {
            Wish wish = (Wish) inputFlashMap.get("wish");
            model.addAttribute("wish", wish);
            return "new-wish-success";
        } else {
            return "new-wish";
        }
    }

}
