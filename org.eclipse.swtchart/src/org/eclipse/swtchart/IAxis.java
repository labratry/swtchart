/*******************************************************************************
 * Copyright (c) 2008, 2024 SWTChart project.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 * yoshitaka - initial API and implementation
 * Philip Wenig - option to skip drawing the axis line
 * Sebastien Darche - Implement arbitrary base log scale
 *******************************************************************************/
package org.eclipse.swtchart;

import org.eclipse.swt.events.MouseEvent;

/**
 * An axis which is composed of title and tick. Grid is associated with axis.
 */
public interface IAxis {

	/** An axis direction */
	public enum Direction {
		/** the constant to represent X axis */
		X,
		/** the constant to represent Y axis */
		Y
	}

	/** An axis position */
	public enum Position {
		/** bottom or left side of chart */
		Primary,
		/** top or right side of chart */
		Secondary
	}

	/**
	 * Gets the axis id.
	 * <p>
	 * An axis id is automatically assigned when axis is created.
	 * 
	 * @return the axis id
	 */
	int getId();

	/**
	 * Gets the axis direction.
	 * <p>
	 * The axis direction is set when axis is created, and won't be changed.
	 * 
	 * @return the axis direction
	 */
	Direction getDirection();

	/**
	 * Gets the axis position.
	 * 
	 * @return the axis position
	 */
	Position getPosition();

	/**
	 * Sets the axis position.
	 * 
	 * @param position
	 *            the axis position
	 */
	void setPosition(Position position);

	/**
	 * Sets the axis range.
	 * 
	 * @param range
	 *            the axis range
	 */
	void setRange(Range range);

	/**
	 * Gets the axis range.
	 * 
	 * @return the axis range
	 */
	Range getRange();

	/**
	 * Gets the axis title.
	 * 
	 * @return the axis title
	 */
	ITitle getTitle();

	/**
	 * Gets the axis tick.
	 * 
	 * @return the axis tick
	 */
	IAxisTick getTick();

	/**
	 * Enables the log scale. If enabling log scale, stacking trace and category
	 * axis will be disabled.
	 * 
	 * @param enabled
	 *            true if enabling log scales
	 * @throws IllegalStateException
	 *             if minimum value of series belonging to this axis is less
	 *             than zero.
	 */
	void enableLogScale(boolean enabled) throws IllegalStateException;

	/**
	 * Gets the state indicating if log scale is enabled.
	 * 
	 * @return true if log scale is enabled
	 */
	boolean isLogScaleEnabled();

	/**
	 * Sets the log scale base. It is set by default to 10.
	 * 
	 * @param base
	 *            the new base
	 */
	void setLogScaleBase(double base);

	/**
	 * Returns the current base for the logarithmic scale
	 *
	 * @return Current base for the logarithmic scale
	 */
	public double getLogScaleBase();

	/**
	 * Gets the grid. The gird interval is identical with the position of axis
	 * tick marks. The horizontal grid is accessible from vertical axis, and the
	 * vertical grid is accessible from horizontal axis.
	 * 
	 * @return grid the grid
	 */
	IGrid getGrid();

	/**
	 * Adjusts the axis range to the series belonging to the axis, so that all
	 * series are completely shown.
	 */
	void adjustRange();

	/**
	 * Zooms in the axis.
	 */
	void zoomIn();

	/**
	 * Zooms in the axis at the given coordinate.
	 * 
	 * @param coordinate
	 *            the coordinate
	 */
	void zoomIn(double coordinate);

	/**
	 * Zooms out the axis.
	 */
	void zoomOut();

	/**
	 * Zooms out the axis at the given coordinate.
	 * 
	 * @param coordinate
	 *            the coordinate
	 */
	void zoomOut(double coordinate);

	/**
	 * Scrolls up the axis.
	 */
	void scrollUp();

	/**
	 * Scrolls up the axis.
	 */
	void scrollDown();

	/**
	 * Enables category. Category is applicable only for X axis. If enabling
	 * category, log scale will be disabled. If category series are not yet set,
	 * category won't be enabled.
	 * 
	 * @param enabled
	 *            true if enabling category
	 */
	void enableCategory(boolean enabled);

	/**
	 * Gets the state indicating if category is enabled.
	 * 
	 * @return true if category is enabled
	 */
	boolean isCategoryEnabled();

	/**
	 * Sets the category series. In order to enable category series,
	 * <tt>enableCategoryAxis(true)</tt> has to be invoked.
	 * 
	 * @param series
	 *            the category series
	 */
	void setCategorySeries(String[] series);

	/**
	 * Gets the category series. If the category series haven't been set yet,
	 * <tt>null</tt> will be returned.
	 * 
	 * @return the category series
	 */
	String[] getCategorySeries();

	/**
	 * Gets the pixel coordinate corresponding to the given data coordinate.
	 * 
	 * @param dataCoordinate
	 *            the data coordinate
	 * @return the pixel coordinate on plot area
	 */
	int getPixelCoordinate(double dataCoordinate);

	/**
	 * Gets the pixel coordinate corresponding to the given data coordinate.
	 * 
	 * @param dataCoordinate
	 *            the data coordinate
	 * @param lower
	 *            the min value of range
	 * @param upper
	 *            the max value of range
	 * @return the pixel coordinate on plot area
	 */
	int getPixelCoordinate(double dataCoordinate, double lower, double upper);

	/**
	 * Gets the data coordinate corresponding to the given pixel coordinate on
	 * plot area.
	 * 
	 * @param pixelCoordinate
	 *            the pixel coordinate on plot area
	 * @return the data coordinate
	 */
	double getDataCoordinate(int pixelCoordinate);

	/**
	 * Sets the axis reversed. When X axis is reversed, axis values increase from right to left.
	 * When Y axis is reversed, axis values increase from top to bottom.
	 * 
	 * @param reverse
	 *            true to show reversed axis
	 */
	void setReversed(boolean reverse);

	/**
	 * Gets the state indicating if axis is reversed.
	 * 
	 * @return true if axis is reversed
	 */
	boolean isReversed();

	/**
	 * Adds the dispose listener. The newly created color or font for axis can
	 * be disposed with the dispose listener when they are no longer needed.
	 * 
	 * @param listener
	 *            the dispose listener
	 */
	void addDisposeListener(IDisposeListener listener);

	void setIntegerDataPointAxis(boolean integerDataPointAxis);

	boolean isIntegerDataPointAxis();

	boolean isDrawAxisLine();

	void setDrawAxisLine(boolean drawAxisLine);

	boolean isDrawPositionMarker();

	void setDrawPositionMarker(boolean drawPositionMarker);

	void updatePositionMarker(MouseEvent e);

	/**
	 * Sets the minimum and maximum limits for the axis range. These limits
	 * are enforced during dynamic operations such as {@code adjustRange},
	 * {@code scroll}, and {@code zoom}. However, they do not restrict
	 * manual range settings through the {@link #setRange(Range, boolean)} method.
	 *
	 * @param minRange
	 *            the minimum value allowed for the axis range.
	 * @param maxRange
	 *            the maximum value allowed for the axis range.
	 * @throws IllegalArgumentException
	 *             if {@code minRange} is greater than or equal to {@code maxRange}.
	 */
	void setLimitRange(double minRange, double maxRange);
}