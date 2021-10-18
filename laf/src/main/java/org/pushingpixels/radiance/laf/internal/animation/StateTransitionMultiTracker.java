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
package org.pushingpixels.radiance.laf.internal.animation;

import java.util.HashMap;
import java.util.Map;

public final class StateTransitionMultiTracker<T> {
	private Map<Comparable<T>, StateTransitionTracker> trackerMap;

	private boolean isInCleaning;

	public StateTransitionMultiTracker() {
		this.trackerMap = new HashMap<>();
	}

	public synchronized void clear() {
		this.isInCleaning = true;
		for (StateTransitionTracker tracker : this.trackerMap.values()) {
			tracker.endTransition();
		}
		this.trackerMap.clear();
		this.isInCleaning = false;
	}

	public synchronized int size() {
		return this.trackerMap.size();
	}

	public synchronized StateTransitionTracker getTracker(Comparable<T> id) {
		return this.trackerMap.get(id);
	}

	public synchronized void addTracker(final Comparable<T> id,
			final StateTransitionTracker tracker) {
		this.trackerMap.put(id, tracker);

		// System.out.println("Storing tracker @" + tracker.hashCode() + " for "
		// + tracker.component.getClass().getSimpleName() + ":" + id);

		StateTransitionListener listener = new StateTransitionListener() {
			@Override
			public void onModelStateTransition(
					StateTransitionEvent stateTransitionEvent) {
				if (isInCleaning) {
					return;
				}

				if (!tracker.hasRunningTimelines()) {
					// System.out.println("Removing tracker for " + id);
                    removeTracker(id);
					tracker.unregisterModelListeners();
					tracker.removeStateTransitionListener(this);
				}
			}

			@Override
			public void onFocusStateTransition(
					StateTransitionEvent stateTransitionEvent) {
				if (isInCleaning) {
                    return;
                }

				if (!tracker.hasRunningTimelines()) {
					// System.out.println("Removing tracker for " + id);
                    removeTracker(id);
					tracker.unregisterModelListeners();
					tracker.removeStateTransitionListener(this);
				}
			}
		};
		tracker.addStateTransitionListener(listener);
	}

	public synchronized void removeTracker(final Comparable<T> id) {
		trackerMap.remove(id);
	}
}