package com.bobocode.web.controller;

import com.bobocode.TestDataGenerator;
import com.bobocode.model.Account;

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
public class AccountController {

}
