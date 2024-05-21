package com.wallet.Rest.walletControllers.postMapping;

import com.wallet.Rest.enums.TemplatesText;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletRepository.WalletRepository;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PostCalculator {
    private WalletServices walletServices;
    private WalletRepository walletRepository;
    String nameWarning = "empty";

    @Autowired
    public void calculateServices(WalletServices walletServices) {
        this.walletServices = walletServices;
    }

    @Autowired
    public void calculateRepository(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public String runCalculate(int amount, long UUID, String button){
        Optional<WalletClient> walletClientOptional = walletServices.getWalletsByUUID(UUID);
        switch (button){
            case "plus":
                if (checkNumber(amount) && walletClientOptional.isPresent()){
                    WalletClient walletClient = walletClientOptional.get();
                    walletClient.setAmount(walletClient.getAmount() + amount);
                    walletRepository.saveAndFlush(walletClient);
                } else {
                    nameWarning = TemplatesText.WarningWrongNumber.text;
                } break;
            case "minus":
                if (checkNumber(amount) && walletClientOptional.isPresent()){
                    WalletClient walletClient = walletClientOptional.get();
                    if(walletClient.getAmount() > amount){
                        walletClient.setAmount(walletClient.getAmount() - amount);
                        walletRepository.saveAndFlush(walletClient);
                    } else {
                        nameWarning = TemplatesText.WarningInsufficientFunds.text;
                    } break;
                } else {
                    nameWarning = TemplatesText.WarningWrongNumber.text;
                } break;
        }
        return nameWarning;
    }
    public boolean checkNumber(Integer amount){return amount != null && amount != 0 && amount >= 0;}
}
