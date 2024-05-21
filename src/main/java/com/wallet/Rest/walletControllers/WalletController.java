package com.wallet.Rest.walletControllers;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletControllers.getMapping.GetHomePage;
import com.wallet.Rest.walletControllers.getMapping.GetWarnings;
import com.wallet.Rest.walletControllers.postMapping.PostAdapter;
import com.wallet.Rest.walletControllers.getMapping.GetAccess;
import com.wallet.Rest.walletControllers.postMapping.PostAdminAdapter;
import com.wallet.Rest.walletControllers.postMapping.PostCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class WalletController {

    private GetAccess getAccess;
    private GetHomePage getHomePage;
    private GetWarnings getWarnings;
    private PostCalculator postCalculate;
    private PostAdapter postAdapter;
    private String login = "empty", password = "empty", button = "empty", nameWarning = "empty", buttonAdmin = "empty",
            adminLogin = "empty", adminPass = "empty";
    private boolean accessAccount = false;
    private PostAdminAdapter postAdminAdapter;

    @Autowired
    public void setAccess (GetAccess getAccess){
        this.getAccess = getAccess;
    }
    @Autowired
    public void setHomePage(GetHomePage getHomePage) {
        this.getHomePage = getHomePage;
    }
    @Autowired
    public void setWarnings(GetWarnings getWarnings) {this.getWarnings = getWarnings; }
    @Autowired
    public void setAdapter(PostAdapter postAdapter) {
        this.postAdapter = postAdapter;
    }
    @Autowired
    public void setPostCalculate (PostCalculator postCalculate){
        this.postCalculate = postCalculate;
    }
    @Autowired
    public void setPostCalculate (PostAdminAdapter postAdminAdapter){
        this.postAdminAdapter = postAdminAdapter;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute(TemplatesText.TitleName.text, TemplatesText.TitleValue.text);
        this.button = getHomePage.setButton(this.button);
        return getHomePage.setHomePageButtons(this.accessAccount, model);
    }
    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute(TemplatesText.AdminName.text, TemplatesText.AdminValue.text);
        String page = getWarnings.getAdminWarning(this.adminLogin, this.adminPass, this.buttonAdmin, model);
        this.adminLogin = "empty";
        this.adminPass = "empty";
        this.buttonAdmin = "empty";
        return page;
    }
    @GetMapping("/verify")
    public String verifyAdmin(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String button) {
        this.adminLogin = login;
        this.adminPass = password;
        this.buttonAdmin = button;
        return postAdminAdapter.verifyAccess(login, password, buttonAdmin);
    }
    @GetMapping("/verify/admin")
    public String htmlAdmin(Model model) {
        model.addAttribute(TemplatesText.AdminName.text, TemplatesText.AdminValue.text);
        return "/adminPanel/htmlAdmin";
    }
    @GetMapping("/auth")
    public String getAuthPage(Model model) {
        String page = getWarnings.checkWarningsAuth(this.login, this.password, this.button, model);
        this.login = "empty";
        this.password = "empty";
        this.button = "empty";
        return page;
    }
    @GetMapping("/regis")
    public String getRegisPage(Model model) {
        String page = getWarnings.checkWarningsRegis(this.login, this.password, this.button, model);
        this.login = "empty";
        this.password = "empty";
        this.button = "empty";
        return page;
    }
    @GetMapping("/account")
    public String getAccountPage(Model model){
        this.accessAccount = true;
        return getAccess.setRegisAuthData(this.login, this.password, this.button, this.nameWarning, model);
    }
    @PostMapping("/adapter")
    public String postAccountPage(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String button) {
        this.login = login;
        this.password = password;
        this.button = button;
        return postAdapter.checkPage(login, password, button);
    }
    @PostMapping("/calculator")
    public String runCalculatePage(
            @RequestParam int amount,
            @RequestParam long UUID,
            @RequestParam String button){
        nameWarning = postCalculate.runCalculate(amount, UUID, button);
        return "redirect:/account";
    }
    @PostMapping("/exit")
    public String getHomePage(){
        this.accessAccount = false;
        this.button = "empty";
        return "redirect:/";
    }
}