package com.linc.lil.learningspring.web;

import com.linc.lil.learningspring.business.GuestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAllGuests(Model model) {
        model.addAttribute("guests", this.guestService.getAllGuests());
        return "hotel-guest";
    }
}
