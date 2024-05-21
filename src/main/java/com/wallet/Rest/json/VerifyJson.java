package com.wallet.Rest.json;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.wallet.Rest.walletEntity.WalletClient;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class VerifyJson {

    public boolean checkJsonFormat(WalletClient walletClient){
        String json =
                "{"+
                "login:" + ","+
                "password:" + "," +
                "amount:" +
                "}";
        try {
            JsonParser.parseString(walletClient.toString());
        } catch (JsonSyntaxException e) {
            return true;
        }
        return false;
    }
}
