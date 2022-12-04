package com.unitech.exception;

public class InValidSenderAccountException  extends RuntimeException {

    public static final String MESSAGE = "This account doesn't belong to sender.Aaccount number is: %s";

    public InValidSenderAccountException(Integer inValidAccount) {
        super(String.format(MESSAGE,inValidAccount));

    }
}