package com.wallet.Rest.walletControllers.getMapping;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;

@Component
public class GetAccess {
    private final WalletServices walletServices;
    private String page;

    @Autowired
    public GetAccess(WalletServices walletServices) {this.walletServices = walletServices;}

    public String setRegisAuthData(String login, String password, String buttonCreateEnter, String nameWarning, Model model) {
            switch (buttonCreateEnter) {
                case "create":
                    model.addAttribute(TemplatesText.AccountNameTitle.text, TemplatesText.AccountValueTitle.text);
                    page = accessRegistration(login, password, model);
                    break;
                case "enter":
                    page = accessAuthorization(login, password, nameWarning, model);
                    break;
            }
        return page;
    }

    public String accessRegistration(String login, String password, Model model) {
        WalletClient wallet = new WalletClient(login, password);
        WalletClient walletClient = walletServices.createWallets(wallet);
        model.addAttribute(TemplatesText.AccountName.text, walletClient);
        return "personal-account";
    }
    public String accessAuthorization(String login, String password, String nameWarning, Model model){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient : clientsList) {
            if (walletClient.getLogin().equals(login) && walletClient.getPassword().equals(password)) {
                model.addAttribute(TemplatesText.AccountNameTitle.text, TemplatesText.AccountValueTitle.text);
                model.addAttribute(TemplatesText.WarningName.text, checkNameWarning(nameWarning));
                model.addAttribute(TemplatesText.AccountName.text, walletClient);
                return "personal-account";
            }
        }
        model.addAttribute(TemplatesText.AuthorizationNameTitle.text, TemplatesText.AuthorizationValueTitle.text);
        return "authorization";
    }
    public String checkNameWarning(String nameWarning) {
        if (nameWarning.equals("empty")){
            return "";
        }
        return nameWarning;
    }
}
