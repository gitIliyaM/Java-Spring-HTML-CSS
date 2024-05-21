package com.wallet.Rest.enums;

public enum TemplatesText {
    TitleName("Title"),
    TitleValue("Wallet"),

    WarningName("warning"),
    WarningWrongNumber("Введите правильно число"),
    WarningInsufficientFunds("Недостаточно средств для снятия"),
    LoginIsBusy("Такой логин занят"),
    EmptyFieldValue("Заполните пустое поле"),
    AuthorizationError("Вы не зарегистрированы"),
    PasswordError("Не верный логин и/или пароль"),

    RegistrationNameTitle("registration"),
    RegistrationValueTitle("Registration"),
    AuthorizationNameTitle("authorization"),
    AuthorizationValueTitle("Authorization"),

    AccountNameTitle("accountTitle"),
    AccountValueTitle("Account"),
    AccountName("account"),

    AdminName("Title"),
    AdminValue("AdminPanel");

    public final String text;

    TemplatesText(String text) {
        this.text = text;
    }
}
