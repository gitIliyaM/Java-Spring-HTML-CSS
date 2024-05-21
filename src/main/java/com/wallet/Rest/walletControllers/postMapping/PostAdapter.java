package com.wallet.Rest.walletControllers.postMapping;

import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostAdapter {
    private String page;
    private WalletServices walletServices;

    @Autowired
    public void setWalletServices(WalletServices walletServices) {
        this.walletServices = walletServices;
    }

    public String checkPage (String login, String password, String button){
        switch (button){
            case "create":
                if (checkNull(login, password)){
                    page = accessRegistration(login);
                } else {
                    page = "redirect:/regis";
                } break;
            case "enter":
                if (checkNull(login, password)){
                    page = accessAuthorization(login, password);
                } else {
                    page = "redirect:/auth";
                } break;
        }
        return page;
    }
    public Boolean checkNull(String login, String password){
        return !login.isEmpty() && !password.isEmpty();
    }

    public String accessRegistration(String login){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient: clientsList) {
            if (walletClient.getLogin().equals(login)) {
                return "redirect:/regis";
            }
        }
        return "redirect:/account";
    }

    public String accessAuthorization(String login, String password){
        List<WalletClient> clientsList = walletServices.getWallets();
        for (WalletClient walletClient : clientsList) {
            if (walletClient.getLogin().equals(login) && walletClient.getPassword().equals(password)) {
                return  "redirect:/account";
            } else if (walletClient.getLogin().equals(login)){
                return "redirect:/auth";
            }
        }
        return "redirect:/regis";
    }
}
