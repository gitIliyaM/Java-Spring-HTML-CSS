package com.wallet.Rest.walletControllers;

import com.wallet.Rest.converter.ConverterObject;
import com.wallet.Rest.json.VerifyJson;
import com.wallet.Rest.walletControllers.postMapping.PostRegistration;
import com.wallet.Rest.walletEntity.WalletClient;
import com.wallet.Rest.walletServices.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class WalletRestController {
    private WalletServices walletServices;
    private ConverterObject converterObject;
    private PostRegistration postRegistration;

    @Autowired
    public void walletRestController(WalletServices walletServices) {
        this.walletServices = walletServices;
    }
    @Autowired
    public void setConverterObject(ConverterObject converterObject) {this.converterObject = converterObject;}
    @Autowired
    public void setPostRegis(PostRegistration postRegistration) {this.postRegistration = postRegistration;}

    @PostMapping("/regisWallet")
    public ResponseEntity<?> createWalletClient(@RequestBody WalletClient walletClient) {
        if (postRegistration.checkLogin(walletClient)){
            return ResponseEntity.ok("Такой логин занят");
        }
        WalletClient newWalletClient = walletServices.createWallets(walletClient);
        return ResponseEntity.ok(newWalletClient);
    }

    @GetMapping("/getWallets")
    public List<WalletClient> listWalletClient(){
        List<WalletClient> listsWalletClient = walletServices.getWallets();
        return listsWalletClient;
    }

    @GetMapping("/getWallet/{UUID}")
    public ResponseEntity<?> uuidWalletClient(@PathVariable Long UUID) {
        Optional<WalletClient> walletClientUuid = walletServices.getWalletsByUUID(UUID);
        if (walletClientUuid.isPresent()) {
            return ResponseEntity.ok(walletClientUuid);
        }
        return ResponseEntity.ok("Такого кошелька не существует");
    }

    @PutMapping("/putWallet/{UUID}")
    public ResponseEntity<String> updateWalletClient(@PathVariable Long UUID, @RequestBody WalletClient walletClient) throws IOException {
        String eventHandler = walletServices.eventHandler(UUID, walletClient);
        Optional<WalletClient> wallet = walletServices.getWalletsByUUID(UUID);
        return ResponseEntity.ok(eventHandler + "\n" + converterObject.converter(wallet));
    }

    @DeleteMapping("/deleteWallet/{UUID}")
    public ResponseEntity<String> deleteWalletClient(@PathVariable Long UUID) {
        Optional<WalletClient> walletClientUuid = walletServices.getWalletsByUUID(UUID);
        if (walletClientUuid.isPresent()) {
            walletServices.deleteWallets(UUID);
            return ResponseEntity.ok("Кошелек удален");
        }
        return ResponseEntity.ok("Такого кошелька не существует");
    }
}