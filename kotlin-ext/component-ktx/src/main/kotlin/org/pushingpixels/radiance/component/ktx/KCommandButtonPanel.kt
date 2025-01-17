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
package org.pushingpixels.radiance.component.ktx

import org.pushingpixels.radiance.component.api.common.CommandButtonPresentationState
import org.pushingpixels.radiance.component.api.common.HorizontalAlignment
import org.pushingpixels.radiance.component.api.common.model.*
import org.pushingpixels.radiance.component.api.common.model.panel.MenuPopupPanelLayoutSpec
import org.pushingpixels.radiance.component.api.common.model.panel.PanelLayoutSpec
import org.pushingpixels.radiance.component.api.common.model.panel.PanelRowFillSpec
import org.pushingpixels.radiance.component.api.common.projection.CommandPanelProjection
import org.pushingpixels.radiance.theming.api.RadianceThemingSlices
import java.awt.Dimension
import java.awt.Insets
import javax.swing.JComponent

@RadianceElementMarker
public class KCommandButtonPanelPresentation {
    public var layoutSpec: PanelLayoutSpec = PanelLayoutSpec.RowFill(PanelRowFillSpec.Adaptive(48))
    public var contentPadding: Insets = CommandPanelPresentationModel.DEFAULT_CONTENT_PADDING
    public var contentGap: Int = CommandPanelPresentationModel.DEFAULT_GAP
    public var toShowGroupLabels: Boolean = true
    public var commandPresentationState: CommandButtonPresentationState? = null
    public var commandIconDimension: Dimension? = null
    public var commandHorizontalAlignment: HorizontalAlignment =
        CommandButtonPresentationModel.DEFAULT_HORIZONTAL_ALIGNMENT
    public var commandContentPadding: Insets =
        CommandButtonPresentationModel.COMPACT_BUTTON_CONTENT_PADDING
    public var commandHorizontalGapScaleFactor: Double =
        CommandButtonPresentationModel.DEFAULT_GAP_SCALE_FACTOR
    public var commandVerticalGapScaleFactor: Double =
        CommandButtonPresentationModel.DEFAULT_GAP_SCALE_FACTOR
    public var commandPopupFireTrigger: BaseCommandButtonPresentationModel.PopupFireTrigger =
        BaseCommandButtonPresentationModel.PopupFireTrigger.ON_PRESSED
    public var commandSelectedStateHighlight: BaseCommandButtonPresentationModel.SelectedStateHighlight =
        BaseCommandButtonPresentationModel.SelectedStateHighlight.FULL_SIZE
    public var backgroundAppearanceStrategy: RadianceThemingSlices.BackgroundAppearanceStrategy =
        RadianceThemingSlices.BackgroundAppearanceStrategy.FLAT
    public var activeIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.ORIGINAL
    public var enabledIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.ORIGINAL
    public var disabledIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME
    public var popupPlacementStrategy: RadianceThemingSlices.PopupPlacementStrategy =
        RadianceThemingSlices.PopupPlacementStrategy.Downward.HALIGN_START

    internal fun toCommandPanelPresentationModel(): CommandPanelPresentationModel {
        val presentationModelBuilder = CommandPanelPresentationModel.builder()
        if (this.commandPresentationState != null) {
            presentationModelBuilder.setCommandPresentationState(this.commandPresentationState)
        } else {
            presentationModelBuilder.setCommandPresentationState(
                CommandButtonPresentationState.BIG_FIT_TO_ICON
            )
            presentationModelBuilder.setCommandIconDimension(this.commandIconDimension)
        }
        presentationModelBuilder.setLayoutSpec(this.layoutSpec)
        presentationModelBuilder.setContentPadding(this.contentPadding)
        presentationModelBuilder.setContentGap(this.contentGap)
        presentationModelBuilder.setToShowGroupLabels(this.toShowGroupLabels)
        presentationModelBuilder.setCommandHorizontalAlignment(this.commandHorizontalAlignment)
        presentationModelBuilder.setCommandContentPadding(this.commandContentPadding)
        presentationModelBuilder.setCommandHorizontalGapScaleFactor(this.commandHorizontalGapScaleFactor)
        presentationModelBuilder.setCommandVerticalGapScaleFactor(this.commandVerticalGapScaleFactor)
        presentationModelBuilder.setBackgroundAppearanceStrategy(this.backgroundAppearanceStrategy)
        presentationModelBuilder.setIconFilterStrategies(
            this.activeIconFilterStrategy,
            this.enabledIconFilterStrategy,
            this.disabledIconFilterStrategy
        )
        presentationModelBuilder.setCommandPopupFireTrigger(this.commandPopupFireTrigger)
        presentationModelBuilder.setCommandSelectedStateHighlight(this.commandSelectedStateHighlight)
        presentationModelBuilder.setPopupPlacementStrategy(this.popupPlacementStrategy)

        return presentationModelBuilder.build()
    }
}

@RadianceElementMarker
public class KCommandPopupMenuPanelLayoutSpec {
    public var columnCount: Int by NonNullDelegate { false }
    public var visibleRowCount: Int by NonNullDelegate { false }

    internal fun toMenuPopupPanelLayoutSpec(): MenuPopupPanelLayoutSpec {
        return MenuPopupPanelLayoutSpec(columnCount, visibleRowCount)
    }
}

