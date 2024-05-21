package com.wallet.Rest.walletRepository;
import com.wallet.Rest.walletEntity.WalletClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletClient, Long> {
}
