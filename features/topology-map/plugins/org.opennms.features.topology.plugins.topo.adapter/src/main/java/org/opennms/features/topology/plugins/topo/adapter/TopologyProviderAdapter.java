package org.opennms.features.topology.plugins.topo.adapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.opennms.features.topology.api.TopologyProvider;
import org.opennms.features.topology.api.topo.GraphProvider;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologyProviderAdapter {
	
	
	private static final String NAMESPACE = "namespace";
	private static final String LABEL = "label";
	
	private static final Logger s_log = LoggerFactory.getLogger(TopologyProviderAdapter.class);
	
	private BundleContext m_bundleContext;
	private final Map<TopologyProvider, ServiceRegistration> m_registrations = new HashMap<TopologyProvider, ServiceRegistration>();

	public void setBundleContext(BundleContext context) {
		m_bundleContext = context;
	}
	
	public void addTopologyProvider(TopologyProvider topologyProvider, Map<String, Object> metaData) {
    	
    	s_log.debug("Adding topology provider: " + topologyProvider);
    	
    	Properties properties = new Properties();
    	for(Entry<String, Object> entry : metaData.entrySet()) {
    		properties.put(entry.getKey(), entry.getValue().toString());
    	}
    	
    	TPGraphProvider graphProvider = new TPGraphProvider(topologyProvider);
    	
    	ServiceRegistration reg = m_bundleContext.registerService(GraphProvider.class.getName(), graphProvider, properties);
    	
    	m_registrations.put(topologyProvider, reg);
    }
    
	public void removeTopologyProvider(TopologyProvider topologyProvider, Map<String, Object> metaData) {
    	
    	s_log.debug("Removing topology provider: " + topologyProvider);
    	
    	ServiceRegistration reg = m_registrations.remove(topologyProvider);
    	if (reg != null) {
    		reg.unregister();
    	}
    	
    }
}