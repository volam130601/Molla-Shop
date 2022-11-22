package com.molla.util;

class AesEncryptionTest {
    public static void main(String[] args) {
        AesEncryption aesEncryption = new AesEncryption();
        String str = "Hello";
        String encrypt = aesEncryption.encrypt(str, "hi");
        String decrypt = aesEncryption.decrypt(encrypt, "hi");
        System.out.println(encrypt + " \n" + decrypt);
    }
}