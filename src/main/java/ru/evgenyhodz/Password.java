package ru.evgenyhodz;

import com.toedter.calendar.JCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Evgeny Khodzitskiy (evgeny.hodz@gmail.com)
 * @since 10.02.2017
 */
public class Password {
    /**
     * Reference to JFrame.
     */
    private JFrame passFrame;
    /**
     * Reference to button.
     */
    private JButton enter;
    /**
     * Reference to JLabel.
     */
    private JLabel password;
    /**
     * Reference to JTextField.
     */
    private JTextField secret;

    /**
     * Creates password JFrame.
     */
    private void createPassFrame() {
        passFrame = new JFrame("Вход...");
        passFrame.setSize(500, 150);
        passFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        passFrame.setLocationRelativeTo(null);
        passFrame.getContentPane().setBackground(new JCalendar().getDecorationBackgroundColor());
        passFrame.setVisible(true);
        passFrame.setResizable(false);
        passFrame.setLayout(new GridBagLayout());
        passFrame.getRootPane().setDefaultButton(enter);
    }

    /**
     * Создаем кнопку ввода пароля.
     * Creates "Enter" button.
     */
    private void createButtons() {
        enter = new JButton("Ввести");
        enter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        enter.addActionListener(new Password.EnterButtonActionListener());
    }

    /**
     * Создание лейбла.
     * Creates label.
     */
    private void createLabels() {
        password = new JLabel(" Введите пароль: ");
    }

    /**
     * Создание поля ввода.
     * Creates the input text field.
     */
    private void createTextFields() {
        secret = new JTextField(15);
    }

    /**
     * Расставляем элементы/arranges elements.
     */
    private void pack() {
        passFrame.add(password, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        passFrame.add(secret, new GridBagConstraints(2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        passFrame.add(enter, new GridBagConstraints(3, 0, 1, 1, 0, 0, GridBagConstraints.SOUTH,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
    }

    /**
     * Сборка.
     * Builds the password window.
     */
    void init() {
        createButtons();
        createLabels();
        createTextFields();
        createPassFrame();
        pack();
    }

    /**
     * Слушатель кнопки "Ввести".
     * Button listener.
     */
    private class EnterButtonActionListener implements ActionListener {

        /**
         * Действие кнопки "Ввести".
         * Action of button "Enter".
         *
         * @param e - событие (нажатие на кнопку "Ввести")/action of button "Enter".
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (secret.getText().equals("*")) {
                passFrame.dispose();
                new Window().init();
            } else {
                JOptionPane.showMessageDialog(null, "Введен неверный пароль!",
                        "Э-э...", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
