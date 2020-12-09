package com.atm;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ATMs extends JFrame {
    private JPanel mainPanel;
    private JButton select2;
    private JButton select4;
    private JButton btn9;
    private JButton btn6;
    private JButton btn8;
    private JButton btn5;
    private JButton btn2;
    private JButton btn7;
    private JButton btn4;
    private JButton btn1;
    private JButton btn3;
    private JButton select3;
    private JButton select1;
    private JTextField textField1;
    private JButton btnAccept;
    private JButton btnclear;
    private JTextPane userMessage;
    private JButton btn0;
    private JTextField textField2;
    private ATMDataProvider atmDataProvider;

    private int id = 0;
    private  int pin = 0;


    class ATMButtonsAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectButtonId = Integer.parseInt(e.getActionCommand());
            switch (currentState) {
                case LogIn :
                    if (selectButtonId == 5) {
                         id = Integer.parseInt(textField1.getText());
                        textField1.setText("");
                        changeState(ATMState.Pin);
                    }
                    break;
                case Pin:
                    if (selectButtonId == 5) {
                         pin = Integer.parseInt(textField1.getText());
                        textField1.setText("");
                        changeState(ATMState.Pin);
                        try {
                            atmDataProvider.getMoney(id, pin);
                        } catch (ATMDataProvider.IdNotFoundException ex) {
                            System.out.println("invalid id");
                            changeState(ATMState.LogIn);
                            break;
                        } catch (ATMDataProvider.InvalidPinException ex) {
                            System.out.println("invalid pin");
                            changeState(ATMState.LogIn);
                            break;
                        }

                        changeState(ATMState.SelectAction);
                    }
                    break;
                case SelectAction:
                    if (selectButtonId == 2) {
                        changeState(ATMState.GetMoney);
                    } else if (selectButtonId == 4) {
                        changeState(ATMState.PutMoney);
                    } else if(selectButtonId==1)
                    {
                        changeState(ATMState.SeeAccountBalance);
                    } if (selectButtonId == 3){
                    changeState(ATMState.Bye);
                }
                    break;
                case GetMoney:
                    if (selectButtonId == 4) {
                        try {
                            int currentMoney = atmDataProvider.getMoney(id, pin);
                            int amount = Integer.parseInt(textField1.getText());
                            if(currentMoney-amount<0) {
                                userMessage.setText("Insufficient funding, enter different amount");
                                textField1.setText("");
                                break;
                            }

                            atmDataProvider.setMoney(id, pin, currentMoney - amount);
                            System.out.println("money disposed");
                            textField1.setText("");
                            changeState(ATMState.SelectAction);
                        } catch (ATMDataProvider.IdNotFoundException | ATMDataProvider.InvalidPinException | FileNotFoundException ex) {
                            throw new IllegalStateException("GetMoney - something is wrong..");
                        }
                    }
                    if (selectButtonId == 3){
                        changeState(ATMState.Bye);
                    }
                    break;

                case PutMoney:
                    if (selectButtonId == 4) {
                        try {
                            int currentMoney = atmDataProvider.getMoney(id, pin);
                            int amount = Integer.parseInt(textField1.getText());
                            atmDataProvider.setMoney(id, pin, currentMoney + amount);
                            System.out.println("money added");
                            textField1.setText("");
                            changeState(ATMState.SelectAction);
                        } catch (ATMDataProvider.IdNotFoundException | ATMDataProvider.InvalidPinException | FileNotFoundException ex) {
                            throw new IllegalStateException("PutMoney - something is wrong..");
                        }
                    }
                    if (selectButtonId == 3){
                        changeState(ATMState.Bye);
                    }
                    break;
                case SeeAccountBalance:
                    try {
                        if(selectButtonId==2)
                        {
                            int currentMoney = atmDataProvider.getMoney(id, pin);
                            textField1.setText("Your Account Balance: " + currentMoney);

                        }
                        if(selectButtonId==4)
                        {
                            textField1.setText("");
                            changeState(ATMState.SelectAction);
                        }
                    } catch (ATMDataProvider.IdNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (ATMDataProvider.InvalidPinException ex) {
                        ex.printStackTrace();
                    }
                    if (selectButtonId == 3){
                        changeState(ATMState.Bye);
                        textField1.setText("");
                    }
                    break;
                case Bye:
                    if (selectButtonId == 3) {
                        textField1.setText("");
                        changeState(ATMState.LogIn);

                    }
                    break;
            }

        }


    }
    private void changeState(ATMState newState) {
                    currentState = newState ;
        switch (newState) {
            case LogIn:
                userMessage.setText("Please enter your ID number");
                select1.setText("-");
                select2.setText("-");
                select3.setText("-");
                select4.setText("-");
                btnAccept.setText("Enter");
                break;
             case Pin:
                userMessage.setText("Please enter your Pin Code");
                select1.setText("-");
                select2.setText("-");
                select3.setText("-");
                select4.setText("-");
                btnAccept.setText("Enter");
              break;

            case SelectAction:
                userMessage.setText("Please select Action ");
                select1.setText("Account Info ");
                select2.setText("Get money ");
                select3.setText("End");
                select4.setText("Put money ");
                btnAccept.setText("Enter");
                break;
            case GetMoney:
                userMessage.setText("Please enter Money Amount");
                select1.setText("-");
                select2.setText("-");
                select3.setText("End");
                select4.setText("OK");
                btnAccept.setText("Enter");
                break;
            case PutMoney:
                userMessage.setText("Please enter Money Amount");
                select1.setText("-");
                select2.setText("-");
                select3.setText("End");
                select4.setText("OK");
                btnAccept.setText("Enter");
                break;
            case SeeAccountBalance:
                userMessage.setText("Get Info");
                select1.setText("-");
                select2.setText("See Account Balance");
                select3.setText("End");
                select4.setText("Select Different Acction\n");
                btnAccept.setText("Enter");
                break;

            case Bye:
                userMessage.setText("Thank you for using our ATM");
                select1.setText("-");
                select2.setText("-");
                select3.setText("Log In");
                select4.setText("-");
                btnAccept.setText("Enter");
                break;
        }

    }

    private ATMButtonsAction buttonAction = new ATMButtonsAction();
     ATMState currentState = ATMState.LogIn;
    public void init() throws IOException {
        atmDataProvider = new ATMDataProvider();
                    atmDataProvider.init();
                    }

    public ATMs(String title)
    {


        super (title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        select1.setAction(buttonAction);
        select2.setAction(buttonAction);
        select3.setAction(buttonAction);
        select4.setAction(buttonAction);
        btnAccept.setAction(buttonAction);
        btnAccept.setActionCommand("5");
        select1.setActionCommand("1");
        select2.setActionCommand("2");
        select3.setActionCommand("3");
        select4.setActionCommand("4");




         btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonOneText=textField1.getText()+btn1.getText(); //to display 11
                textField1.setText(buttonOneText);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonDwoText=textField1.getText()+ btn2.getText();
                textField1.setText(buttonDwoText);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonThreeText=textField1.getText()+btn3.getText();
                textField1.setText(buttonThreeText);
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonFourText=textField1.getText()+btn4.getText();
                textField1.setText(buttonFourText);
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonFiveText=textField1.getText()+btn5.getText();
                textField1.setText(buttonFiveText);
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonSixText=textField1.getText()+btn6.getText();
                textField1.setText(buttonSixText);
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonSevenText=textField1.getText()+btn7.getText();
                textField1.setText(buttonSevenText);
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonEightText=textField1.getText()+btn8.getText();
                textField1.setText(buttonEightText);
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonNineText=textField1.getText()+btn9.getText();
                textField1.setText(buttonNineText);
            }
        });
        btnclear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonNineText=textField1.getText()+btn0.getText();
                textField1.setText(buttonNineText);
            }
        });

        changeState(ATMState.LogIn);
    }

    public static void main(String[] args) throws IOException, ATMDataProvider.IdNotFoundException, ATMDataProvider.InvalidPinException {
        ATMDataProvider atmData = new ATMDataProvider();
        atmData.init();
        int money = atmData.getMoney(43214321, 1234);
        System.out.println(money);
        atmData.setMoney(43214321, 1234, 150010);
        ATMs frame = new ATMs("ATM Symulator");
        frame.init();
        frame.setVisible(true);
    }

}
