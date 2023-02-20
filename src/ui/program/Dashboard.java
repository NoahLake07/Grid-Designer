package ui.program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Dashboard extends JFrame implements ActionListener {

        private JLabel headerLabel;
        private JButton newGridButton, loadGridButton, settingsButton;

        public Dashboard() {
            setTitle("Design Grid Dashboard");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Create header label
            headerLabel = new JLabel("Design Grid Dashboard", JLabel.CENTER);
            headerLabel.setFont(new Font("Serif", Font.BOLD, 20));
            add(headerLabel, BorderLayout.NORTH);

            // Create button panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(1, 3));

            // Create new grid button
            newGridButton = new JButton("New Grid");
            newGridButton.addActionListener(this);
            buttonPanel.add(newGridButton);

            // Create load grid button
            loadGridButton = new JButton("Load Grid");
            loadGridButton.addActionListener(this);
            buttonPanel.add(loadGridButton);

            // Create settings button
            settingsButton = new JButton("Settings");
            settingsButton.addActionListener(this);
            buttonPanel.add(settingsButton);

            // Add button panel to window
            add(buttonPanel, BorderLayout.CENTER);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newGridButton) {
                String widthStr = JOptionPane.showInputDialog(this, "Enter grid width:");
                String heightStr = JOptionPane.showInputDialog(this, "Enter grid height:");
                int width = Integer.parseInt(widthStr);
                int height = Integer.parseInt(heightStr);
                GridView x = new GridView(width,height);
                x.start();
                dispose();
            } else if (e.getSource() == loadGridButton) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    GridView gv = null;
                    try {
                        FileInputStream fileIn = new FileInputStream(selectedFile);
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        gv = (GridView) in.readObject();
                        in.close();
                        fileIn.close();
                    } catch (IOException i) {
                        i.printStackTrace();
                    } catch (ClassNotFoundException c) {
                        c.printStackTrace();
                    }

                    gv.start();
                }
            } else if (e.getSource() == settingsButton) {
                // TODO: open settings page
            }
        }

        public static void main(String[] args) {
            new Dashboard();
        }

    }


