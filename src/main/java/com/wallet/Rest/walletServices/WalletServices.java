package com.wallet.Rest.walletServices;

import com.wallet.Rest.walletEntity.WalletClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface WalletServices {

    WalletClient createWallets(WalletClient walletClient);

    List<WalletClient> getWallets();

    Optional<WalletClient> getWalletsByUUID(Long UUID);

    WalletClient updateWallets(Long UUID, WalletClient walletClient);

    String eventHandler(Long UUID, WalletClient walletClient);

    boolean checkAmount(int newWalletClient, int walletClient);

    void deleteWallets(Long UUID);

    boolean checkNumber(Integer amount);

    boolean ifExist(String login, String password);
}
