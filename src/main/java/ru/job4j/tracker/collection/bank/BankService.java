package ru.job4j.tracker.collection.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *Класс описывает модель банковской системы.
 * В системе можно производить следующие действия:
 * 1. Регистрировать пользователя.
 * 2. Удалять пользователя из системы.
 * 3. Добавлять пользователю банковский счет. У пользователя системы могут быть несколько счетов.
 * 4. Переводить деньги с одного банковского счета на другой счет.
 * @author  MAXIM TSURKANOV
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение данных осуществляется в коллекции типа HashMap
     */
    private HashMap<User, List<Account>> users = new HashMap<>();

    /**
     * Метод позволяет добавить нового пользователя в систему.
     * Если пользователь уже есть в системе, то добавление не производится.
     * @param user пользователь, которого будет добавляться в систему
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод позволяет выполнить удаление пользователя из системы
     * @param user пользователь, который будет удален
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Метод позволяет найти пользователя в системе по номеру паспорта
     * @param passport номер паспорта, по которому производится поиск пользователя
     * @return возвращает пользователя, у которого номер паспорта совпадает с запрошенным.
     * Если такого пользователя не нашлось, возвращается null.
     */
    public User findByPassport(String passport) {
        User rsl = null;
        for (User user
                : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return rsl;
    }

    /**
     * Метод позволяет добавить новый счет пользователю.
     * Если добавляемый счет уже есть в списке счетов пользователя, то добавление не производится.
     * @param passport номер паспорта, по которому производится поиск пользователя в системе
     * @param account номер счета, который будет добавлен к списку счетов пользователя.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null && !users.get(user).contains(account)) {
            users.get(user).add(account);
        }
    }

    /**
     * Метод позволяет выполнить поиск счета пользователя по запращиваемым реквизитам
     * @param passport номер паспорта, по которому производится поиск пользователя в системе
     * @param requisite запращиваемые реквизиты счета пользователя
     * @return возвращает счет пользователя, реквизиты которого совпадат с запращиваемыми и null
     * в противоположном случае
     */
    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = users.get(user);
            for (Account value
                    : userAccounts) {
                if (value.getRequisite().equals(requisite)) {
                    return value;
                }
            }
        }
        return rsl;
    }

    /**
     * Метод позволяет выполнить перечисление средств с одного счета на другой.
     * Транзакция будет выполнена, если пользователи с такими счетами найдены и на счету,
     * с которого оуществляется перечисление, достаточно денежных средств
     * @param srcPassport номер паспорта пользователя, который выполняет перевод средств
     * @param srcRequisite реквизиты счета, с которого быдет выполняться перечисление средств
     * @param destPassport номер паспорта пользователя, который будет получать переводимые средства
     * @param destRequisite реквизиты счета, на который будут зачисляться средства
     * @param amount количество переводимых средств в денежном эквиваленте
     * @return возвращае true, если транзакция прошла успено, и false в противоположном случае
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount != null && srcAccount.getBalance() >= amount && destAccount != null) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            return true;
        }
        return rsl;
    }
}
