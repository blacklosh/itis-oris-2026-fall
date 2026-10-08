package ru.itis.servlet;

import ru.itis.servlet.service.impl.PasswordEncoderHashImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println(new PasswordEncoderHashImpl().getHash("ytrewq"));
    }
}
