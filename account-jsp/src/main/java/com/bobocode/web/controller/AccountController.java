package com.bobocode.web.controller;

import com.bobocode.TestDataGenerator;
import com.bobocode.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * This controller provides endpoint that generates a list of {@link Account} and passes it to the view.
 * <p>
 * todo: 1. Configure controller that handles requests with url "/accounts"
 * todo: 2. Inject a {@link TestDataGenerator}
 * todo: 3. Implement a method that handles GET request with param "size" to url "/accounts" and forwards it to the accounts.jsp view
 * todo: 4. In this method generate a list of account using data generator and received int value "size"
 * todo: 5. Provide a default value "10" for parameter "size"
 * todo: 6. Pass the list of accounts to the view using model attribute with name "accountList"
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {
    private final TestDataGenerator dataGenerator;

    @Autowired
    public AccountController(TestDataGenerator dataGenerator) {
        this.dataGenerator = dataGenerator;
    }

    @GetMapping()
    public String generateAccounts(@RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
        List<Account> accountList = Stream.generate(dataGenerator::generateAccount)
                .limit(size)
                .collect(toList());
        model.addAttribute("accountList", accountList);
        return "accounts";
    }
}
