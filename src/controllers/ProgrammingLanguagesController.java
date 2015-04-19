/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Jacob Gorney, Max Savard, Matt Mossner, Spencer Kokaly
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package controllers;

import businessobjects.ProgrammingLanguage;
import businessobjects.SettingsCommunicator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import ui.AdminFrame;

/**
 *
 * @author Matthew Mossner
 */
public class ProgrammingLanguagesController implements ActionListener {
    
    private final AdminFrame adminFrame;
    private HashSet<ProgrammingLanguage> enabledLanguages;
    
    public ProgrammingLanguagesController(final AdminFrame adminFrame) {
        this.adminFrame = adminFrame;
        adminFrame.jcmbLanguages.addActionListener(this);
        adminFrame.jchkEnabled.addActionListener(this);
        adminFrame.jbtnRemoveLanguage.addActionListener(this);
        adminFrame.jbtnAddLanguage.addActionListener(this);
        adminFrame.jbtnSaveLanguageSettings.addActionListener(this);
        adminFrame.jchkCompiled.addActionListener(this);
        adminFrame.jbtnBrowseCompiler.addActionListener(this);
        adminFrame.jtxtCompilerExecutable1.addActionListener(this);
        adminFrame.jtxtCompilerArgs1.addActionListener(this);
        adminFrame.jtxtCompilerInExt.addActionListener(this);
        adminFrame.jtxtCompilerOutExt1.addActionListener(this);
        adminFrame.jchkInterpreted.addActionListener(this);
        adminFrame.jbtnBrowseInterpreter.addActionListener(this);
        adminFrame.jtxtInterpreterExecutable.addActionListener(this);
        adminFrame.jtxtInterpreterArgs.addActionListener(this);
        adminFrame.jtxtInterpreterInExt.addActionListener(this);
        adminFrame.jtxtFilenameRegEx.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminFrame.jcmbLanguages)
            jcmbLanguagesSelectionChanged();
        else if (e.getSource() == adminFrame.jchkEnabled)
            jchkEnabledCheckChanged();
        else if (e.getSource() == adminFrame.jbtnRemoveLanguage)
            jbtnRemoveLanguageClicked();
        else if (e.getSource() == adminFrame.jbtnAddLanguage)
            jbtnAddLanguageClicked();
        else if (e.getSource() == adminFrame.jbtnSaveLanguageSettings)
            jbtnSaveLanguageSettingsClicked();
        else if (e.getSource() == adminFrame.jchkCompiled)
            jchkCompiledCheckChanged();
        else if (e.getSource() == adminFrame.jbtnBrowseCompiler)
            jbtnBrowseCompilerClicked();
        else if (e.getSource() == adminFrame.jtxtCompilerExecutable1)
            jtxtCompilerExecutableTextChanged();
        else if (e.getSource() == adminFrame.jtxtCompilerArgs1)
            jtxtCompilerArgsTextChanged();
        else if (e.getSource() == adminFrame.jtxtCompilerInExt)
            jtxtCompilerInExtTextChanged();
        else if (e.getSource() == adminFrame.jtxtCompilerOutExt1)
            jtxtCompilerOutExtTextChanged();
        else if (e.getSource() == adminFrame.jchkInterpreted)
            jchkInterpretedCheckChanged();
        else if (e.getSource() == adminFrame.jbtnBrowseInterpreter)
            jbtnBrowserInterpreterClicked();
        else if (e.getSource() == adminFrame.jtxtInterpreterExecutable)
            jtxtInterpreterExecutableTextChanged();
        else if (e.getSource() == adminFrame.jtxtInterpreterArgs)
            jtxtInterpreterArgsTextChanged();
        else if (e.getSource() == adminFrame.jtxtInterpreterInExt)
            jtxtInterpreterInExtTextChanged();
        else if (e.getSource() == adminFrame.jtxtFilenameRegEx)
            jtxtFileNameRegExTextChanged();
    }

    private void jcmbLanguagesSelectionChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        adminFrame.jchkEnabled.setSelected(enabledLanguages.contains(selectedLanguage));
        adminFrame.jtxtLanguageName.setText(selectedLanguage.toString());
        adminFrame.jchkCompiled.setSelected(selectedLanguage.isCompiled());
        adminFrame.jtxtCompilerExecutable1.setText(selectedLanguage.getCompilerExecutable());
        adminFrame.jtxtCompilerArgs1.setText(selectedLanguage.getCompilerArgs());
        adminFrame.jtxtCompilerInExt.setText(selectedLanguage.getCompilerInExt());
        adminFrame.jtxtCompilerOutExt1.setText(selectedLanguage.getCompilerOutExt());
        adminFrame.jchkInterpreted.setSelected(selectedLanguage.isInterpreted());
        adminFrame.jtxtInterpreterExecutable.setText(selectedLanguage.getInterpreterExecutable());
        adminFrame.jtxtInterpreterArgs.setText(selectedLanguage.getInterpreterArgs());
        adminFrame.jtxtInterpreterInExt.setText(selectedLanguage.getInterpreterInExt());
        adminFrame.jtxtFilenameRegEx.setText(selectedLanguage.getFileNameRegEx());
    }

    private void jchkEnabledCheckChanged() {
        if (adminFrame.jchkEnabled.isSelected())
            enabledLanguages.add((ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem());
        else
            enabledLanguages.remove((ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem());
        adminFrame.jpnlLanguageSettings.setVisible(adminFrame.jchkEnabled.isSelected());
    }

    private void jbtnRemoveLanguageClicked() {
        adminFrame.jcmbLanguages.removeItem(adminFrame.jcmbLanguages.getSelectedItem());
    }

    private void jbtnAddLanguageClicked() {
        String name = JOptionPane.showInputDialog(adminFrame, "Language name");
        ProgrammingLanguage newLanguage = new ProgrammingLanguage(name, true, false);
        adminFrame.jcmbLanguages.addItem(newLanguage);
        adminFrame.jcmbLanguages.setSelectedItem(newLanguage);
        adminFrame.jchkEnabled.setSelected(true);
    }

    private void jbtnSaveLanguageSettingsClicked() {
        if (JOptionPane.showConfirmDialog(adminFrame, "Are you "
                + "sure you want to save changes?", "Save Changes", 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            SettingsCommunicator.setAvailableLanguages(enabledLanguages);
        }
    }

    private void jchkCompiledCheckChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setCompiled(adminFrame.jchkCompiled.isSelected());
    }

    private void jbtnBrowseCompilerClicked() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(adminFrame);
        if (result == JFileChooser.APPROVE_OPTION)
            adminFrame.jtxtCompilerExecutable1.setText(chooser.getSelectedFile().getAbsolutePath());
    }

    private void jchkInterpretedCheckChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setInterpreted(adminFrame.jchkInterpreted.isSelected());
    }

    private void jbtnBrowserInterpreterClicked() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(adminFrame);
        if (result == JFileChooser.APPROVE_OPTION)
            adminFrame.jtxtCompilerExecutable1.setText(chooser.getSelectedFile().getAbsolutePath());
    }

    private void jtxtCompilerExecutableTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setCompilerExecutable(adminFrame.jtxtCompilerExecutable1.getText());
    }

    private void jtxtCompilerArgsTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setCompilerArgs(adminFrame.jtxtCompilerArgs1.getText());
    }

    private void jtxtCompilerInExtTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setCompilerInExt(adminFrame.jtxtCompilerInExt.getText());
    }

    private void jtxtCompilerOutExtTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setCompilerOutExt(adminFrame.jtxtCompilerOutExt1.getText());
    }

    private void jtxtInterpreterExecutableTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setInterpreterExecutable(adminFrame.jtxtInterpreterExecutable.getText());
    }

    private void jtxtInterpreterArgsTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setInterpreterArgs(adminFrame.jtxtInterpreterArgs.getText());
    }

    private void jtxtInterpreterInExtTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setInterpreterInExt(adminFrame.jtxtInterpreterInExt.getText());
    }

    private void jtxtFileNameRegExTextChanged() {
        ProgrammingLanguage selectedLanguage = (ProgrammingLanguage) adminFrame.jcmbLanguages.getSelectedItem();
        selectedLanguage.setFileNameRegEx(adminFrame.jtxtFilenameRegEx.getText());
    }
}
