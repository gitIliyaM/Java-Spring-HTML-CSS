package com.wallet.Rest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class JV {
    private final static Map<String, String> hashMapLoginPass = new HashMap<>();

    public static void main(String[] args) throws IOException {
        setHashMapLoginPass("Log1", "Pass1");
        File file = new File("C:\\Users\\User\\Desktop\\HashMap\\HashMap.xls");
        file.createNewFile();

        //Path path = Path.of("C:\\Users\\User\\Desktop\\HashMap\\HashMap.xls");
        //Files.createFile(path);
    }
    public static void setHashMapLoginPass(String login, String password){
        File file = new File("C:\\Users\\User\\Desktop\\HashMap\\HashMap.xls");
        /*if (!hashMapLoginPass.containsKey(login)){
            hashMapLoginPass.put(login, password);
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                PrintWriter printWriter = new PrintWriter(bufferedWriter, true);
                printWriter.write("Логин="+login + " пароль="+password + "\n");
                printWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.ready()){
                String n = bufferedReader.readLine();
                int loginIndex = n.indexOf("Логин=");
                int passIndex = n.indexOf("пароль=");
                String loginStr = n.substring(loginIndex + 6, passIndex);
                String passStr = n.substring(passIndex + 7);
                //hashMapLoginPass.put(loginStr, passStr);
                System.out.println(loginStr + "=" + passStr);
            }
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    public static Map<String, String> getHashMapLoginPass(){
        return hashMapLoginPass;
    }

}
