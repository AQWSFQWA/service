package ru.itpark.service;

import ru.itpark.domain.Account;
import ru.itpark.reposototy.AccountRepository;

public class AccountService {
    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public void transfer(int senderId, int receplientId, int amount) {

        if (amount < 0) {
            return;
        }

        Account sender = repository.findById(senderId);
        if (sender == null) {
            return;
        }
        if (sender.getBalance()<amount) {
            return;
        }

        Account recipient = repository.findById(receplientId);
        if (recipient == null) {
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);

        repository.update(sender);
        repository.update(recipient);
    }
}
