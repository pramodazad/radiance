/*
 * Copyright (c) 2005-2023 Radiance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.radiance.component.internal.theming.common;

import org.pushingpixels.radiance.common.api.icon.RadianceIcon;
import org.pushingpixels.radiance.component.api.common.JCommandButton;
import org.pushingpixels.radiance.component.api.common.model.PopupButtonModel;
import org.pushingpixels.radiance.component.internal.theming.common.ui.ActionPopupTransitionAwareUI;
import org.pushingpixels.radiance.theming.api.ComponentState;
import org.pushingpixels.radiance.theming.api.RadianceThemingSlices;
import org.pushingpixels.radiance.theming.internal.animation.StateTransitionTracker;
import org.pushingpixels.radiance.theming.internal.blade.BladeArrowIconUtils;
import org.pushingpixels.radiance.theming.internal.blade.BladeColorScheme;
import org.pushingpixels.radiance.theming.internal.blade.BladeUtils;
import org.pushingpixels.radiance.theming.internal.utils.RadianceSizeUtils;
import org.pushingpixels.radiance.theming.internal.utils.icon.TransitionAware;

import javax.swing.*;
import java.awt.*;

@TransitionAware
public class BreadcrumbCommandButtonPopupIcon implements RadianceIcon {
    // Base width
    private int baseWidth;
    // Base height
    private int baseHeight;
    // Icon dimension
    private int dimension;

    private BladeColorScheme mutableColorScheme = new BladeColorScheme();

    public BreadcrumbCommandButtonPopupIcon() {
        int fontSize = RadianceSizeUtils.getComponentFontSize(null);
        this.baseWidth = (int) RadianceSizeUtils.getArrowIconWidth(fontSize);
        this.baseHeight = (int) RadianceSizeUtils.getArrowIconHeight(fontSize);
        this.dimension = Math.max(this.baseWidth, this.baseHeight);
    }

    @Override
    public void setDimension(Dimension newDimension) {
        this.baseWidth = newDimension.width;
        this.baseHeight = newDimension.height;
        this.dimension = Math.max(this.baseWidth, this.baseHeight);
    }

    @Override
    public boolean supportsColorFilter() {
        return false;
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getIconHeight() {
        return this.dimension;
    }

    @Override
    public int getIconWidth() {
        return this.dimension;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JCommandButton commandButton = (JCommandButton) c;

        StateTransitionTracker stateTransitionTracker =
                ((ActionPopupTransitionAwareUI) commandButton.getUI()).getPopupTransitionTracker();
        StateTransitionTracker.ModelStateInfo modelStateInfo =
                stateTransitionTracker.getModelStateInfo();

        ComponentState currState = modelStateInfo.getCurrModelState();

        BladeUtils.populateColorScheme(mutableColorScheme, c, modelStateInfo, currState,
                RadianceThemingSlices.ColorSchemeAssociationKind.MARK, false);

        PopupButtonModel model = commandButton.getPopupModel();
        boolean displayDownwards = model.isRollover() || model.isPopupShowing();

        int direction = displayDownwards ? SwingUtilities.SOUTH :
                (commandButton.getComponentOrientation().isLeftToRight()
                        ? SwingConstants.EAST : SwingConstants.WEST);
        int fontSize = RadianceSizeUtils.getComponentFontSize(c);

        int widthNudge = Math.round((this.dimension - this.baseWidth) / 2.0f);
        int heightNudge = Math.round((this.dimension - this.baseHeight) / 2.0f);
        int dx = displayDownwards ? widthNudge : heightNudge;
        int dy = displayDownwards ? heightNudge : widthNudge;

        Graphics2D graphics = (Graphics2D) g.create();
        graphics.translate(x + dx, y + dy);
        BladeArrowIconUtils.drawArrow(graphics, this.baseWidth, this.baseHeight,
                RadianceSizeUtils.getArrowStrokeWidth(fontSize) - 0.5f,
                direction, this.mutableColorScheme);
        graphics.dispose();
    }
}
