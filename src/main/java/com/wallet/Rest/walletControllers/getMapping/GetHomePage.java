package com.wallet.Rest.walletControllers.getMapping;

import com.wallet.Rest.enums.TemplatesButton;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class GetHomePage {

    public String setHomePageButtons(boolean accessAccount, Model model){
        if (accessAccount){
            model.addAttribute(TemplatesButton.ButtonAccess.hrefName, TemplatesButton.ButtonAccess.hrefValue);
            model.addAttribute(TemplatesButton.ButtonAccess.textName, TemplatesButton.ButtonAccess.textValue);
            model.addAttribute(TemplatesButton.ButtonAccess.cssName, TemplatesButton.ButtonAccess.cssValue);
        } else {
            model.addAttribute(TemplatesButton.ButtonAuthorization.hrefName, TemplatesButton.ButtonAuthorization.hrefValue);
            model.addAttribute(TemplatesButton.ButtonAuthorization.textName, TemplatesButton.ButtonAuthorization.textValue);
            model.addAttribute(TemplatesButton.ButtonAuthorization.cssName, TemplatesButton.ButtonAuthorization.cssValue);
            model.addAttribute(TemplatesButton.ButtonRegistration.hrefName, TemplatesButton.ButtonRegistration.hrefValue);
            model.addAttribute(TemplatesButton.ButtonRegistration.textName, TemplatesButton.ButtonRegistration.textValue);
            model.addAttribute(TemplatesButton.ButtonRegistration.cssName, TemplatesButton.ButtonRegistration.cssValue);
        }
        return "home";
    }

    public String setButton(String button) {
        if (button.equals("create") || button.equals("enter")) {
            return "enter";
        }
        return "empty";
    }
}
