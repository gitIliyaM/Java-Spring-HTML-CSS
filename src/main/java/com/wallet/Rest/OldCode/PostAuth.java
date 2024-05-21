package com.wallet.Rest.OldCode;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.List;

@Component
public class PostAuth {
    private final WalletServices walletServices;

    @Autowired
    public PostAuth(WalletServices walletServices) {
        this.walletServices = walletServices;
    }
    public String getAuthorization (String login, String password, Model model){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient : clientsList) {
            if (walletClient.getPassword().equals(password) && walletClient.getLogin().equals(login)) {
                model.addAttribute(TemplatesText.AccountName.text, walletClient);
                return "personal-account";
            } else {
                continue;
            }
        }
        model.addAttribute(TemplatesText.WarningName.text, TemplatesText.AuthorizationError.text);
        return "registration";
    }
    public Boolean checkNull (String login, String password){
        return !login.isEmpty() && !password.isEmpty();
    }
}
