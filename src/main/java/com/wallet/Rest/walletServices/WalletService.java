package com.wallet.Rest.walletServices;

import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletRepository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService implements WalletServices {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {this.walletRepository = walletRepository;}

    @Override
    public WalletClient createWallets(WalletClient walletClient) {
        walletClient.setOperation("Create");
        WalletClient newWalletClient = walletRepository.saveAndFlush(walletClient);
        return newWalletClient;
    }

    @Override
    public List<WalletClient> getWallets(){
        List<WalletClient> walletClientList = walletRepository.findAll();
        return walletClientList;
    }

    @Override
    public Optional<WalletClient> getWalletsByUUID(Long UUID) {
        Optional<WalletClient> getWalletByUUID = walletRepository.findById(UUID);
        return getWalletByUUID;
    }

    @Override
    public String eventHandler(Long UUID, WalletClient walletClient) {
        Optional<WalletClient> existWallet = walletRepository.findById(UUID);
        String success = "Операция прошла успешно";
        if (existWallet.isPresent()) {
            WalletClient newWalletClient = existWallet.get();
            switch (walletClient.getOperation()){
                case "DEPOSIT"  :
                    try {
                        if (checkNumber(walletClient.getAmount())){
                            newWalletClient.setAmount(newWalletClient.getAmount() + walletClient.getAmount());
                            newWalletClient.setOperation("DEPOSIT");
                            break;
                        } else throw new RuntimeException("Введите правильно число");
                    } catch (RuntimeException e) {
                        return e.getMessage();
                    }
                case "WITHDRAW" :
                    try {
                        if (checkNumber(walletClient.getAmount()) && checkAmount(newWalletClient.getAmount(), walletClient.getAmount())){
                            newWalletClient.setAmount(newWalletClient.getAmount() - walletClient.getAmount());
                            newWalletClient.setOperation("WITHDRAW");
                            break;
                        } else if (!checkAmount(newWalletClient.getAmount(), walletClient.getAmount())){
                            throw new RuntimeException("Не достаточно средств на счету");
                        } else throw new RuntimeException("Введите правильно число");
                    } catch (RuntimeException e) {
                        return e.getMessage();
                    }
            }
            newWalletClient.setLogin(walletClient.getLogin());
            newWalletClient.setPassword(walletClient.getPassword());
            walletRepository.saveAndFlush(newWalletClient);
            return success;
        }
        return "Такой кошелек отсутствует";
    }

    @Override
    public WalletClient updateWallets(Long UUID, WalletClient walletClient) {
        Optional<WalletClient> existWallet = walletRepository.findById(UUID);
        if(existWallet.isPresent()){
            WalletClient newWalletClient = existWallet.get();
            newWalletClient.setAmount(walletClient.getAmount());
            newWalletClient.setLogin(walletClient.getLogin());
            newWalletClient.setPassword(walletClient.getPassword());
            return walletRepository.saveAndFlush(newWalletClient);
        } else {
            throw new RuntimeException("Wallet not exists");
        }
    }

    @Override
    public boolean checkAmount(int newWalletClient, int walletClient) {
        return newWalletClient > walletClient;
    }

    @Override
    public boolean checkNumber(Integer amount) {
        return amount != null && amount != 0 && amount >= 0;
    }

    @Override
    public void deleteWallets(Long UUID) {
        Optional<WalletClient> existWallet = walletRepository.findById(UUID);
        if(existWallet.isPresent()){
            walletRepository.deleteById(UUID);
        } else {
            throw new RuntimeException("Wallet not exists");
        }
    }

    @Override
    public boolean ifExist(String login, String password) {
        return false;
    }
}
