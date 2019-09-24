package com.bobocode.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Welcome controller that consists of one method that handles get request to "/" and "/welcome" and respond with a message.
 * <p>
 * todo: 1. Mark this class as Spring controller
 * todo: 2. Configure HTTP GET mapping "/" and "/welcome" for method {@link WelcomeController#welcome()}
 * todo: 3. Forward the request to "welcome.jsp" view
 */
@Controller
public class WelcomeController {

    @GetMapping({"/", "/welcome"})
    public String welcome() {
        return "welcome";
    }

}

