package com.wallet.Rest.OldCode;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class PostControllerAccount {
    private final WalletServices walletServices;
    private String page;

    @Autowired
    public PostControllerAccount(WalletServices walletServices) {
        this.walletServices = walletServices;
    }

    public String checkRegisAuthData(String login, String password, String buttonCreateEnter ,Model model){
        switch (buttonCreateEnter){
            case "create":
                if (checkNull(login, password)){
                    model.addAttribute(TemplatesText.AccountNameTitle.text, TemplatesText.AccountValueTitle.text);
                    page = accessRegistration(login, password, model);
                } else {
                    model.addAttribute(TemplatesText.RegistrationNameTitle.text, TemplatesText.RegistrationValueTitle.text);
                    model.addAttribute(TemplatesText.WarningName.text, TemplatesText.EmptyFieldValue.text);
                    page = "registration";
                } break;
            case "enter":
                if (checkNull(login, password)){
                    model.addAttribute(TemplatesText.AccountNameTitle.text, TemplatesText.AccountValueTitle.text);
                    page = accessAuthorization(login, password, model);
                } else {
                    model.addAttribute(TemplatesText.AuthorizationNameTitle.text, TemplatesText.AuthorizationValueTitle.text);
                    model.addAttribute(TemplatesText.WarningName.text, TemplatesText.EmptyFieldValue.text);
                    page = "authorization";
                } break;
        }
        return page;
    }
    public Boolean checkNull(String login, String password){
        return !login.isEmpty() && !password.isEmpty();
    }

    public String accessRegistration(String login, String password, Model model){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient: clientsList) {
            if (walletClient.getLogin().equals(login)) {
                model.addAttribute(TemplatesText.RegistrationNameTitle.text, TemplatesText.RegistrationValueTitle.text);
                model.addAttribute(TemplatesText.WarningName.text, TemplatesText.LoginIsBusy.text);
                return "registration";
            } else {
                continue;
            }
        }

        WalletClient wallet = new WalletClient(login, password);
        WalletClient walletClient = walletServices.createWallets(wallet);
        model.addAttribute(TemplatesText.AccountName.text, walletClient);
        return "personal-account";
    }

    public String accessAuthorization(String login, String password, Model model){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient : clientsList) {
            if (walletClient.getLogin().equals(login) && walletClient.getPassword().equals(password)) {
                model.addAttribute(TemplatesText.AccountName.text, walletClient);
                return  "personal-account";
            } else if (walletClient.getLogin().equals(login)){
                model.addAttribute(TemplatesText.AuthorizationNameTitle.text, TemplatesText.AuthorizationValueTitle.text);
                model.addAttribute(TemplatesText.WarningName.text, TemplatesText.PasswordError.text);
                return "authorization";
            }
        }
        model.addAttribute(TemplatesText.RegistrationNameTitle.text, TemplatesText.RegistrationValueTitle.text);
        model.addAttribute(TemplatesText.WarningName.text, TemplatesText.AuthorizationError.text);
        return "registration";
    }
}
