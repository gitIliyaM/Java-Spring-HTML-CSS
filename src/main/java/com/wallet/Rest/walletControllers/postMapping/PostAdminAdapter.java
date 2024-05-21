package com.wallet.Rest.walletControllers.postMapping;

import com.wallet.Rest.collections.CollectionsBase;
import com.wallet.Rest.walletEntity.WalletClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PostAdminAdapter {
    private String page;
    private CollectionsBase collectionsBase;

    @Autowired
    public void setMapLoginPass (CollectionsBase collectionsBase){
        this.collectionsBase = collectionsBase;
    }

    public String verifyAccess (String login, String password, String button){

        switch (button){
            case "html":
                if (checkNull(login, password)){
                    if (checkLoginPassMap(login)){
                        page = "redirect:/verify/admin";
                    } else {
                        collectionsBase.setHashMapLoginPass(login, password);
                        page = "redirect:/verify/admin";
                    }
                } else {
                    page = "redirect:/admin";
                } break;
            case "json":
                if (checkNull(login, password)){
                    //page = accessAuthorization(login, password);
                } else {
                    page = "redirect:/adminPanel/admin";
                } break;
        }
        return page;
    }
    public boolean checkNull(String login, String password){
        return !login.isEmpty() && !password.isEmpty();
    }
    public boolean checkLoginPassMap(String login){
        Map<String, String> map = collectionsBase.getHashMapLoginPass();
        return map.containsKey(login);
    }
    /*public Map<String, String> setLoginPass(){

    }*/
    /*public String accessRegistration(String login){
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
    }*/
}
