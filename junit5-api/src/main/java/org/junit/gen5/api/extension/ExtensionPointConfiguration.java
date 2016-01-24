/*
 * Copyright 2015-2016 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.junit.gen5.api.extension;

import java.util.HashMap;
import java.util.Map;

import org.junit.gen5.api.extension.ExtensionPointRegistry.Position;

/**
 * Configuration for a concrete subtype of {@link ExtensionPoint}
 *
 * @since 5.0
 */
public class ExtensionPointConfiguration {

	private static Map<Class<? extends ExtensionPoint>, ExtensionPointConfiguration> type2config = new HashMap<>();

	public static void register(Class<? extends ExtensionPoint> extensionPointType,
			ExtensionPointConfiguration configuration) {
		type2config.put(extensionPointType, configuration);
	}

	public static ExtensionPointConfiguration getFor(Class<? extends ExtensionPoint> extensionPointType) {
		return type2config.getOrDefault(extensionPointType, DEFAULT);
	}

	private static ExtensionPointConfiguration DEFAULT = new ExtensionPointConfiguration(
		new Position[] { Position.DEFAULT }, ExtensionPointRegistry.ApplicationOrder.FORWARD);

	public Position[] getAllowedPositions() {
		return allowedPositions;
	}

	public ExtensionPointRegistry.ApplicationOrder getApplicationOrder() {
		return applicationOrder;
	}

	private final Position[] allowedPositions;
	private final ExtensionPointRegistry.ApplicationOrder applicationOrder;

	public ExtensionPointConfiguration(Position[] allowedPositions,
			ExtensionPointRegistry.ApplicationOrder applicationOrder) {
		this.allowedPositions = allowedPositions;
		this.applicationOrder = applicationOrder;
	}
}