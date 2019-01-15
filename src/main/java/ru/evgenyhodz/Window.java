package ru.evgenyhodz;

import com.toedter.calendar.JCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Основное окно программы.
 * <p>
 * Basic window of program.
 *
 * @author Evgeny Khodzitskiy (evgeny.hodz@gmail.com)
 * @since 04.02.2017
 */
public class Window {
    /**
     * Ссылка на кнопку расчета.
     * <p>
     * Ref to button.
     */
    private JButton calculate;
    /**
     * Ссылка на кнопку "Очистить поля".
     * <p>
     * Ref to button.
     */
    private JButton clear;
    /**
     * Ссылка на фрейм.
     * <p>
     * Ref to JFrame of basic window.
     */
    private JFrame window;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JTextField sumPfr;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JTextField sumFfoms;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JTextField total;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JLabel resultPfr;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JLabel resultFfoms;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JLabel resultTotal;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JLabel begin;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element of basic window.
     */
    private JLabel end;
    /**
     * Ссылка на объект стилизации текста.
     * <p>
     * Ref to element.
     */
    private Font font;
    /**
     * Ссылка на элемент окна.
     * <p>
     * Ref to element.
     */
    private CalculateTax taxActions;
    /**
     * Календарик для даты начала отчетного периода.
     * <p>
     * Calendar for the beginning of billing period.
     */
    private JCalendar startCalendar;
    /**
     * Календарик для даты конца отчетного периода.
     * <p>
     * Calendar for the end of billing period.
     */
    private JCalendar endCalendar;
    /**
     * Ref to color.
     */
    private static final Color COLOR = new JCalendar().getDecorationBackgroundColor();

    /**
     * Создаем окно нашей программы:
     * 1) Создается объект для формы
     * 2) Задаем размер окна
     * 3) Заголовок окна
     * 4) Задаем команду выхода нажатием на крестик
     * 5) Окошко будет появляться в центре экрана
     * 6) Отображаем форму на экране.
     * <p>
     * Creates elements for basic window.
     */
    private void createWindow() {
        window = new JFrame("РАСЧЕТ фиксированных взносов ИП 2017");
        window.setSize(600, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);

        window.setVisible(true);
        window.setResizable(false);
        window.setLayout(new GridBagLayout());
    }

    /**
     * Создаем два объекта календаря.
     * Creates two JCalendar objects.
     */
    private void createCalendar() {
        startCalendar = new JCalendar();
        startCalendar.setSize(150, 150);
        endCalendar = new JCalendar();
    }

    /**
     * Добавление компонентов в окно.
     * И расположение их в окне...
     * <p>
     * Adds and arranges items in the window.
     */
    private void addComponent() {
        // labels:
        window.add(begin, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(end, new GridBagConstraints(2, 1, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        // Calendars:
        window.add(startCalendar, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(endCalendar, new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        // Labels:
        window.add(resultPfr, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(resultFfoms, new GridBagConstraints(1, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(resultTotal, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        // Result JTextFields:
        window.add(sumPfr, new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(sumFfoms, new GridBagConstraints(2, 4, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(total, new GridBagConstraints(2, 5, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        //  Buttons:
        window.add(calculate, new GridBagConstraints(1, 6, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));
        window.add(clear, new GridBagConstraints(2, 6, 1, 1, 0, 0, GridBagConstraints.CENTER,
                GridBagConstraints.HORIZONTAL, new Insets(4, 4, 4, 4), 0, 0));

    }

    /**
     * Создание кнопки расчета взносов:
     * 1) Создаем объект кнопки
     * 2) Задаем стиль и размер шрифта в кнопке
     * 3) Меняем курсор мыши при наведении на кнопку
     * 4) Добавляем слушателя.
     * <p>
     * Creates button "Рассчитать!".
     */
    private void createButtons() {
        calculate = new JButton("Посчитать!");
        calculate.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        calculate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        calculate.addActionListener(new CalculateActionListener());

        clear = new JButton("Выйти");
        clear.setFont(new Font("Book Antiqua", Font.BOLD, 18));
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.addActionListener(new ClearActionListener());
    }

    /**
     * Создание двух полей ввода.
     * Creates two input fields.
     */
    private void createTextFields() {

        sumPfr = new JTextField(10);
        sumPfr.setHorizontalAlignment(JTextField.CENTER);
        sumPfr.setBackground(Color.lightGray);
        sumPfr.setFont(font);
        sumPfr.setEditable(false);

        sumFfoms = new JTextField(10);
        sumFfoms.setBackground(Color.lightGray);
        sumFfoms.setHorizontalAlignment(JTextField.CENTER);
        sumFfoms.setFont(font);
        sumFfoms.setEditable(false);

        total = new JTextField(10);
        total.setBackground(COLOR);
        total.setHorizontalAlignment(JTextField.CENTER);
        total.setFont(font);
        total.setEditable(false);
    }

    /**
     * Создание лейбелов.
     * Creates labels.
     */
    private void createLabels() {
        font = new Font("Times New Roman", Font.ROMAN_BASELINE, 18);

        begin = new JLabel(" Начало расчетного периода: ");
        begin.setFont(font);

        end = new JLabel(" Конец расчетного периода: ");
        end.setFont(font);

        resultPfr = new JLabel(" Пенсионный фонд:");
        resultPfr.setFont(font);
        resultPfr.setOpaque(true);
        resultPfr.setBackground(Color.lightGray);

        resultFfoms = new JLabel(" ФФОМС:");
        resultFfoms.setFont(font);
        resultFfoms.setOpaque(true);
        resultFfoms.setBackground(Color.lightGray);

        resultTotal = new JLabel(" Сумма ВСЕГО:");
        resultTotal.setFont(font);
        resultTotal.setOpaque(true);
        resultTotal.setBackground(COLOR);
    }

    /**
     * Сборка.
     * Builds main program.
     */
    public void init() {
        createButtons();
        createTextFields();
        createWindow();
        createLabels();
        createCalendar();
        addComponent();
        window.pack();
        //Важный момент/that is important here:
        taxActions = new CalculateTax(new Validation(), 7500, 0.26, 0.051);
    }

    /**
     * Действие кнопки расчета. Мы берем две введенные даты и считаем
     * взносы, результаты выводим в поля ниже.
     * <p>
     * Action of button "Рассчитать!". It takes two dates and calculate payments.
     * Results will be in the fields below.
     */
    private class CalculateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (startCalendar.getDate().after(endCalendar.getDate())
                    || endCalendar.getDate().before(startCalendar.getDate())) {
                JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введенных дат.",
                        "Некорректные даты", JOptionPane.ERROR_MESSAGE);
            } else if (startCalendar.getYearChooser().getYear() == 2017
                    && startCalendar.getYearChooser().getYear() == 2017) {
                taxActions.countAll(startCalendar.getDate(), endCalendar.getDate());
                sumPfr.setText(String.valueOf(taxActions.getPfr()));
                sumFfoms.setText(String.valueOf(taxActions.getFfoms()));
                total.setText(String.valueOf(taxActions.getTotal()));
            } else {
                JOptionPane.showMessageDialog(null, "Ошибка! Задайте период внутри 2017 года.",
                        "Э-э...", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Слушатель для выхода из программки.
     * Listener to exit the program.
     */
    private class ClearActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
        }
    }
}
