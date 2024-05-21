package com.wallet.Rest.walletControllers.getMapping;

import com.wallet.Rest.enums.TemplatesText;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class GetWarnings {
    public String checkWarningsAuth(String login, String password, String button, Model model) {
        model.addAttribute(TemplatesText.AuthorizationNameTitle.text, TemplatesText.AuthorizationValueTitle.text);
        if (login.equals("empty") || password.equals("empty") || button.equals("empty")) {
            return "authorization";
        } else if (checkEmpty(login, password)) {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.EmptyFieldValue.text);
        } else {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.PasswordError.text);
        }
        return "authorization";
    }

    public String checkWarningsRegis(String login, String password, String button, Model model) {
        model.addAttribute(TemplatesText.RegistrationNameTitle.text, TemplatesText.RegistrationValueTitle.text);
        if (login.equals("empty") || password.equals("empty") || button.equals("empty")) {
            return "registration";
        } else if (checkEmpty(login, password)) {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.EmptyFieldValue.text);
        } else if (button.equals("enter")){
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.AuthorizationError.text);
        } else {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.LoginIsBusy.text);
        }
        return "registration";
    }
    public String getAdminWarning(String login, String password, String button, Model model) {
        if (login.equals("empty") || password.equals("empty") || button.equals("empty")) {
            return "adminPanel/admin";
        } else if (checkEmpty(login, password)) {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.EmptyFieldValue.text);
        } else {
            model.addAttribute(TemplatesText.WarningName.text, TemplatesText.LoginIsBusy.text);
        }
        return "adminPanel/admin";
    }
    public boolean checkEmpty(String login, String password) {
        return login.isEmpty() || password.isEmpty();
    }
}