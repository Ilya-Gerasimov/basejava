package com.urise.webapp.model;

public enum ContactType {
    PHONE("Тел."),
    MOBIL("Моб."),
    E_MAIL("Почта"),
    SKYPE("Скайп"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STATCKOVERFLOW("Профиль Stackoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
