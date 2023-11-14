/*******************************************************************************
 * Copyright (c) 2020, 2023 Lablicate GmbH.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * Philip Wenig - initial API and implementation
 *******************************************************************************/
package org.eclipse.swtchart.extensions.core;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

public abstract class AbstractSeriesListUI extends TableViewer {

	protected AbstractSeriesListUI(Composite parent, int style) {

		super(parent, style);
	}

	public void clear() {

		super.setInput(null);
	}

	public boolean isTableSortable() {

		return getComparator() != null;
	}
}