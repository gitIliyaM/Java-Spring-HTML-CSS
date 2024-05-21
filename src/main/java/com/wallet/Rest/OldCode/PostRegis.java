package com.wallet.Rest.OldCode;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import java.util.List;

@Component
public class PostRegis {
    private final WalletServices walletServices;

    @Autowired
    public PostRegis(WalletServices walletServices) {
        this.walletServices = walletServices;
    }
    public String getRegistration(String login, String password, Model model){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient: clientsList) {
            if (walletClient.getLogin().equals(login)) {
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

    public Boolean checkNull(String login, String password){
        return !login.isEmpty() && !password.isEmpty();
    }
}
