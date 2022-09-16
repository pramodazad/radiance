/*
 * Copyright (c) 2005-2022 Radiance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.radiance.component.api.common.model;

import org.pushingpixels.radiance.common.api.icon.RadianceIcon;
import org.pushingpixels.radiance.component.api.common.RichTooltip;
import org.pushingpixels.radiance.component.api.ribbon.synapse.model.ComponentContentModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.EventListener;

public class TriStateCheckBoxContentModel implements ComponentContentModel {
    public enum State {
        DESELECTED,
        INDETERMINATE,
        SELECTED
    }

    public interface TriStateItemListener extends EventListener {
        void itemTriStateChanged(TriStateItemEvent e);
    }

    public interface TriStateItem {
        void addItemListener(TriStateItemListener l);

        void removeItemListener(TriStateItemListener l);
    }

    public static class TriStateItemEvent extends AWTEvent {
        /**
         * This event id indicates that an item's state changed.
         */
        public static final int ITEM_STATE_CHANGED  = 1000;

        Object item;

        State stateChange;

        public TriStateItemEvent(TriStateItem source, int id, Object item, State stateChange) {
            super(source, id);
            this.item = item;
            this.stateChange = stateChange;
        }

        /**
         * Returns the originator of the event.
         *
         * @return the ItemSelectable object that originated the event.
         */
        public TriStateItem getTriStateItem() {
            return (TriStateItem) source;
        }

        /**
         * Returns the item affected by the event.
         *
         * @return the item (object) that was affected by the event
         */
        public Object getItem() {
            return item;
        }

        /**
         * Returns the type of state change.
         */
        public State getStateChange() {
            return this.stateChange;
        }
    }


    private boolean isEnabled;
    private RadianceIcon.Factory iconFactory;
    private String caption;
    private RichTooltip richTooltip;
    private TriStateItemListener itemListener;

    private String text;
    private State state;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public static Builder builder() {
        return new Builder();
    }

    private TriStateCheckBoxContentModel() {
        super();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.addPropertyChangeListener(pcl);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        this.pcs.removePropertyChangeListener(pcl);
    }

    public String getText() {
        return this.text;
    }

    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        if (this.state != state) {
            State oldState = this.state;
            this.state = state;
            this.pcs.firePropertyChange("state", oldState, this.state);
        }
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (this.isEnabled != enabled) {
            this.isEnabled = enabled;
            this.pcs.firePropertyChange("enabled", !this.isEnabled, this.isEnabled);
        }
    }

    @Override
    public RadianceIcon.Factory getIconFactory() {
        return this.iconFactory;
    }

    @Override
    public String getCaption() {
        return this.caption;
    }

    @Override
    public RichTooltip getRichTooltip() {
        return this.richTooltip;
    }

    public TriStateItemListener getItemListener() {
        return this.itemListener;
    }

    public static class Builder {
        private boolean isEnabled = true;
        private RadianceIcon.Factory iconFactory;
        private String caption;
        private RichTooltip richTooltip;
        private String text;
        private State state;
        private ActionListener actionListener;
        private TriStateItemListener itemListener;

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setEnabled(boolean enabled) {
            this.isEnabled = enabled;
            return this;
        }

        public Builder setIconFactory(RadianceIcon.Factory iconFactory) {
            this.iconFactory = iconFactory;
            return this;
        }

        public Builder setCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public Builder setRichTooltip(RichTooltip richTooltip) {
            this.richTooltip = richTooltip;
            return this;
        }

        public Builder setItemListener(TriStateItemListener itemListener) {
            this.itemListener = itemListener;
            return this;
        }

        public TriStateCheckBoxContentModel build() {
            TriStateCheckBoxContentModel model = new TriStateCheckBoxContentModel();
            model.text = this.text;
            model.state = this.state;
            if (this.itemListener != null) {
                // Wrap the original application-provided item listener
                model.itemListener = new TriStateItemListener() {
                    private State lastState = state;

                    @Override
                    public void itemTriStateChanged(TriStateItemEvent e) {
                        State newState = e.getStateChange();
                        if (lastState == state) {
                            // de-dupe changes from multiple checkboxes created from this
                            // content model
                            return;
                        }
                        itemListener.itemTriStateChanged(e);
                        lastState = newState;
                    }
                };
            }
            model.isEnabled = this.isEnabled;
            model.iconFactory = this.iconFactory;
            model.caption = this.caption;
            model.richTooltip = this.richTooltip;
            return model;
        }
    }
}
