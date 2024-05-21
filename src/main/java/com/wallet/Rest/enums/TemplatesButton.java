package com.wallet.Rest.enums;

public enum TemplatesButton {
    ButtonAccess("access", "/account", "accessText", "Личный кабинет", "accessCSS", "btn btn-primary my-2"),
    ButtonAuthorization("auth", "/auth", "authText", "Авторизация", "authCSS", "btn btn-primary my-2"),
    ButtonRegistration("regis", "/regis", "regisText", "Регистрация", "regisCSS", "btn btn-secondary my-2");

    public final String hrefName;
    public final String hrefValue;
    public final String textName;
    public final String textValue;
    public final String cssName;
    public final String cssValue;

    TemplatesButton(String hrefName, String hrefValue, String textName, String textValue, String cssName, String cssValue ){
        this.hrefName = hrefName;
        this.hrefValue = hrefValue;
        this.textName = textName;
        this.textValue = textValue;
        this.cssName = cssName;
        this.cssValue = cssValue;
    }
}
