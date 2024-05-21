package com.wallet.Rest.walletControllers.postMapping;

import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PostRegistration {
    private WalletServices walletServices;

    @Autowired
    public void setWalletServices(WalletServices walletServices) {this.walletServices = walletServices;}

    public boolean checkLogin(WalletClient walletClient){
        Iterable<WalletClient> walletClients = walletServices.getWallets();
        for(WalletClient wallet: walletClients){
            if(wallet.getLogin().equals(walletClient.getLogin())) {
                return true;
            }
        }
        return false;
    }
}
