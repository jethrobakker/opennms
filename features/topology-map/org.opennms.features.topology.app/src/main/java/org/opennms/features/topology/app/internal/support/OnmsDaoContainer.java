/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2012 The OpenNMS Group, Inc.
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

package org.opennms.features.topology.app.internal.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.opennms.netmgt.dao.OnmsDao;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;

public abstract class OnmsDaoContainer<T,K extends Serializable> implements Container {

	private static final long serialVersionUID = -9131723065433979979L;

	private final OnmsDao<T,K> m_dao;

	public OnmsDaoContainer(OnmsDao<T,K> dao) {
		m_dao = dao;
	}

	@Override
	public final boolean addContainerProperty(Object propertyId, Class<?> type, Object defaultValue) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot add new properties to objects in this container");
	}

	/**
	 * Can be overridden if you want to support adding items.
	 */
	@Override
	public Object addItem() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot add new items to this container");
	}

	/**
	 * Can be overridden if you want to support adding items.
	 */
	@Override
	public Item addItem(Object itemId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot add new items to this container");
	}

	@Override
	public boolean containsId(Object itemId) {
		return m_dao.get((K)itemId) != null;
	}

	@Override
	public Property getContainerProperty(Object itemId, Object propertyId) {
		Item item = getItem(itemId);
		return item.getItemProperty(propertyId);
	}

	@Override
	public abstract Collection<?> getContainerPropertyIds();

	@Override
	public Item getItem(Object itemId) {
		T bean = m_dao.get((K)itemId);
		return new BeanItem<T>(bean);
	}

	@Override
	public Collection<?> getItemIds() {
		List<T> beans = m_dao.findAll();
		List<K> retval = new ArrayList<K>();
		for (T bean : beans) {
			retval.add(getId(bean));
		}
		return retval;
	}

	protected abstract K getId(T bean);

	@Override
	public abstract Class<?> getType(Object propertyId);

	@Override
	public boolean removeAllItems() throws UnsupportedOperationException {
		m_dao.clear();
		return true;
	}

	@Override
	public final boolean removeContainerProperty(Object propertyId) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove properties from objects in this container");
	}

	@Override
	public boolean removeItem(Object itemId) throws UnsupportedOperationException {
		m_dao.delete((K)itemId);
		return true;
	}

	@Override
	public int size() {
		return m_dao.countAll();
	}
}