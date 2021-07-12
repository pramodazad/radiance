/*
 * Copyright (c) 2005-2021 Radiance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of the copyright holder nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.pushingpixels.demo.substance.main.check.selector;

import org.pushingpixels.demo.substance.main.check.FlexiComboBox;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSlices;

import javax.swing.*;

public class SubstanceIconFilterStrategySelector extends
        FlexiComboBox<SubstanceIconFilterStrategySelector.IconFilterStrategyConfiguration> {
    public static class IconFilterStrategyConfiguration {
        private String title;
        private SubstanceSlices.IconFilterStrategy activeIconFilterStrategy;
        private SubstanceSlices.IconFilterStrategy enabledIconFilterStrategy;
        private SubstanceSlices.IconFilterStrategy disabledIconFilterStrategy;

        private IconFilterStrategyConfiguration(String title,
                SubstanceSlices.IconFilterStrategy activeIconFilterStrategy,
                SubstanceSlices.IconFilterStrategy enabledIconFilterStrategy,
                SubstanceSlices.IconFilterStrategy disabledIconFilterStrategy) {
            this.title = title;
            this.activeIconFilterStrategy = activeIconFilterStrategy;
            this.enabledIconFilterStrategy = enabledIconFilterStrategy;
            this.disabledIconFilterStrategy = disabledIconFilterStrategy;
        }
    }

    public SubstanceIconFilterStrategySelector() {
        // populate the combobox
        super(new IconFilterStrategyConfiguration("Default",
                        SubstanceSlices.IconFilterStrategy.ORIGINAL,
                        SubstanceSlices.IconFilterStrategy.ORIGINAL,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME),
                new IconFilterStrategyConfiguration("Muted",
                        SubstanceSlices.IconFilterStrategy.ORIGINAL,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME),
                new IconFilterStrategyConfiguration("Text inactive",
                        SubstanceSlices.IconFilterStrategy.ORIGINAL,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_TEXT,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_TEXT),
                new IconFilterStrategyConfiguration("Text always",
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_TEXT,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_TEXT,
                        SubstanceSlices.IconFilterStrategy.THEMED_FOLLOW_TEXT));

        // add an action listener to change icon filter strategies based on user selection
        this.addActionListener(actionEvent -> SwingUtilities.invokeLater(() -> {
            IconFilterStrategyConfiguration selected = (IconFilterStrategyConfiguration) getSelectedItem();
            SubstanceCortex.GlobalScope.setIconFilterStrategies(
                    selected.activeIconFilterStrategy,
                    selected.enabledIconFilterStrategy,
                    selected.disabledIconFilterStrategy);
            SwingUtilities.getWindowAncestor(SubstanceIconFilterStrategySelector.this).repaint();
        }));
    }

    @Override
    public String getCaption(IconFilterStrategyConfiguration item) {
        return item.title;
    }
}