@RadianceElementMarker
public class KCommandPopupMenuPanelPresentation {
    public var layoutSpec: KCommandPopupMenuPanelLayoutSpec = KCommandPopupMenuPanelLayoutSpec()
    public var contentPadding: Insets = CommandPanelPresentationModel.DEFAULT_CONTENT_PADDING
    public var contentGap: Int = CommandPanelPresentationModel.DEFAULT_GAP
    public var toShowGroupLabels: Boolean = true
    public var commandPresentationState: CommandButtonPresentationState? = null
    public var commandIconDimension: Dimension? = null
    public var commandContentPadding: Insets =
        CommandButtonPresentationModel.COMPACT_BUTTON_CONTENT_PADDING
    public var commandHorizontalAlignment: HorizontalAlignment =
        CommandButtonPresentationModel.DEFAULT_HORIZONTAL_ALIGNMENT
    public var commandHorizontalGapScaleFactor: Double =
        CommandButtonPresentationModel.DEFAULT_GAP_SCALE_FACTOR
    public var commandVerticalGapScaleFactor: Double =
        CommandButtonPresentationModel.DEFAULT_GAP_SCALE_FACTOR
    public var backgroundAppearanceStrategy: RadianceThemingSlices.BackgroundAppearanceStrategy =
        RadianceThemingSlices.BackgroundAppearanceStrategy.FLAT
    public var activeIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.ORIGINAL
    public var enabledIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.ORIGINAL
    public var disabledIconFilterStrategy: RadianceThemingSlices.IconFilterStrategy =
        RadianceThemingSlices.IconFilterStrategy.THEMED_FOLLOW_COLOR_SCHEME

    public fun layoutSpec(init: KCommandPopupMenuPanelLayoutSpec.() -> Unit) {
        layoutSpec.init()
    }

    internal fun toCommandPopupMenuPanelPresentationModel(): CommandPopupMenuPanelPresentationModel {
        val presentationModelBuilder = CommandPopupMenuPanelPresentationModel.builder()
        presentationModelBuilder.setLayoutSpec(this.layoutSpec.toMenuPopupPanelLayoutSpec())
        presentationModelBuilder.setContentPadding(this.contentPadding)
        presentationModelBuilder.setContentGap(this.contentGap)
        if (this.commandPresentationState != null) {
            presentationModelBuilder.setCommandPresentationState(this.commandPresentationState)
        } else {
            presentationModelBuilder.setCommandPresentationState(
                CommandButtonPresentationState.BIG_FIT_TO_ICON
            )
            presentationModelBuilder.setCommandIconDimension(this.commandIconDimension)
        }
        presentationModelBuilder.setCommandContentPadding(this.commandContentPadding)
        presentationModelBuilder.setToShowGroupLabels(this.toShowGroupLabels)
        presentationModelBuilder.setCommandHorizontalAlignment(this.commandHorizontalAlignment)
        presentationModelBuilder.setCommandHorizontalGapScaleFactor(this.commandHorizontalGapScaleFactor)
        presentationModelBuilder.setCommandVerticalGapScaleFactor(this.commandVerticalGapScaleFactor)
        presentationModelBuilder.setBackgroundAppearanceStrategy(this.backgroundAppearanceStrategy)
        presentationModelBuilder.setIconFilterStrategies(
            this.activeIconFilterStrategy,
            this.enabledIconFilterStrategy,
            this.disabledIconFilterStrategy
        )

        return presentationModelBuilder.build()
    }
}

@RadianceElementMarker
public class KCommandButtonPanel {
    public class KCommandButtonPanelGroup {
        public var title: String by NonNullDelegate { false }
        internal val commands = arrayListOf<KCommand>()

        public fun command(init: KCommand.() -> Unit): KCommand {
            val command = KCommand()
            command.init()
            commands.add(command)
            return command
        }

        internal fun toJavaCommandGroupModel(): CommandGroup {
            return CommandGroup(
                this.title,
                this.commands.map { it.asJavaCommand() })
        }
    }

    private var hasBeenConverted: Boolean = false

    private val commandGroups = arrayListOf<KCommandButtonPanelGroup>()
    internal val presentation: KCommandButtonPanelPresentation = KCommandButtonPanelPresentation()
    public var isSingleSelectionMode: Boolean by NonNullDelegate { hasBeenConverted }

    init {
        isSingleSelectionMode = false
    }

    public fun commandGroup(init: KCommandButtonPanelGroup.() -> Unit): KCommandButtonPanelGroup {
        val commandGroup = KCommandButtonPanelGroup()
        commandGroup.init()
        commandGroups.add(commandGroup)
        return commandGroup
    }

    public fun presentation(init: KCommandButtonPanelPresentation.() -> Unit) {
        presentation.init()
    }

    internal fun toJavaButtonPanel(): JComponent {
        val hasInitialState = (presentation.commandPresentationState != null)
        val hasInitialDimension = (presentation.commandIconDimension != null)

        if (!hasInitialState && !hasInitialDimension) {
            throw IllegalStateException("No presentation state or dimension specified")
        }

        val contentModel =
            CommandPanelContentModel(
                commandGroups.map { it.toJavaCommandGroupModel() })
        val presentationModel = presentation.toCommandPanelPresentationModel()

        return CommandPanelProjection(
            contentModel,
            presentationModel
        ).buildComponent()
    }
}

public fun commandButtonPanel(init: KCommandButtonPanel.() -> Unit): KCommandButtonPanel {
    val commandButtonPanel = KCommandButtonPanel()
    commandButtonPanel.init()
    return commandButtonPanel
}
