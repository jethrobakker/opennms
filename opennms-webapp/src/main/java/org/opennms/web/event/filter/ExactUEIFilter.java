/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2006-2012 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2012 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.web.event.filter;

import org.opennms.web.filter.EqualsFilter;
import org.opennms.web.filter.SQLType;

/**
 * Encapsulates filtering on exact unique event identifiers.
 *
 * @author ranger
 * @version $Id: $
 * @since 1.8.1
 */
public class ExactUEIFilter extends EqualsFilter<String> {
    /** Constant <code>TYPE="exactUei"</code> */
    public static final String TYPE = "exactUei";

    /**
     * <p>Constructor for ExactUEIFilter.</p>
     *
     * @param uei a {@link java.lang.String} object.
     */
    public ExactUEIFilter(String uei) {
        super(TYPE, SQLType.STRING, "EVENTUEI", "eventUei", uei);
    }

    /**
     * <p>toString</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @Override
    public String toString() {
        return ("<WebEventRepository.ExactUEIFilter: " + this.getDescription() + ">");
    }

    /**
     * <p>getUEI</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUEI() {
        return getValue();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ExactUEIFilter)) return false;
        return (this.toString().equals(obj.toString()));
    }
}
