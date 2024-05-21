package com.wallet.Rest.collections;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CollectionsBase {
    private final Map<String, String> hashMapLoginPass = new HashMap<>();

    public void setHashMapLoginPass(String login, String password){
        hashMapLoginPass.put(login,password);
    }
    public Map<String, String> getHashMapLoginPass(){
        return hashMapLoginPass;
    }
}
