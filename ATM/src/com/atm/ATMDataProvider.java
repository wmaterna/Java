package com.atm;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ATMDataProvider {
    class UserData {
         int pin;
         int money;

        public UserData(int pin, int money) {
            this.pin = pin;
            this.money = money;
        }

        @Override
        public String toString() {
            return "UserData{" +
                    "pin=" + pin +
                    ", money=" + money +
                    '}';
        }
    }

    public  class IdNotFoundException extends Exception {
}
   public  class InvalidPinException extends Exception {
}
    private Map<Integer, UserData> userDataMap = new LinkedHashMap<>();

    public void init() throws IOException {
        List<String> dataLines = Files.readAllLines(Paths.get("data"));
        for (String dataLine: dataLines) {
            String[] tab = dataLine.split(":");
            String userId = tab[0];
            int pin = Integer.parseInt(tab[1]);
            int money = Integer.parseInt(tab[2]);
            UserData userData = new UserData(pin, money);
            userDataMap.put(Integer.parseInt(userId), userData);

        }
        System.out.println(userDataMap);
        System.out.println(dataLines);
     }

    public int getMoney(int id, int pin) throws IdNotFoundException, InvalidPinException {
        UserData userData = userDataMap.get(id);
        if (userData == null) {
            throw  new IdNotFoundException();
        }
        if (userData.pin != pin) {
            throw new InvalidPinException();
            }

        return userData.money;
    }

    public void setMoney(int id, int pin, int money) throws IdNotFoundException, InvalidPinException, FileNotFoundException {
        UserData userData = userDataMap.get(id);
        if (userData == null) {
            throw  new IdNotFoundException();
        }
        if (userData.pin != pin) {
            throw new InvalidPinException();
            }
        userData.money = money;
        writeUserDataToFile();
    }

    void writeUserDataToFile() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("data", false);
        PrintWriter pr = new PrintWriter(fos);
        Set<Map.Entry<Integer, UserData>> entries = userDataMap.entrySet();
        for (Map.Entry<Integer, UserData> entry : entries) {
            int id = entry.getKey();
            int pin = entry.getValue().pin;
            int money = entry.getValue().money;
            pr.println(id + ":" + pin + ":" + money);
        }
        pr.close();
    }

}
