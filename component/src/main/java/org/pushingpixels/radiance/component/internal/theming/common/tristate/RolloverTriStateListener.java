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
package org.pushingpixels.radiance.component.internal.theming.common.tristate;

import org.pushingpixels.radiance.component.api.common.JTriStateCheckBox;
import org.pushingpixels.radiance.theming.internal.animation.StateTransitionTracker;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;

public class RolloverTriStateListener extends BasicTriStateListener {
    /**
     * If the mouse pointer is currently inside the associated button area,
     * <code>this</code> flag is <code>true</code>.
     */
    private boolean isMouseInside;

    private StateTransitionTracker stateTransitionTracker;

    /**
     * Simple constructor.
     *
     * @param b The associated button.
     */
    public RolloverTriStateListener(JTriStateCheckBox b,
            StateTransitionTracker stateTransitionTracker) {
        super(b);
        this.isMouseInside = false;
        this.stateTransitionTracker = stateTransitionTracker;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseEntered(e);
            this.isMouseInside = true;
            boolean isMouseDrag = (e.getButton() == MouseEvent.BUTTON1);
            if (!isMouseDrag) {
                this.button.getTriStateButtonModel().setRollover(true);
            }
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseExited(e);
            this.isMouseInside = false;
            this.button.getTriStateButtonModel().setRollover(false);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseReleased(e);
            this.button.getTriStateButtonModel().setRollover(this.isMouseInside);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseMoved(e);
            this.button.getTriStateButtonModel().setRollover(this.isMouseInside);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.focusGained(e);
            if (!this.button.isShowing()) {
                // shouldn't happen. Is some lurking Swing bug
                return;
            }
            PointerInfo pi = MouseInfo.getPointerInfo();
            if (pi != null) {
                int px = pi.getLocation().x - this.button.getLocationOnScreen().x;
                int py = pi.getLocation().y - this.button.getLocationOnScreen().y;
                this.button.getTriStateButtonModel().setRollover(this.button.contains(px, py));
            }
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.focusLost(e);
            this.button.getTriStateButtonModel().setRollover(false);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseClicked(e);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mouseDragged(e);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.stateTransitionTracker.turnOffModelChangeTracking();
        try {
            super.mousePressed(e);
        } finally {
            this.stateTransitionTracker.onModelStateChanged();
        }
    }
}
