package com.wallet.Rest.walletControllers;

import com.wallet.Rest.exception.RestControllerException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @PostMapping("/regisWallet")
    public ResponseEntity<?> testExtendsControllerAdvice(@RequestBody RestControllerException restControllerException) {
        return  ResponseEntity.ok(restControllerException);
    }
}
