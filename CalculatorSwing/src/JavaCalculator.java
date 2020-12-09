import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaCalculator {

    private double total1 = 0.0;
    private double total2 = 0.0;
    private  char math_operator;
    private JPanel JavaCalculator;
    private JTextField textField1;
    private JButton buttonDot;
    private JButton buttonEight;
    private JButton buttonTwo;
    private JButton buttonFive;
    private JButton buttonThree;
    private JButton buttonSix;
    private JButton buttonNine;
    private JButton buttonClear;
    private JButton buttonMultiply;
    private JButton buttonEquals;
    private JButton buttonMinus;
    private JButton buttonPlus;
    private JButton buttonOne;
    private JButton buttonFour;
    private JButton buttonSeven;
    private JButton buttonZero;
    private JButton buttonDivide;

    private void getOperator(String buttonText)
    { //jako argument podajemy tekst z kliknietego przycisku
        math_operator = buttonText.charAt(0); //konwersja stringa na chara
        total1=total1+ Double.parseDouble(textField1.getText());
        textField1.setText("");
    }

    public JavaCalculator() {

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //gdybysmy wpisali
                //String buttonOneText=buttonOne.getText();
                //moglibysmy wyswietlic tylko 1
            String buttonOneText=textField1.getText()+buttonOne.getText(); //to display 11
            textField1.setText(buttonOneText);
            }
        });
        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonDwoText=textField1.getText()+ buttonTwo.getText();
                textField1.setText(buttonDwoText);
            }
        });
        buttonThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonThreeText=textField1.getText()+buttonThree.getText();
                textField1.setText(buttonThreeText);
            }
        });
        buttonFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonFourText=textField1.getText()+buttonFour.getText();
                textField1.setText(buttonFourText);
            }
        });
        buttonFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonFiveText=textField1.getText()+buttonFive.getText();
                textField1.setText(buttonFiveText);
            }
        });
        buttonSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonSixText=textField1.getText()+buttonSix.getText();
                textField1.setText(buttonSixText);
            }
        });
        buttonSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonSevenText=textField1.getText()+buttonSeven.getText();
                textField1.setText(buttonSevenText);
            }
        });
        buttonEight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonEightText=textField1.getText()+buttonEight.getText();
                textField1.setText(buttonEightText);
            }
        });
        buttonNine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonNineText=textField1.getText()+buttonNine.getText();
                textField1.setText(buttonNineText);
            }
        });
        buttonZero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonZeroText=textField1.getText()+buttonZero.getText();
                textField1.setText(buttonZeroText);
            }
        });

        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           // total1=total1+Double.parseDouble(textField1.getText());  //przechowowana wartosc jest stringiem musimy
                //zrzutowac na doubla zeby moc wykonywac operacje
            //textField1.setText(""); //do wyczyszczenia pola
                String button_text = buttonPlus.getText();
                getOperator(button_text);
            }
        });
        buttonEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //total2=total1+Double.parseDouble(textField1.getText());
            //textField1.setText(Double.toString(total2)); //zeby moc wyswietlic w polu wynik
                //potrzebujemy zrzutowac na stringa, tylko stringa mozemy wyswietlic
            //total1=0;
            switch(math_operator){
                case '+':
                    total2=total1+Double.parseDouble(textField1.getText());
                    break;
                case '-':
                    total2=total1-Double.parseDouble(textField1.getText());
                    break;

                case '/':
                    total2=total1/Double.parseDouble(textField1.getText());
                    break;

                case '*':
                    total2=total1*Double.parseDouble(textField1.getText());
                    break;
            }
            textField1.setText(Double.toString(total2));
            total1=0;
            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            total2=0;
            textField1.setText("");
            }
        });
        buttonDot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().equals("")) { //zeby nam sie zrobila 0.32 a nie .32
                    textField1.setText(("0."));
                } else if (textField1.getText().contains(".")) {
                    buttonDot.setEnabled(false);
                } else {
                    String buttonDotText = textField1.getText() + buttonDot.getText();
                    textField1.setText(buttonDotText);
                }
                buttonDot.setEnabled(true);
            }
        });

        buttonDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String button_text = buttonDivide.getText();
                getOperator(button_text);
            }
        });
        buttonMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String button_text = buttonMultiply.getText();
                getOperator(button_text);
            }
        });
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String button_text = buttonMinus.getText();
                getOperator(button_text);
            }
        });
    }

    public static void main(String[] args) {
        //okno z tytułem "JavaCalculator"
        JFrame frame = new JFrame("JavaCalculator");
        frame.setContentPane(new JavaCalculator().JavaCalculator);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
