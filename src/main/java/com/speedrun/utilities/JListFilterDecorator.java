package com.speedrun.utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class JListFilterDecorator {
    public static <T> JPanel decorate(JList<T> jList, BiPredicate<T, String> userFilter, String basedTextField) {
        if (!(jList.getModel() instanceof DefaultListModel)) {
            throw new IllegalArgumentException("List model must be an instance of DefaultListModel");
        }
        DefaultListModel<T> model = (DefaultListModel<T>) jList.getModel();
        List<T> items = getItems(model);
        JTextField textField = new JTextField();
        textField.setText(basedTextField);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().equals("")){
                    textField.setText(basedTextField);
                }
            }
        });
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filter();
            }

            private void filter() {
                model.clear();
                String s = textField.getText();
                for (T item : items) {
                    if(userFilter.test(item, s)){
                        model.addElement(item);
                    }
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(textField, BorderLayout.NORTH);
        JScrollPane pane = new JScrollPane(jList);
        panel.add(pane);
        return panel;
    }

    private static <T> List<T> getItems(DefaultListModel<T> model) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            list.add(model.elementAt(i));
        }
        return list;
    }
}