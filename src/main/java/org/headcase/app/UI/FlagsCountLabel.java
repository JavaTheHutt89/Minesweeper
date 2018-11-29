package org.headcase.app.UI;

import javax.swing.*;

public class FlagsCountLabel extends JLabel {
    int flagsCount;

    public FlagsCountLabel() {
    }

    public int getFlagsCount() {
        return flagsCount;
    }

    public void setFlagsCount(int flagsCount) {
        this.flagsCount = flagsCount;
        setText("Flags: " + this.flagsCount);
    }
}
