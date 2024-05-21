package com.wallet.Rest.converter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.Rest.walletEntity.WalletClient;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConverterObject {

    public String converter(Optional<WalletClient> wallet) throws JsonProcessingException {
        if(wallet.isPresent()){
            WalletClient walletClient = wallet.get();
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(walletClient).replace(",", ",\n").replace("{", "{\n").replace("}", "\n}");
        }
        return "Нужно зарегистрироваться";
    }

    public boolean verifyJson(WalletClient walletClient) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(walletClient);
        try {
            JSONObject o = new JSONObject(s);
            return true;
        } catch (JSONException e) {
            throw new RuntimeException("Не правильный формат JSON");
        }
    }
}
